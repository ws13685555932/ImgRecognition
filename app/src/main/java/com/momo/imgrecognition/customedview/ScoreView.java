package com.momo.imgrecognition.customedview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.momo.imgrecognition.R;

/**
 * Created by Administrator on 2017/5/23.
 */

public class ScoreView extends View {
    private int defaultSize = 300;

    private Paint mPaint;
    private Paint mTextPaint;
    private Paint mFillPaint;

    private int progress = 0;
    private int total = 10;
    private int margin = 30;
    private int level = 0;

    private SweepGradient gradient;
    private Matrix mMatrix = new Matrix();

    int centerX;
    int radius = 220;
    int centerY;

    private boolean isAnim = false;
    private boolean isError = false;

    public ScoreView(Context context) {
        super(context);
        initData(context);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        defaultSize = dp2px(defaultSize);

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        mFillPaint = new Paint();
        mFillPaint.setColor(getResources().getColor(R.color.theme_color_primary));
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(100);
        mTextPaint.setColor(getResources().getColor(R.color.white));


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
        centerY = getHeight() / 2;

        int white = getResources().getColor(R.color.white);
        int whiteTrans = getResources().getColor(R.color.white_trans);
        gradient = new SweepGradient(centerX, centerY, whiteTrans, white);
        mMatrix.setRotate(90, centerX, centerY);//旋转mRotate度,圆心为(x,y)
        gradient.setLocalMatrix(mMatrix);
    }

    int currAngle = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(isError){
            isAnim = false;
        }

        float ratio = calculateRatio();
        int sweepAngle = (int) (ratio * 270);

        drawMaxAndProgress(canvas, sweepAngle);

        drawLittleIndicator(canvas, sweepAngle);

        drawScore(canvas,sweepAngle,ratio);

        drawLevel(canvas);

        if(isAnim){
            currAngle += 15;
            postInvalidateDelayed(20);
        }


    }

    private void drawLevel(Canvas canvas) {
        mTextPaint.setTextSize(50);
        Paint.FontMetrics fmBottom = mTextPaint.getFontMetrics();
        float heightfmBottom = fmBottom.bottom - fmBottom.top;
        float baseLinefmBottom = (float) (heightfmBottom / 2 - fmBottom.bottom + centerY + radius * 0.707 + margin);
        if(isError){
            canvas.drawText("Lv.?", centerX, baseLinefmBottom, mTextPaint);
        }else {
            canvas.drawText("Lv." + level, centerX, baseLinefmBottom, mTextPaint);
        }
    }

    private void drawScore(Canvas canvas, int sweepAngle,float ratio) {
        mTextPaint.setTextSize(100);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float height = fm.bottom - fm.top;
        float baseLine = height / 2 - fm.bottom + centerY;
        if(currAngle < sweepAngle && isAnim) {
            canvas.drawText("" + (int)(total * (currAngle*1.0/270)), centerX, baseLine, mTextPaint);
        }else if(isError){
            canvas.drawText("???", centerX, baseLine, mTextPaint);
        } else{
            canvas.drawText("" +(int) (total * ratio), centerX, baseLine, mTextPaint);
        }
    }

    private void drawLittleIndicator(Canvas canvas, int sweepAngle) {
        canvas.save();

        canvas.translate(centerX, centerY);
        canvas.rotate(-135);
        if(currAngle < sweepAngle && isAnim){
            canvas.rotate(currAngle);
        }else {
            canvas.rotate(sweepAngle);
        }
        canvas.drawCircle(0, -radius, 10, mFillPaint);
        canvas.restore();
    }

    private void drawMaxAndProgress(Canvas canvas, int sweepAngle) {
        mPaint.setColor(getResources().getColor(R.color.white_trans));
        canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                , 135, 270, false, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
//        mPaint.setShader(gradient);

        if(currAngle < sweepAngle && isAnim){
            canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                    , 135, currAngle, false, mPaint);
        }else {
            isAnim = false;
            canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                    , 135, sweepAngle, false, mPaint);
        }
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

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
        postInvalidate();
    }

    public void startAnim(){
        isAnim = true;
        currAngle = 0;
        postInvalidate();
    }

    public void isError(boolean isError){
        this.isError = isError;
        postInvalidate();
    }
}
