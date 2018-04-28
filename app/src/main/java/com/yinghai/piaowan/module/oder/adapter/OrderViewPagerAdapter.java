package com.yinghai.piaowan.module.oder.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.SampleApplicationLike;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/17
 *         Description：${VULUES}
 * @Param
 */

public class OrderViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    public OrderViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        if (fragments != null) {
            mList = fragments;
        }else{
            mList = new ArrayList<>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return SampleApplicationLike.getMyApplication().getResources().getStringArray(R.array.order_tab)[position];
    }
}
