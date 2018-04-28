package com.yinghai.piaowan.module.ticket.detail;

import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.TicketDetailBean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 14:45
 *         Describe：票务详情Presenter
 */
public class TicketDetailPresenter extends MyBasePresenter<TicketDetailRepository, TicketDetailContract.IView> implements TicketDetailContract.IPresenter,
        TicketDetailContract.IRepository.ITicketDetailCallback {


    public TicketDetailPresenter(TicketDetailContract.IView view) {
        attachView(view);
    }


    @Override
    public void onTicketDetailSuccess(TicketDetailBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showTicketDetailSuccess(bean);
        }
    }

    @Override
    public void onTicketDetailFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showTicketDetailFailure(errorMsg);
        }
    }

    @Override
    protected TicketDetailRepository createModel() {
        return new TicketDetailRepository();
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }

    @Override
    public void onTicketDetail(int ticketId) {
        mBaseModel.onTicketDetail(ticketId,this);
    }
}