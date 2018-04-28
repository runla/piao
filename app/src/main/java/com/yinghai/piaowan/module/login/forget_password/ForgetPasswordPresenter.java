package com.yinghai.piaowan.module.login.forget_password;

import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.LoginBean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 18:09
 *         Describe：找回密码的Presenter
 */

public class ForgetPasswordPresenter extends MyBasePresenter<ForgetPasswordRepository,ForgetPasswordContract.IView> implements ForgetPasswordContract.IPresenter, ForgetPasswordContract.IRepository.IFindPasswordCallback {

   public ForgetPasswordPresenter(ForgetPasswordContract.IView view){
       attachView(view);
   }

    @Override
    protected ForgetPasswordRepository createModel() {
        return new ForgetPasswordRepository();
    }

    @Override
    public void onFindPassword(String countryCode, String tel, String verification, String password, String deviceId) {
        mBaseModel.findPassword(countryCode,tel,verification,password,deviceId,this);
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onFindPasswordSuccess(LoginBean.DataBean bean) {
        if (isViewAttached()){
            getBaseView().showFindPasswordSuccess(bean);
        }
    }

    @Override
    public void onFindPasswordFailure(String errorMsg) {
        if (isViewAttached()){
            getBaseView().showFindPasswordFailure(errorMsg);
        }
    }
}
