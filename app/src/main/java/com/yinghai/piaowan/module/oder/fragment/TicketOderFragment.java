package com.yinghai.piaowan.module.oder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.module.oder.TicketOrderDetailsActivity;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/17
 *         Description：票務的訂單記錄，有團體票和個人票
 * @Param
 */

public class TicketOderFragment extends MyBaseFragment{
    private ConstraintLayout mRootView;
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_ticket;
    }
    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mRootView = findMyViewId(R.id.rootView);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostActivity.overridePendingTransition(R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
                startActivity(new Intent(hostActivity, TicketOrderDetailsActivity.class));

            }
        });
        return view;

    }
}
