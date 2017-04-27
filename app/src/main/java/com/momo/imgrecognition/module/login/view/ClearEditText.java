package com.momo.imgrecognition.module.login.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.utils.DrawableUtil;

import static com.momo.imgrecognition.utils.DrawableUtil.tintDrawable;

public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener,
        TextWatcher {
    //EditText右侧的删除按钮
    //TODO:删除图片变色效果
    private Drawable mClearDrawable;
    private boolean hasFoucs;
    private Paint underlinePaint;
    int underlineHeight = 5;
    float paddingBottom = 0.05f;
    int paddingLeft = 5;

    int themePrimary;
    ColorStateList themePrimaryCsl;


    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        themePrimaryCsl= getResources().getColorStateList(R.color.theme_color_primary);
        themePrimary = getResources().getColor(R.color.theme_color_primary);
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3,）  
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.drawable.ic_text_delete);
            mClearDrawable = DrawableUtil.tintDrawable(mClearDrawable,themePrimaryCsl);
        }
        mClearDrawable.setBounds(0, 0, 80, 80);
        setCompoundDrawables(getCompoundDrawables()[0], null, mClearDrawable, null);

        //设置左边图片
        Drawable leftDrawable = getCompoundDrawables()[0];
        if (leftDrawable != null) {
            leftDrawable.setBounds(0, 0, 80, 80);
            setCompoundDrawables(leftDrawable, null, getCompoundDrawables()[2], null);
        }


        // 默认设置隐藏图标  
        setClearIconVisible(false);
        // 设置焦点改变的监听  
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听  
        addTextChangedListener(this);

        underlinePaint = new Paint();
        underlinePaint.setAntiAlias(true);
        underlinePaint.setStyle(Paint.Style.FILL);
        underlinePaint.setColor(getResources().getColor(R.color.editTextUnderline));

    }

    /**
     * @说明：isInnerWidth, isInnerHeight为ture，触摸点在删除图标之内，则视为点击了删除图标
     * event.getX() 获取相对应自身左上角的X坐标
     * event.getY() 获取相对应自身左上角的Y坐标
     * getWidth() 获取控件的宽度
     * getHeight() 获取控件的高度
     * getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
     * getPaddingRight() 获取删除图标右边缘到控件右边缘的距离
     * isInnerWidth:
     * getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
     * getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
     * isInnerHeight:
     * distance 删除图标顶部边缘到控件顶部边缘的距离
     * distance + height 删除图标底部边缘到控件顶部边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，
     * 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
            underlinePaint.setColor(themePrimary);
            Drawable left = getCompoundDrawables()[0];
            tintAndSetBounds(left,themePrimaryCsl);
        } else {
            underlinePaint.setColor(getResources().getColor(R.color.editTextUnderline));
            setClearIconVisible(false);
            Drawable left =getCompoundDrawables()[0];
            tintAndSetBounds(left,ColorStateList.valueOf(getResources().getColor(R.color.editTextUnderline)));
        }
        postInvalidate();
    }

    private void tintAndSetBounds(Drawable left ,ColorStateList colorStateList) {
        if (left != null) {
            left = tintDrawable(left, colorStateList);
            left.setBounds(0, 0, 80, 80);
            setCompoundDrawables(left, null, getCompoundDrawables()[2], null);
        }
    }


    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int underlineBottom = (int) (this.getHeight() * (1 - paddingBottom));
        int underlineTop = underlineBottom - underlineHeight;
        int underlineLeft = paddingLeft;
        int underlineRight = this.getWidth() - paddingLeft;

        canvas.drawRect(underlineLeft, underlineTop, underlineRight, underlineBottom, underlinePaint);
    }

}