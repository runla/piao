package com.yinghai.piaowan.module.login.register;

import android.app.Activity;
import android.content.Context;
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
import com.yinghai.piaowan.bean.VerifyCodeBean;
import com.yinghai.piaowan.callback.OnFragmentListener;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.module.login.helper.LoginInputUtil;
import com.yinghai.piaowan.widget.PowerfulEditText;

/**
 * Created by chenjianrun on 2018/4/9.
 * 描述：註冊的 fragment
 */

public class RegisterFragment extends MyBaseMvpFragment<RegisterPresenter> implements RegisterContract.IView {
    private static final int PASSWORD_COUNT = 6;
    private Activity hostActivity;
    private PowerfulEditText mEtPhone;
    private PowerfulEditText mEtPassword;
    private PowerfulEditText mEtVerifyCode;
    private TextView mTvCountryCode;
    private TextView mTvGetVerifyCode;
    private Button mBtnRegister;
    private CountDownTimer mCountDownTimer;
    /**
     * 注册时，申请权限的Code
     */
    public static final int REGISTER_APPLY_PERMISSION = 595;
    /**
     * 获取验证码需要等待的时长，默认60s
     */
    private static final int WAIT_TIME_SECOND = 1000 * 60;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentListener = (OnFragmentListener) getActivity();
        hostActivity = getActivity();
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
        super.initView(view, bundle);
        mEtPhone = (PowerfulEditText) view.findViewById(R.id.et_phone);
        mEtPassword = (PowerfulEditText) view.findViewById(R.id.et_password);
        mTvCountryCode = (TextView) view.findViewById(R.id.tv_country_code);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register);
        mEtVerifyCode = view.findViewById(R.id.et_verify_code);
        mTvGetVerifyCode = view.findViewById(R.id.tv_get_verify_code);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTvGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginInputUtil.checkPhone(getCountryCode(), getTelNo()) == null) {
                    showLoading();
                    startCountTimer();
                    mPresenter.getVerifyCode(getCountryCode(), getTelNo(), getPassword());
                } else {
                    ShowToast.singleShort(LoginInputUtil.checkPhone(getCountryCode(), getTelNo()));
                }
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister();
            }
        });
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
     * 触发注册操作
     */
    public void gotoRegister() {
        if (LoginInputUtil.checkAll(getCountryCode(), getTelNo(), getVerifyCode(), getPassword()) == null) {
            //判断如果设备ID为空，则通知Activity申请权限
            if (SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null) == null) {
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.APPLY_PERMISSION,REGISTER_APPLY_PERMISSION);
            } else {
                showLoading();
                mPresenter.onRegister(getCountryCode(), getTelNo(), getVerifyCode(), getPassword(), SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null));
            }

        } else {
            ShowToast.singleShort(LoginInputUtil.checkAll(getCountryCode(), getTelNo(), getVerifyCode(), getPassword()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCountTimer();
    }

    /**
     * 开启计时器
     */
    private void startCountTimer() {
        if (mCountDownTimer == null) {
            mTvGetVerifyCode.setEnabled(false);
            mTvGetVerifyCode.setTextColor(ContextCompat.getColor(hostActivity, R.color.md_grey_500_color_code));
            mCountDownTimer = new CountDownTimer(WAIT_TIME_SECOND, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTvGetVerifyCode.setText(String.format(hostActivity.getString(R.string.secs_later), millisUntilFinished / 1000));
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
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void showRegisterSuccess(LoginBean.DataBean bean) {
        hideLoading();
        ShowToast.singleShort(bean.getUser().getUNick());
    }

    @Override
    public void showRegisterFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

    @Override
    public void showVerifyCodeSuccess(VerifyCodeBean.DataBean bean) {
        hideLoading();
        ShowToast.singleShort(getString(R.string.verify_code_has_send));
    }

    @Override
    public void showVerifyCodeFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleShort(errorMsg);
    }

}
