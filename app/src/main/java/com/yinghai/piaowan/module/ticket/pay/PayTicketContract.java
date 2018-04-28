package com.yinghai.piaowan.module.ticket.pay;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.CreateOrderBean;
import com.yinghai.piaowan.bean.WechatPayBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

import java.util.Map;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/25
 *         Description：支付的契约类
 * @Param
 */
public class PayTicketContract {

    interface IView extends BaseView {

        /**
         * 显示支付成功的界面
         *
         **/
        void showPayTicketSuccess();

        /**
         * 显示失败的界面
         *
         * @param errorMsg 失败信息
         **/
        void showPayTicketFailure(String errorMsg);

        /**
         * 获取网络请求参数
         * @return
         */
        Map<String, Object> getMaps();

        /**
         * 获取支付方式类型
         * @return  0：支付宝   1：微信
         */
        int getPayType();
    }

    interface IPresenter {

        /**
         * 下单并支付
         */
        void createAndPayOrder();


    }

    interface IRepository {
        /**
         * 下单
         */
        void createOrder(Map<String,Object> maps,ICreateTicketCallback callback);

        /**
         * 创建订单结果回调
         **/
        interface ICreateTicketCallback extends IHandleCodeCallback {

            /**
             * 创建订单成功
             * @param createOrderBean
             */
            void onCreateTicketSuccess(CreateOrderBean createOrderBean);

            /**
             * 创建订单失败失败
             *
             * @param errorMsg 失败信息
             **/
            void onCreateTicketFailure(String errorMsg);

        }


        /**
         * 微信支付
         **/
        void onWechatPayTicket(String orderNo,IPayTicketCallback callback);

        /**
         * 微信支付结果回调
         **/
        interface IPayTicketCallback extends IHandleCodeCallback {

            /**
             * 微信支付向后台发起请求成功
             * @param wechatPayBean
             */
            void onWechatPayTicketSuccess(WechatPayBean wechatPayBean);

            /**
             * 微信支付向后台发起请求失败
             *
             * @param errorMsg 失败信息
             **/
            void onWechatPayTicketFailure(String errorMsg);

        }

    }


}  