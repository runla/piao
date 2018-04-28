package com.yinghai.piaowan.module.person.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/13
 *         Description：我的收藏 fragment
 * @Param
 */

public class CollectFragment extends MyBaseFragment{

    @Override
    public String getTitle() {
        return getString(R.string.my_collect);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        StatusBarUtil.setStatusBarColor(hostActivity, ContextCompat.getColor(hostActivity, R.color.md_grey_100_color_code));

        return view;
    }
}
