package com.yinghai.piaowan.module.shop.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.widget.LikeShape;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/16
 *         Description：商品详情
 * @Param
 */

public class ShopDetailFragment  extends MyBaseFragment {
    private TextView mTvPrice;          //  商品價格
    private TextView mTvDescription;    //  商品描述
    private TextView mTvColor;          //  商品顏色
    private TextView mTvTransport;      //  商品郵費
    private TextView mTvAddToCar;       //  加入購物車
    private TextView mTvBuyNow;         //  立即購買

    private ImageView mIvCollect;       //  收藏界面
    private ImageView mIvShopCar;       //  購物車
    private LikeShape mLikeShop;        //  收藏商品

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_detail;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mTvPrice = view.findViewById(R.id.tv_shop_price);
        mTvDescription = view.findViewById(R.id.tv_shop_description);
        mTvColor = view.findViewById(R.id.tv_shop_style);
        mTvTransport = view.findViewById(R.id.tv_shop_transport);
        mTvAddToCar = view.findViewById(R.id.tv_add_to_car);
        mTvBuyNow = view.findViewById(R.id.tv_buy_now);
        mIvCollect = view.findViewById(R.id.iv_shop_collect);
        mIvShopCar = view.findViewById(R.id.iv_shop_car);
        mLikeShop = view.findViewById(R.id.like_collect);
        return view;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();

        //  加入購物車
        mTvAddToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {

                }
            }
        });

        //  立即購買
        mTvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.SHOP_PAY);
                }
            }
        });

        //  我的收藏界面
        mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_COLLECT);
                }
            }
        });

        //  購物車界面
        mIvShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_ORDER);
                }
            }
        });

        //  收藏商品
        mLikeShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLikeShop.isSelected()) {
                    mLikeShop.setSelected(false);
                }else{
                    mLikeShop.setSelected(true);
                    mLikeShop.startAnimation();
                }
            }
        });
    }
}
