package com.yinghai.piaowan.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by chenjianrun on 2018/4/8.
 */

public class LabelDrawable extends Drawable {

    public int mWidth,mHeight;
    private Paint mPaint;
    public LabelDrawable(int width,int height,int color){
        this.mHeight = height;
        this.mWidth = width;

        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }

    /**
     * drawable 核心方法，最終繪製圖形
     * @param canvas
     */
    @Override
    public void draw(@NonNull Canvas canvas) {
        Path path = new Path();
        path.lineTo(mWidth - mHeight/2,0);
        path.lineTo(mWidth,mHeight/2);
        path.lineTo(mWidth - mHeight/2,mHeight);
        path.lineTo(0,mHeight);
        path.close();
        canvas.drawPath(path,mPaint);
    }

    /**
     * 設置透明度
     * @param alpha
     */
    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth()
    {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight()
    {
        return mHeight;
    }
}
