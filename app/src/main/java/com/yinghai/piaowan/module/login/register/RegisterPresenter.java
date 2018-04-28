package com.yinghai.piaowan.module.login.register;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.bean.VerifyCodeBean;
import com.yinghai.piaowan.constant.ConstantPreference;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 9:43
 *         Describe：注册的Presenter
 */

public class RegisterPresenter extends MyBasePresenter<RegisterRepository, RegisterContract.IView> implements RegisterContract.IPresenter, RegisterContract.IModel.IRegisterCallback, GetVerifyCodeRepository.IVerifyCodeCallback {

    private GetVerifyCodeRepository mVerifyCodeRepository;

    /**
     * 获取GetVerifyCodeRepository实例
     *
     * @return
     */
    public GetVerifyCodeRepository getVerifyCodeRepository() {
        if (mVerifyCodeRepository == null) {
            mVerifyCodeRepository = new GetVerifyCodeRepository();
        }
        return mVerifyCodeRepository;
    }

    public RegisterPresenter(RegisterContract.IView view) {
        attachView(view);
    }

    @Override
    protected RegisterRepository createModel() {
        return new RegisterRepository();
    }

    @Override
    public void onRegister(String countryCode, String tel, String verifyCode, String password, String deviceId) {
        mBaseModel.register(countryCode, tel, verifyCode, password, deviceId, this);
    }

    @Override
    public void getVerifyCode(String countryCode, String tel, String deviceId) {
        getVerifyCodeRepository().getVerifyCode(countryCode, tel, GetVerifyCodeRepository.CODE_TYPE_LOGIN, deviceId, this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (this.mVerifyCodeRepository != null) {
            this.mVerifyCodeRepository.onDestroy();
            this.mVerifyCodeRepository = null;
        }
    }

    @Override
    public void onRegisterSuccess(LoginBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showRegisterSuccess(bean);
        }
    }

    @Override
    public void onRegisterFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showRegisterFailure(errorMsg);
        }
    }

    @Override
    public void onGetVerifyCodeSuccess(VerifyCodeBean.DataBean bean) {
        SharePreferenceHelper.putString(ConstantPreference.S_SESSION_ID,bean.getSessionId());
        SharePreferenceHelper.apply();
        if (isViewAttached()) {
            getBaseView().showVerifyCodeSuccess(bean);
        }
    }

    @Override
    public void onGetVerifyCodeFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showVerifyCodeFailure(errorMsg);
        }
    }
}
