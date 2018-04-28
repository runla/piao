package com.yinghai.piaowan.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/19
 *         Description：上购票界面中，进入商城的按钮跟随底部 view 变化的行为类
 * @Param
 */

public class RightBehavior extends CoordinatorLayout.Behavior<View> {

    private String viewTag = "BottomSheet";
    private static final String TAG = "RightBehavior";
    private float mStartY = -1;

    public RightBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency.getTag() instanceof String
                && TextUtils.equals((String)dependency.getTag(),viewTag)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(dependency);
        if (mStartY == -1) {
            mStartY = parent.getBottom() - behavior.getPeekHeight();
            return true;
        }
        float scaleY = 0;
        if (mStartY != 0) {
            scaleY = (1- (dependency.getY()) / mStartY);
        }
        child.setTranslationX(child.getWidth() * scaleY);
        return true;
    }


}
