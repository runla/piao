package com.yinghai.piaowan.module.shop.fragment;

import android.view.View;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/16
 *         Description：商城購物車的 fragment
 * @Param
 */

public class ShopCarFragment extends MyBaseFragment{
    @Override
    public String getTitle() {
        return getString(R.string.shop_car);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public String getRightTitle() {
        return getString(R.string.select_all);
    }
}
