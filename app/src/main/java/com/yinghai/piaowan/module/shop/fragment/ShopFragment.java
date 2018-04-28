package com.yinghai.piaowan.module.shop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.bean.ShopBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.module.shop.adapter.GoodLongAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/13
 *         Description：玩具主題商城
 * @Param
 */

public class ShopFragment extends MyBaseFragment implements GoodLongAdapter.ClickShopCallback, OnRefreshListener, OnLoadMoreListener {
    private RecyclerView mRecyclerView;
    private GoodLongAdapter mAdapter;
//    private RefreshLayout refreshLayout;
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        BottomNavigationView navigation = view.findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

//        refreshLayout = view.findViewById(R.id.refreshLayout);
//        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setOnLoadMoreListener(this);
        //设置 Footer 为 球脉冲 样式
//        refreshLayout.setRefreshFooter(new BallPulseFooter(hostActivity).setSpinnerStyle(SpinnerStyle.Scale));

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new GoodLongAdapter(hostActivity);
        mAdapter.setmClickShopCallback(this);
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
//        refreshLayout.autoLoadMore();
    }

    @Override
    protected void initData() {
        super.initData();
        List<ShopBean> list = new ArrayList<>();
        list.add(new ShopBean("http://img.redocn.com/sheying/20151218/tangbaomaoronggongzi_5620190.jpg","糖宝毛绒公仔",10));
        list.add(new ShopBean("http://img.redocn.com/sheying/20160812/keaidexiaotuzigongzitupian_6923687.jpg","可爱的小兔子公仔图片",10));
        list.add(new ShopBean("http://img.redocn.com/sheying/20140809/keaidongwugongzi_2860285.jpg","可爱动物公仔",20));
        list.add(new ShopBean("http://img.redocn.com/sheying/20160830/tiaodanzidekatongnvhai_7026041.jpg","挑担子的卡通女孩",30));
        list.add(new ShopBean("http://img.redocn.com/sheying/20151218/tangbaomaoronggongzi_5620190.jpg","糖宝毛绒公仔",10));
        list.add(new ShopBean("http://img.redocn.com/sheying/20160812/keaidexiaotuzigongzitupian_6923687.jpg","可爱的小兔子公仔图片",10));
        list.add(new ShopBean("http://img.redocn.com/sheying/20140809/keaidongwugongzi_2860285.jpg","可爱动物公仔",20));
        list.add(new ShopBean("http://img.redocn.com/sheying/20160830/tiaodanzidekatongnvhai_7026041.jpg","挑担子的卡通女孩",30));
        mAdapter.fillList(list);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (mFragmentListener == null) {
                return false;
            }
            switch (item.getItemId()) {
                case R.id.navigation_shop_car:
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.SHOP_CAR);
                    return true;

                case R.id.navigation_shop_order:
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_ORDER);
                    return true;

                case R.id.navigation_shop_collect:
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_COLLECT);
                    return true;
                default:
                    break;
            }
            return false;
        }

    };

    @Override
    public void onSelectShop(ShopBean shopBean) {
        if (mFragmentListener != null) {
            mFragmentListener.onFragmentCallback(ConFragmentResponseCode.SHOP_DETAILS);
        }

    }

    /**
     * 下拉刷新
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
    }

    /**
     * 加載更多
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(2000);//

        /*new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    refreshLayout.finishLoadMore(false);//

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}
