package com.yinghai.piaowan.module.ticket.pay;

import android.content.Context;

import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.CreateOrderBean;
import com.yinghai.piaowan.bean.WechatPayBean;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.wxapi.pay.WechatPay;
import com.yinghai.piaowan.wxapi.pay.WechatPayCallback;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/25
 *         Description：${VULUES}
 * @Param
 */
public class PayTicketPresenter extends MyBasePresenter<PayTicketRepository, PayTicketContract.IView> implements PayTicketContract.IPresenter, PayTicketContract.IRepository.IPayTicketCallback, PayTicketContract.IRepository.ICreateTicketCallback {

    private Context mContext;
    private int mPayType;   // 支付方式
    public PayTicketPresenter(PayTicketContract.IView view, Context context) {
        attachView(view);
        this.mContext = context;
    }


    @Override
    protected PayTicketRepository createModel() {
        return new PayTicketRepository();
    }

    @Override
    public void createAndPayOrder() {
        getBaseView().showLoading();
        mPayType = getBaseView().getPayType();
        mBaseModel.createOrder(getBaseView().getMaps(),this);
    }



    @Override
    public void onWechatPayTicketSuccess(WechatPayBean wechatPayBean) {
        WechatPay.getInstance().startPay(mContext, Config.WECHAT_APP_ID, wechatPayBean.getData().getData(), new WechatPayCallback() {
            @Override
            public void showPayLoading() {

            }

            @Override
            public void paySuccess() {
                if (isViewAttached()) {
                    getBaseView().showPayTicketSuccess();
                }
            }

            @Override
            public void payFailure(int errCode) {
                handlerResultCode(errCode);
            }
        });
    }

    @Override
    public void onWechatPayTicketFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().showPayTicketFailure(errorMsg);
        }
    }


    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onCreateTicketSuccess(CreateOrderBean createOrderBean) {
        getBaseView().hideLoading();
        if (mPayType == PayTicketFragment.ALI_PAY) {

        }else if(mPayType == PayTicketFragment.WECHAT_PAY){
            mBaseModel.onWechatPayTicket(createOrderBean.getData().getTicketOrderHelper().getOrder().getOOrderNo(),this);
        }
    }

    @Override
    public void onCreateTicketFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().hideLoading();
            getBaseView().showPayTicketFailure(errorMsg);
        }
    }
}