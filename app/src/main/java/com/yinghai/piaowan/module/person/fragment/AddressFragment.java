package com.yinghai.piaowan.module.person.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.fansonlib.utils.StatusBarUtil;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.widget.DrawableTextView;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/13
 *         Description：地址管理界面的 fragment
 * @Param
 */

public class AddressFragment extends MyBaseFragment{
    private AutoLoadRecyclerView mRecyclerView;
    private DrawableTextView mTvEditAddress;

    @Override
    public String getTitle() {
        return getString(R.string.address_manager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        StatusBarUtil.setStatusBarColor(hostActivity, ContextCompat.getColor(hostActivity, R.color.md_grey_100_color_code));
        mTvEditAddress = view.findViewById(R.id.tv_edit_address);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(hostActivity));

        return view;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();

        // 編輯地址
        mTvEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
