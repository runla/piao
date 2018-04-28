package com.yinghai.piaowan.module.person.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/12
 *         Description：账号绑定 fragment
 * @Param
 */

public class BindAccountFragment extends MyBaseFragment{
    private RelativeLayout mRlWechat;
    private RelativeLayout mRlFacebook;
    private RelativeLayout mRlPhone;
    private TextView mTvWechatHasBand;
    private TextView mTvFacebookHasBand;
    private TextView mTvPhoneHasBand;
    private ImageView mIvWechatRight;
    private ImageView mIvFacebookRight;
    private ImageView mIvPohoneRight;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bind_account;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view,bundle);
        StatusBarUtil.setStatusBarColor(hostActivity, ContextCompat.getColor(hostActivity, R.color.md_grey_100_color_code));
        mRlWechat = view.findViewById(R.id.rl_wechat);
        mRlFacebook = view.findViewById(R.id.rl_facebook);
        mRlPhone = view.findViewById(R.id.rl_phone);

        mTvWechatHasBand = view.findViewById(R.id.tv_wechat_has_bind);
        mTvFacebookHasBand = view.findViewById(R.id.tv_facebook_has_bind);
        mTvPhoneHasBand = view.findViewById(R.id.tv_phone_has_bind);

        mIvWechatRight = view.findViewById(R.id.iv_wechat_right);
        mIvFacebookRight = view.findViewById(R.id.iv_facebook_right);
        mIvPohoneRight = view.findViewById(R.id.iv_phone_right);


        return null;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public String getTitle() {
        return getString(R.string.bind_account);
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mRlWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRlFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRlPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
