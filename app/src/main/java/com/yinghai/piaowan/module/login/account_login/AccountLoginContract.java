package com.yinghai.piaowan.module.login.account_login;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 14:01
 *         Describe：账号登录契约类
 */

public class AccountLoginContract {

    interface IView extends BaseView{

        /**
         * 显示账号登录成功
         * @param bean 返回的数据
         */
        void showAccountLoginSuccess(LoginBean.DataBean bean);

        /**
         * 显示账号登录失败
         * @param errorMsg 失败信息
         */
        void showAccountLoginFailure(String errorMsg);

    }

    interface IPresenter{

        /**
         * 前往账号登录
         * @param countryCode 区号
         * @param tel 手机号码
         * @param password 密码
         * @param deviceId 设备ID
         */
        void onAccountLogin(String countryCode,String tel,String password,String deviceId);

    }


    interface IRepository {

        /**
         * 前往账号登录
         * @param countryCode 区号
         * @param tel 手机号码
         * @param password 密码
         * @param deviceId 设备ID
         * @param callback 回调
         */
        void loginByAccount(String countryCode,String tel,String password,String deviceId,IAccountLoginCallback callback);


        interface IAccountLoginCallback extends IHandleCodeCallback {

            /**
             * 显示账号登录成功
             * @param bean 返回的数据
             */
            void onAccountLoginSuccess(LoginBean.DataBean bean);

            /**
             * 显示账号登录失败
             * @param errorMsg 失败信息
             */
            void onAccountLoginFailure(String errorMsg);

        }

    }
    
}
