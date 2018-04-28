package com.yinghai.piaowan.module.login.register;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.bean.VerifyCodeBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 13:38
 *         Describe：注册的契约类
 */

public class RegisterContract {

    interface IView extends BaseView{

        /**
         * 注册成功
         * @param bean 响应参数
         */
        void showRegisterSuccess(LoginBean.DataBean bean);

        /**
         * 注册失败
         * @param errorMsg 失败信息
         */
        void showRegisterFailure(String errorMsg);

        /**
         * 获取验证码成功
         * @param bean 响应参数
         */
        void showVerifyCodeSuccess(VerifyCodeBean.DataBean bean);

        /**
         * 获取验证码失败
         * @param errorMsg 失败信息
         */
        void showVerifyCodeFailure(String errorMsg);

    }

    interface IPresenter{

        /**
         * 前往注册
         * @param countryCode 区号
         * @param tel 电话号码
         * @param verifyCode 验证码
         * @param password 密码
         * @param deviceId 设备ID
         */
        void onRegister(String countryCode,String tel,String verifyCode,String password,String deviceId);

        /**
         * 获取验证码的逻辑层
         * @param countryCode 区号
         * @param tel 电话号码
         * @param deviceId 设备ID
         */
        void getVerifyCode(String countryCode,String tel ,String deviceId);
    }



    interface IModel {

        /**
         * 前往注册
         * @param countryCode 区号
         * @param tel 电话号码
         * @param verifyCode 验证码
         * @param password 密码
         * @param deviceId 设备ID
         * @param callback 回调
         */
        void register(String countryCode,String tel,String verifyCode,String password,String deviceId,IRegisterCallback callback);


        interface IRegisterCallback extends IHandleCodeCallback{

            /**
             * 显示注册成功
             * @param bean 响应参数
             */
            void onRegisterSuccess(LoginBean.DataBean bean);

            /**
             * 显示注册失败
             * @param errorMsg 失败信息
             */
            void onRegisterFailure(String errorMsg);

        }

    }

}
