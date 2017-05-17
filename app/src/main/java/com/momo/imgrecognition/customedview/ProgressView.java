package com.momo.imgrecognition.customedview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.momo.imgrecognition.R;

/**
 * Created by Administrator on 2017/5/17.
 * todo add finished and not finished style
 */

public class ProgressView extends View {

    private int defaultSize = 100;

    private Paint mPaint;
    private Paint mTextPaint;

    private int progress = 10;
    private int total = 10;
    private int margin = 30;

    int centerX;
    int radius = 40;
    int centerY;

    public ProgressView(Context context) {
        super(context);
        initData(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        defaultSize = dp2px(defaultSize);

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.theme_color_primary));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(getResources().getColor(R.color.theme_color_primary));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            width = Math.min(sizeWidth, defaultSize);
        }

        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            height = Math.min(sizeHeight, defaultSize);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2 - margin/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float ratio = calculateRatio();
        float sweepAngle = ratio * 360;
        mPaint.setColor(getResources().getColor(R.color.gray_light));
        canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                , -90, 360, false, mPaint);
        mPaint.setColor(getResources().getColor(R.color.theme_color_primary));
        canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                , -90, sweepAngle, false, mPaint);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float height = fm.bottom - fm.top;
        float baseLine = height / 2 - fm.bottom + centerY + radius + margin;
        canvas.drawText(progress + "/" + total, centerX, baseLine, mTextPaint);
    }

    private float calculateRatio() {
        float ratio = 0;
        if (total != 0) {
            ratio = (float) (progress * 1.0 / total);
        }
        return ratio;
    }

    public int dp2px(int values) {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
