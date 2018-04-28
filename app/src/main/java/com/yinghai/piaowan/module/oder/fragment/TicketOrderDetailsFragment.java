package com.yinghai.piaowan.module.oder.fragment;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/17
 *         Description：票務訂單詳情 fragment
 * @Param
 */

public class TicketOrderDetailsFragment extends MyBaseFragment{
    @Override
    public String getTitle() {
        return getString(R.string.order_detail);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_ticket_details;
    }
}
