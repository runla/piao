package com.yinghai.piaowan.module.game.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.bean.IntegralBean;
import com.yinghai.piaowan.module.game.adapter.IntegralAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/23
 *         Description：積分領取的 fragment
 * @Param
 */

public class IntegralFragment extends MyBaseFragment {
    private RecyclerView mRecyclerView;
    private IntegralAdapter mAdapter;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mRecyclerView = findMyViewId(R.id.recyclerview);

        FlexboxLayoutManager manager = new FlexboxLayoutManager();
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);
        manager.setJustifyContent(JustifyContent.CENTER);

        mRecyclerView.setLayoutManager(manager);

        mAdapter = new IntegralAdapter(hostActivity);
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }

    @Override
    protected void initData() {
        super.initData();
        List<IntegralBean> list = new ArrayList<>();
        list.add(new IntegralBean(1,1));
        list.add(new IntegralBean(2,2));
        list.add(new IntegralBean(3,4));
        list.add(new IntegralBean(4,6));
        list.add(new IntegralBean(5,8));
        list.add(new IntegralBean(6,10));
        list.add(new IntegralBean(7,12));
        mAdapter.fillList(list);
    }
}
