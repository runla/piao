package com.yinghai.piaowan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.yinghai.piaowan.R;


/**
 * Created by chenjianrun on 2018/1/31.
 */

public class DrawableTextView extends android.support.v7.widget.AppCompatTextView {
    private int mDrawableLeftSize;
    private int mDrawableRightSize;
    private int mDrawableTopSize;
    private int mDrawableBottomSize;

    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;
    private Drawable mDrawableTop;
    private Drawable mDrawableBottom;

    public DrawableTextView(Context context) {
        this(context,null);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DrawableTextViewStyle, defStyleAttr, 0);
        double scale;
        mDrawableLeft = typedArray.getDrawable(R.styleable.DrawableTextViewStyle_myDrawableLeft);
        if (mDrawableLeft != null) {
            mDrawableLeftSize = typedArray.getDimensionPixelSize(R.styleable.DrawableTextViewStyle_myDrawableLeftSize,mDrawableLeft.getMinimumWidth());
            scale = (1.0*mDrawableLeft.getMinimumWidth())/mDrawableLeft.getMinimumHeight();
            mDrawableLeft.setBounds(0,0,mDrawableLeftSize, (int) (mDrawableLeftSize/scale));
        }

        mDrawableRight = typedArray.getDrawable(R.styleable.DrawableTextViewStyle_myDrawableRight);
        if (mDrawableRight != null) {
            mDrawableRightSize = typedArray.getDimensionPixelSize(R.styleable.DrawableTextViewStyle_myDrawableRightSize,mDrawableRight.getMinimumWidth());
            scale = (1.0*mDrawableRight.getMinimumWidth())/mDrawableRight.getMinimumHeight();
            mDrawableRight.setBounds(0,0,mDrawableRightSize, (int) (mDrawableRightSize/scale));
        }

        mDrawableTop = typedArray.getDrawable(R.styleable.DrawableTextViewStyle_myDrawableTop);
        if (mDrawableTop != null) {
            mDrawableTopSize = typedArray.getDimensionPixelSize(R.styleable.DrawableTextViewStyle_myDrawableTopSize,mDrawableTop.getMinimumWidth());
            scale = (1.0*mDrawableTop.getMinimumWidth())/mDrawableTop.getMinimumHeight();
            mDrawableTop.setBounds(0,0,mDrawableTopSize, (int) (mDrawableTopSize/scale));
        }

        mDrawableBottom = typedArray.getDrawable(R.styleable.DrawableTextViewStyle_myDrawableBottom);
        if (mDrawableBottom != null) {
            mDrawableBottomSize = typedArray.getDimensionPixelSize(R.styleable.DrawableTextViewStyle_myDrawableBottomSize,mDrawableBottom.getMinimumWidth());
            scale = (1.0*mDrawableBottom.getMinimumWidth())/mDrawableBottom.getMinimumHeight();
            mDrawableBottom.setBounds(0,0,mDrawableBottomSize, (int) (mDrawableBottomSize/scale));
        }
        setCompoundDrawables(mDrawableLeft,mDrawableTop,mDrawableRight,mDrawableBottom);
        typedArray.recycle();
    }
}
