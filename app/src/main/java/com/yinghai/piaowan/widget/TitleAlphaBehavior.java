package com.yinghai.piaowan.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/19
 *         Description：设置为改 behavior 的 View 的背景色 跟随 BottomSheetBehavior 的滑动，透明度发生变化
 *         使用方法：BottomSheetBehavior 设置为 BottomSheetBehavior 的 View 必须设置 tag = "BottomSheet"
 * @Param
 */

public class TitleAlphaBehavior extends CoordinatorLayout.Behavior<View> {
    private String viewTag = "BottomSheet";
    private static final String TAG = "TitleAlphaBehavior";
    private float mStartY = -1;

    public TitleAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency.getTag() instanceof String
                &&TextUtils.equals((String)dependency.getTag(),viewTag)) {
            return true;
        }
        return false;
    }
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) dependency.getLayoutParams();
        if (mStartY == -1) {
            mStartY = dependency.getY()-params.topMargin;
            return true;
        }
        float scaleY =1-( (dependency.getY()-params.topMargin) / mStartY);
        if (scaleY>0.5){
            scaleY += (scaleY-0.5)*0.5;
        }
        if (scaleY >= 1){
            scaleY = 1;
        }
        child.setBackgroundColor(Color.argb((int)(scaleY*255),255,255,255));
        return true;
    }
}
