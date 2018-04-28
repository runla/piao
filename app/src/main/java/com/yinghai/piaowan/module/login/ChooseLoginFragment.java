package com.yinghai.piaowan.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.animation.CubeAnimation;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.module.main.MainActivity;
import com.yinghai.piaowan.wxapi.wechatLogin.WechatLogin;
import com.yinghai.piaowan.wxapi.wechatLogin.WechatLoginCallback;

/**
 * Created by chenjianrun on 2018/4/9.
 * 描述：選擇登錄的界面
 */

public class ChooseLoginFragment extends MyBaseFragment  {

    private TextView mTvAccountLogin;
    private TextView mTvFacebookLogin;
    private TextView mTvWechatLogin;
    private static final String TAG = ChooseLoginFragment.class.getSimpleName();
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_login;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.RIGHT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.LEFT, false, ANIM_DURATION);
        }
    }

    @Override
    public void listenEvent() {
        super.listenEvent();
        mTvAccountLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.REQUEST_LOGIN);
                }
            }
        });

        mTvFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvWechatLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WechatLogin.getInstance().wechatLogin(hostActivity, Config.WECHAT_APP_ID, new WechatLoginCallback() {
                    @Override
                    public void showWechatLoginSuccess() {
                        Log.d(TAG, "showWeChatLoginSuccess: ");
                    }

                    @Override
                    public void wechatLoginSuccess(String code) {
                        Log.d(TAG, "wechatLoginSuccess: ");
                        hostActivity.overridePendingTransition(R.anim.fragment_slide_to_left_in,R.anim.fragment_slide_to_left_out);
                        startActivity(new Intent(hostActivity, MainActivity.class));
                    }

                    @Override
                    public void wechatLoginFailed(String errMsg) {
                        Log.d(TAG, "wechatLoginFailed: ");
                    }
                });
            }
        });

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view,bundle);
        mTvAccountLogin = (TextView) view.findViewById(R.id.tv_account_login);
        mTvFacebookLogin = (TextView) view.findViewById(R.id.tv_facebook_login);
        mTvWechatLogin = (TextView) view.findViewById(R.id.tv_wechat_login);
        return view;
    }

    @Override
    protected void initData() {

    }
}
