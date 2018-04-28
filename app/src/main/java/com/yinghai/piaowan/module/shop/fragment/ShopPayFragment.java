package com.yinghai.piaowan.module.shop.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/16
 *         Description：商品支付界面
 * @Param
 */

public class ShopPayFragment extends MyBaseFragment {
    private ImageView mIvShopPic;       //  商品圖
    private TextView mTvShopDescrip;    //  商品描述
    private TextView mTvShopColor;      //  商品顏色
    private TextView mTvShopNum;        //  商品數量
    private TextView mTvShopPrice;      //  商品價格
    private TextView mTvShopTotal;      //  商品總費用
    private TextView mTvTransport;      //  郵費
    private TextView mTvAddress;        //  收貨地址

    private RelativeLayout mRlWechat;   //  微信支付
    private RelativeLayout mRlAli;      //  支付寶支付
    private View mDotWechat;            //
    private View mDotAli;               //

    private Button mBtnPay;
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_pay;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mIvShopPic = view.findViewById(R.id.iv_show);
        mTvShopDescrip = view.findViewById(R.id.tv_shop_description);
        mTvShopColor = view.findViewById(R.id.tv_shop_description);
        mTvShopNum = view.findViewById(R.id.tv_shop_num);
        mTvShopPrice = view.findViewById(R.id.tv_shop_price);
        mTvShopTotal = view.findViewById(R.id.tv_shop_total_fee);
        mTvTransport = view.findViewById(R.id.tv_shop_transport);
        mTvAddress = view.findViewById(R.id.tv_address);

        mRlWechat = view.findViewById(R.id.rl_wechat_pay);
        mRlAli = view.findViewById(R.id.rl_ali_pay);
        mDotWechat = view.findViewById(R.id.view_dot_wechat);
        mDotAli = view.findViewById(R.id.view_dot_alipay);

        mBtnPay = view.findViewById(R.id.btn_pay);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();

        // 選擇地址
        mTvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.SHOP_SELECT_ADDRESS);
                }
            }
        });

        // 微信支付
        mRlWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDotAli.setSelected(false);
                mDotWechat.setSelected(true);
            }
        });

        // 支付寶支付
        mRlAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDotAli.setSelected(true);
                mDotWechat.setSelected(false);
            }
        });

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
