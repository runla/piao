package com.yinghai.piaowan.module.person.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/12
 *         Description：我的订单的 fragment
 * @Param
 */

public class OrderFragment extends MyBaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view,bundle);
        StatusBarUtil.setStatusBarColor(hostActivity, ContextCompat.getColor(hostActivity, R.color.md_grey_100_color_code));

        return null;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public String getTitle() {
        return getString(R.string.my_order);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();

    }
}
