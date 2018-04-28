package com.yinghai.piaowan.module.login.forget_password;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseMvpFragment;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.module.login.helper.LoginInputUtil;
import com.yinghai.piaowan.widget.PowerfulEditText;

/**
 * Created by chenjianrun on 2018/4/9.
 * 描述：忘記密碼的 fragment
 */

public class ForgetPasswordFragment extends MyBaseMvpFragment<ForgetPasswordPresenter> implements ForgetPasswordContract.IView {
    private static final int PASSWORD_COUNT = 6;

    private PowerfulEditText mEtPhone;
    private PowerfulEditText mEtPassword;
    private PowerfulEditText mEtVerifyCode;
    private TextView mTvCountryCode;
    private TextView mTvGetVerifyCode;
    private Button mBtnFindPwd;
    private CountDownTimer mCountDownTimer;
    /**
     * 获取验证码需要等待的时长，默认60s
     */
    private static final int WAIT_TIME_SECOND = 1000 * 60;
    /**
     * 找回密码时，申请权限的Code
     */
    public static final int FIND_PASSWORD_APPLY_PERMISSION = 599;

    @Override
    protected ForgetPasswordPresenter createPresenter() {
        return new ForgetPasswordPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view,bundle);

        mEtPhone = (PowerfulEditText) view.findViewById(R.id.et_phone);
        mEtPassword = (PowerfulEditText) view.findViewById(R.id.et_password);
        mTvCountryCode = (TextView) view.findViewById(R.id.tv_country_code);
        mBtnFindPwd = (Button) view.findViewById(R.id.btn_register);
        mEtVerifyCode = view.findViewById(R.id.et_verify_code);
        mTvGetVerifyCode = view.findViewById(R.id.tv_get_verify_code);
        mBtnFindPwd.setText(R.string.find_password);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void listenEvent() {
        super.listenEvent();
        mTvGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCountTimer();
            }
        });

        mBtnFindPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFindPassword();
            }
        });
    }

    /**
     * 触发找回密码操作
     */
    public void gotoFindPassword() {
        if (LoginInputUtil.checkAll(getCountryCode(), getTelNo(), getVerifyCode(), getPassword()) == null) {
            //判断如果设备ID为空，则通知Activity申请权限
            if (SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null) == null) {
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.APPLY_PERMISSION,FIND_PASSWORD_APPLY_PERMISSION);
            } else {
                showLoading();
                mPresenter.onFindPassword(getCountryCode(), getTelNo(), getVerifyCode(), getPassword(), SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null));
            }

        } else {
            ShowToast.singleShort(LoginInputUtil.checkAll(getCountryCode(), getTelNo(), getVerifyCode(), getPassword()));
        }
    }

    private String getCountryCode() {
        return mTvCountryCode.getText().toString().replace("+", "").trim();
    }

    private String getTelNo() {
        return mEtPhone.getText().toString().trim();
    }

    private String getVerifyCode() {
        return mEtVerifyCode.getText().toString().trim();
    }

    private String getPassword() {
        return mEtPassword.getText().toString().trim();
    }

    /**
     * 开启计时器
     */
    private void startCountTimer() {
        if (mCountDownTimer == null) {
            mTvGetVerifyCode.setTextColor(ContextCompat.getColor(hostActivity, R.color.md_grey_500_color_code));
            mCountDownTimer = new CountDownTimer(WAIT_TIME_SECOND, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTvGetVerifyCode.setText(String.format( hostActivity.getString(R.string.secs_later),millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    mCountDownTimer = null;
                    mTvGetVerifyCode.setEnabled(true);
                    mTvGetVerifyCode.setTextColor(ContextCompat.getColor(hostActivity, R.color.primaryBlue));
                    mTvGetVerifyCode.setText(hostActivity.getString(R.string.get_verify_code));
                }
            }.start();
        }
    }

    /**
     * 停止计时器
     */
    private void stopCountTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCountTimer();
    }


    @Override
    public void showFindPasswordSuccess(LoginBean.DataBean bean) {
        hideLoading();
        ShowToast.singleShort(getString(R.string.find_password_success));
    }

    @Override
    public void showFindPasswordFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }
}
