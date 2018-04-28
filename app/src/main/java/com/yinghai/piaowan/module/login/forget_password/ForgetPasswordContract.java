package com.yinghai.piaowan.module.login.forget_password;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 18:04
 *         Describe：找回密码的契约类
 */

public class ForgetPasswordContract {

    interface IView extends BaseView {

        /**
         * 显示成功找回密码
         *
         * @param bean 返回的数据
         */
        void showFindPasswordSuccess(LoginBean.DataBean bean);

        /**
         * 显示失败找回密码
         *
         * @param errorMsg 失败信息
         */
        void showFindPasswordFailure(String errorMsg);

    }

    interface IPresenter {

        /**
         * 前往找回密码
         *
         * @param countryCode 区号
         * @param tel         手机号码
         * @param password    密码
         * @param deviceId    设备ID
         */
        void onFindPassword(String countryCode, String tel, String verification, String password, String deviceId);

    }


    interface IRepository {

        /**
         * 前往找回密码
         *
         * @param countryCode  区号
         * @param tel          手机号码
         * @param verification 验证码
         * @param password     密码
         * @param deviceId     设备ID
         * @param callback     回调
         */
        void findPassword(String countryCode, String tel, String verification, String password, String deviceId, IFindPasswordCallback callback);


        interface IFindPasswordCallback extends IHandleCodeCallback {

            /**
             * 显示成功找回密码
             *
             * @param bean 返回的数据
             */
            void onFindPasswordSuccess(LoginBean.DataBean bean);

            /**
             * 显示失败找回密码
             *
             * @param errorMsg 失败信息
             */
            void onFindPasswordFailure(String errorMsg);

        }

    }

}
