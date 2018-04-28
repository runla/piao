package com.yinghai.piaowan.module.oder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.module.oder.ShopOrderDetailActivity;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/17
 *         Description：商品訂單記錄
 * @Param
 */

public class ShopOrderFragment extends MyBaseFragment {
    private ConstraintLayout mRootView;
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_shop;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mRootView = findMyViewId(R.id.rootView);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostActivity.overridePendingTransition(R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
                startActivity(new Intent(hostActivity, ShopOrderDetailActivity.class));

            }
        });
        return view;

    }

    @Override
    protected void initData() {
        super.initData();
    }
}
