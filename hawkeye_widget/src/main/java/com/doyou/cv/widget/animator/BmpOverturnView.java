package com.doyou.cv.widget.animator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.doyou.cv.utils.DensityUtil;
import com.doyou.cv.utils.Util;

import androidx.annotation.Nullable;

public class BmpOverturnView extends View {

    public static int IMAGE_WIDTH = 0;
    private Context context;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap image;
    Camera mCamera = new Camera();
    float left = 0f, top = 0f;
    float topFlip = 0f;
    float bottomFlip = 0f;
    float flipRotation = 0f;

    // 将属性名单独包装下，便于外部和谐引用
    public String getPropIsTopFlip() {
        return "topFlip";
    }

    public String getPropIsBottomFlip() {
        return "bottomFlip";
    }

    public String getPropFlipRotation() {
        return "flipRotation";
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public void propReset() {
        setBottomFlip(0f);
        setTopFlip(0f);
        setFlipRotation(0f);
    }

    public BmpOverturnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        IMAGE_WIDTH =DensityUtil.dp2px(context,180);
    }

    {
        image = Util.getAvatar(getResources(), IMAGE_WIDTH);
        mCamera.setLocation(0, 0, Util.getZForCamera());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (IMAGE_WIDTH * 1.5f), (int) (IMAGE_WIDTH * 1.5f));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        left = (getWidth() - IMAGE_WIDTH) / 2;
        top = (getHeight() - IMAGE_WIDTH) / 2 - DensityUtil.dp2px(context,50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 上半部分
        canvas.save();
        canvas.translate(left + IMAGE_WIDTH / 2, top + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(topFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(left + IMAGE_WIDTH / 2), -(top + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, left, top, mPaint);
        canvas.restore();

        // 下半部分
        canvas.save();
        canvas.translate(left + IMAGE_WIDTH / 2, top + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(bottomFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(left + IMAGE_WIDTH / 2), -(top + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, left, top, mPaint);
        canvas.restore();
    }
}
