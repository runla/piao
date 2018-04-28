package com.yinghai.piaowan.module.ticket.detail;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 14:13
 *         Describe：票务详情契约类
 */
public class TicketDetailContract {

    public interface IView extends BaseView {

        /**
         * 显示成功的界面
         *
         * @param bean 返回数据实体类
         **/
        void showTicketDetailSuccess(TicketDetailBean.DataBean bean);

        /**
         * 显示失败的界面
         *
         * @param errorMsg 失败信息
         **/
        void showTicketDetailFailure(String errorMsg);

    }

    interface IPresenter {

        /**
         * 触发逻辑层
          * @param ticketId 票务活动ID
         **/
        void onTicketDetail(int ticketId);

    }

    interface IRepository {

        /**
         * 触发数据层
         * @param ticketId 票务活动ID
         * @param callback 回调
         */
        void onTicketDetail(int ticketId,ITicketDetailCallback callback);

        /**
         * 数据层回调
         **/
        interface ITicketDetailCallback extends IHandleCodeCallback{

            /**
             * 回调成功的数据
             *
             * @param bean 返回数据实体类
             **/
            void onTicketDetailSuccess(TicketDetailBean.DataBean bean);

            /**
             * 回调失败的数据
             *
             * @param errorMsg 失败信息
             **/
            void onTicketDetailFailure(String errorMsg);

        }

    }


}  