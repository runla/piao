package com.yinghai.piaowan.module.main.ticketlist;

import com.yinghai.piaowan.base.MyBasePresenter;
import com.yinghai.piaowan.bean.TicketsListBean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 11:42
 *         Describe：获取票务列表resenter
 */
public class TicketListPresenter extends MyBasePresenter<TicketListRepository, TicketListContract.IView> implements TicketListContract.IPresenter,
        TicketListContract.IRepository.ITicketListCallback {

    public TicketListPresenter(TicketListContract.IView view) {
        attachView(view);
    }

    @Override
    public void onTicketList(int page) {
        mBaseModel.onTicketList(page, this);
    }

    @Override
    public void onTicketListSuccess(TicketsListBean.DataBean bean) {
        if (isViewAttached()) {
            getBaseView().showTicketListSuccess(bean);
        }
    }

    @Override
    public void onTicketListFailure(String errorMsg) {
        if (isViewAttached()) {
            getBaseView().showTicketListFailure(errorMsg);
        }
    }

    @Override
    protected TicketListRepository createModel() {
        return new TicketListRepository();
    }

    @Override
    public void handlerResultCode(int code) {
        handleResultCode(code);
    }
}