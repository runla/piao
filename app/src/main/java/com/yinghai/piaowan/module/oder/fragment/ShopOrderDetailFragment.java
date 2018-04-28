package com.yinghai.piaowan.module.oder.fragment;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/17
 *         Description：商品訂單詳情
 * @Param
 */

public class ShopOrderDetailFragment extends MyBaseFragment {
    @Override
    public String getTitle() {
        return getString(R.string.order_detail);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_shop_details;
    }
}
