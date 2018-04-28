package com.yinghai.piaowan.module.main.ticketlist;

import com.example.fansonlib.base.BaseView;
import com.yinghai.piaowan.bean.TicketsListBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 11:34
 *         Describe：票务列表契约类
 */
public class TicketListContract {

    public interface IView extends BaseView {

        /**
         * 显示成功的界面
         *
         * @param bean 返回数据实体类
         **/
        void showTicketListSuccess(TicketsListBean.DataBean bean);

        /**
         * 显示失败的界面
         *
         * @param errorMsg 失败信息
         **/
        void showTicketListFailure(String errorMsg);

    }

    interface IPresenter {

        /**
         * 获取票务列表的逻辑层
         * @param page 页码
         **/
        void onTicketList(int page);

    }

    interface IRepository {

        /**
         * 获取票务列表的数据层
         * @param page 页码
         * @param callback 回调
         */
        void onTicketList(int page,ITicketListCallback callback);

        /**
         * 数据层回调
         **/
        interface ITicketListCallback extends IHandleCodeCallback{

            /**
             * 回调成功的数据
             *
             * @param bean 返回数据实体类
             **/
            void onTicketListSuccess(TicketsListBean.DataBean bean);

            /**
             * 回调失败的数据
             *
             * @param errorMsg 失败信息
             **/
            void onTicketListFailure(String errorMsg);

        }

    }


}  