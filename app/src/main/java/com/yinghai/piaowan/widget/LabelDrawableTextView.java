package com.yinghai.piaowan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.yinghai.piaowan.R;


/**
 * Created by chenjianrun on 2018/4/8.
 */

public class LabelDrawableTextView extends DrawableTextView {
    public int mColor;
    public LabelDrawableTextView(Context context) {
        this(context,null);
    }

    public LabelDrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LabelDrawableTextView, defStyleAttr, 0);
        mColor = typedArray.getColor(R.styleable.LabelDrawableTextView_myDrawableColor, Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        LabelDrawable labelDrawable = new LabelDrawable(getWidth(),getHeight(),mColor);
        labelDrawable.draw(canvas);
        super.onDraw(canvas);

    }
}
