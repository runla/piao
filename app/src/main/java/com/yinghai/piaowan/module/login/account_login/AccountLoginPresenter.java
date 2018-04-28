package com.yinghai.piaowan.module.login.account_login;

import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.LoginBean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 14:48
 *         Describe：账号登录Presenter
 */

public class AccountLoginPresenter extends MyBasePresenter<AccountLoginRepository,AccountLoginContract.IView> implements AccountLoginContract.IPresenter, AccountLoginContract.IRepository.IAccountLoginCallback {


    public AccountLoginPresenter(AccountLoginContract.IView view){
        attachView(view);
    }

    @Override
    protected AccountLoginRepository createModel() {
        return new AccountLoginRepository();
    }

    @Override
    public void onAccountLogin(String countryCode, String tel, String password, String deviceId) {
        mBaseModel.loginByAccount(countryCode,tel,password,deviceId,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onAccountLoginSuccess(LoginBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showAccountLoginSuccess(bean);
        }
    }

    @Override
    public void onAccountLoginFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showAccountLoginFailure(errorMsg);
        }
    }
}
