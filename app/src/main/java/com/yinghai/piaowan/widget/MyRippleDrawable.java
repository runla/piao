package com.yinghai.piaowan.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.SampleApplicationLike;

import java.lang.ref.WeakReference;

/**
 * Created by chenjianrun on 2018/4/11.
 */

public class MyRippleDrawable extends Drawable {
    private Paint mPaint;
    private Bitmap mBitmap;

    private RectF rectF;
    private float mRadius;
    private Paint mWhitePaint;
    private WeakReference<ImageView> weakReference;

    private float mScreenHeight;
    private float mScreenWidth;
    public MyRippleDrawable(Bitmap bitmap, ImageView imageView) {
        weakReference = new WeakReference<ImageView>(imageView);
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);

        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(Color.WHITE);
    }

    private void startAnim(){
        mScreenHeight = SampleApplicationLike.getMyApplication().getResources().getDimension(R.dimen.y1920);
        mScreenWidth = SampleApplicationLike.getMyApplication().getResources().getDimension(R.dimen.x1080);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,mScreenHeight);
        valueAnimator.setDuration(700);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mRadius = (float) valueAnimator.getAnimatedValue();
                invalidateSelf();
            }
        });
        valueAnimator.start();
    }
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(weakReference.get().getLeft(),
                weakReference.get().getTop(),
                weakReference.get().getRight(),
                weakReference.get().getBottom());
        startAnim();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((rectF.right-rectF.left)/5, rectF.bottom+mScreenHeight/6, mRadius, mWhitePaint);
        canvas.drawCircle((rectF.right-rectF.left)/5, rectF.bottom+mScreenHeight/6, mRadius, mPaint);
    }

    /**
     * 获取底部虚拟键盘的高度
     *
     * @param context
     * @return
     */
    private static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(out);
        return out.heightPixels;
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }



}
