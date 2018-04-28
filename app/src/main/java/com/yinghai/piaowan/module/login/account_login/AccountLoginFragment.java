package com.yinghai.piaowan.module.login.account_login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.animation.CubeAnimation;
import com.yinghai.piaowan.base.MyBaseMvpFragment;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.module.login.helper.LoginInputUtil;
import com.yinghai.piaowan.module.main.MainActivity;
import com.yinghai.piaowan.widget.PowerfulEditText;

/**
 * @author Created by：fanson
 *         Created on：2018/4/13 14:54
 *         Description：账号登录Fragment
 */

public class AccountLoginFragment extends MyBaseMvpFragment<AccountLoginPresenter> implements AccountLoginContract.IView {
    private static final int PASSWORD_COUNT = 6;
    private PowerfulEditText mEtAccount;
    private PowerfulEditText mEtPassword;
    private TextView mTvRegister;
    private TextView mTvForgetPassword;
    private TextView mTvCountryCode;
    private Button mBtnLogin;
    /**
     * 立体翻转的动画时间
     */
    private static final int ANIM_DURATION = 500;
    /**
     * 登录时，申请权限的Code
     */
    public static final int ACCOUNT_LOGIN_APPLY_PERMISSION = 596;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_login;
    }

    @Override
    protected AccountLoginPresenter createPresenter() {
        return new AccountLoginPresenter(this);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.LEFT, true, ANIM_DURATION);
        } else {
            return CubeAnimation.create(CubeAnimation.RIGHT, false, ANIM_DURATION);
        }
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mEtAccount = (PowerfulEditText) view.findViewById(R.id.et_phone);
        mEtPassword = (PowerfulEditText) view.findViewById(R.id.et_password);
        mTvRegister = (TextView) view.findViewById(R.id.tv_register);
        mTvForgetPassword = (TextView) view.findViewById(R.id.tv_forget_password);
        mTvCountryCode = (TextView) view.findViewById(R.id.tv_country_code);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        mEtAccount.setText(SharePreferenceHelper.getString(ConstantPreference.S_PHONE,""));
        mEtPassword.setText(SharePreferenceHelper.getString(ConstantPreference.S_PASSWORD,""));
    }

    @Override
    public void listenEvent() {
        super.listenEvent();
        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.REQUEST_FIND_PASSWORD);
                }
            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.REQUEST_REGISTER);
                }
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });

        mTvCountryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String getTelNo() {
        return mEtAccount.getText().toString().trim();
    }

    private String getCountryCode() {
        return mTvCountryCode.getText().toString().replace("+", "").trim();
    }

    private String getPassword() {
        return mEtPassword.getText().toString().trim();
    }

    /**
     * 触发登录
     */
    public void gotoLogin(){
        if (TextUtils.isEmpty(LoginInputUtil.checkPhoneAndPwd(getCountryCode(), getTelNo(), getPassword()))) {
            //判断如果设备ID为空，则通知Activity申请权限
            if (SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null) == null) {
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.APPLY_PERMISSION,ACCOUNT_LOGIN_APPLY_PERMISSION);
            } else {
                showLoading();
                mPresenter.onAccountLogin(getCountryCode(), getTelNo(), getPassword(), SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null));
            }
        } else {
            ShowToast.singleLong(LoginInputUtil.checkPhoneAndPwd(getCountryCode(), getTelNo(), getPassword()));
        }
    }

    @Override
    public void showAccountLoginSuccess(LoginBean.DataBean bean) {
        hideLoading();
        hostActivity.overridePendingTransition(R.anim.fragment_slide_to_left_in,R.anim.fragment_slide_to_left_out);
        startActivity(new Intent(hostActivity, MainActivity.class));
    }

    @Override
    public void showAccountLoginFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleLong(errorMsg);
    }
}
