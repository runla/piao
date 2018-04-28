package com.yinghai.piaowan.module.login;

import android.Manifest;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.utils.StatusBarUtil;
import com.example.fansonlib.widget.dialogfragment.ConfirmDialog;
import com.example.fansonlib.widget.dialogfragment.base.IConfirmListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.SettingService;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.manager.MyFragmentManager;
import com.yinghai.piaowan.module.login.account_login.AccountLoginFragment;
import com.yinghai.piaowan.module.login.forget_password.ForgetPasswordFragment;
import com.yinghai.piaowan.module.login.register.RegisterFragment;

import java.util.List;

/**
 * 登录界面Activity
 *
 * @author chenjianrun
 */
public class LoginActivity extends MyBaseActivity {
    private ChooseLoginFragment mChooseLoginFragment;
    private AccountLoginFragment mAccountFragment;
    private RegisterFragment mRegisterFragment;
    private ForgetPasswordFragment mForgetPasswordFragment;
    /**
     * 标记哪个界面申请权限，用于获取权限后，可直接进行中断的操作
     */
    private int mApplyPermissionFromCode = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_400_color_code));
        initChooseLoginFragment();
        applyPermission();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化選擇登錄的界面
     */
    private void initChooseLoginFragment() {
        if (mChooseLoginFragment == null) {
            mChooseLoginFragment = new ChooseLoginFragment();
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.container, mChooseLoginFragment);
        mCurrentFragment = mChooseLoginFragment;
    }

    /**
     * 初始化手機賬號登錄的界面
     */
    private void initAccountLoginFragment() {
        if (mAccountFragment == null) {
            mAccountFragment = new AccountLoginFragment();
        }
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.container, mChooseLoginFragment, mAccountFragment);
        mCurrentFragment = mAccountFragment;
    }

    /**
     * 初始化註冊的界面
     */
    private void initRegisterFragment() {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
        }
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.container, mAccountFragment, mRegisterFragment, null,
                R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        mCurrentFragment = mRegisterFragment;
    }

    /**
     * 初始化找回密碼的界面
     */
    private void initForgetPasswordFragment() {
        if (mForgetPasswordFragment == null) {
            mForgetPasswordFragment = new ForgetPasswordFragment();
        }
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.container, mAccountFragment, mForgetPasswordFragment, null,
                R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        mCurrentFragment = mRegisterFragment;
    }

    @Override
    public void listenEvent() {

    }

    /**
     * 申请权限
     */
    private void applyPermission() {
        AndPermission.with(this).permission(Manifest.permission.READ_PHONE_STATE).onGranted(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                getDeviceId();
                continueApplyPerBeforeEvent();
            }
        }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(LoginActivity.this, permissions)) {
                    ConfirmDialog.newInstance(getString(R.string.must_need_permission)).setConfirmListener(new IConfirmListener() {
                        @Override
                        public void onConfirm() {
                            SettingService settingService = AndPermission.permissionSetting(LoginActivity.this);
                            settingService.execute();
                        }
                    }).show(getSupportFragmentManager());
                } else {
                    ShowToast.singleShort(getString(R.string.reject_permission));
                }
            }
        }).start();
    }

    /**
     * 继续申请权限前的操作
     */
    private void continueApplyPerBeforeEvent() {
        switch (mApplyPermissionFromCode) {
            case RegisterFragment.REGISTER_APPLY_PERMISSION:
                mRegisterFragment.gotoRegister();
                break;
            case AccountLoginFragment.ACCOUNT_LOGIN_APPLY_PERMISSION:
                mAccountFragment.gotoLogin();
                break;
            case ForgetPasswordFragment.FIND_PASSWORD_APPLY_PERMISSION:
                mForgetPasswordFragment.gotoFindPassword();
                break;
            default:
                break;
        }
    }

    /**
     * 获取设备ID
     */
    private void getDeviceId() {
        String strIMEI = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        SharePreferenceHelper.putString(ConstantPreference.S_DEVICE_ID, strIMEI);
        SharePreferenceHelper.apply();
    }

    @Override
    public void onBackPressed() {
        if (MyFragmentManager.handlerBackPress(getSupportFragmentManager()) && getSupportFragmentManager().getBackStackEntryCount() <=0) {
            finish();
        }
    }

    @Override
    public void onFragmentCallback(Object... objects) {
        super.onFragmentCallback(objects);
        switch ((int) objects[0]) {
            case ConFragmentResponseCode.REQUEST_LOGIN:
                initAccountLoginFragment();
                break;

            case ConFragmentResponseCode.REQUEST_REGISTER:
                initRegisterFragment();
                break;

            case ConFragmentResponseCode.REQUEST_FIND_PASSWORD:
                initForgetPasswordFragment();
                break;

            case ConFragmentResponseCode.APPLY_PERMISSION:
                mApplyPermissionFromCode = (int) objects[1];
                applyPermission();
                break;

            default:
                break;
        }
    }


}
