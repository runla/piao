package com.yinghai.piaowan.module.oder;

import android.view.View;
import android.widget.ImageView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;

/**
 * @author chenjianrun
 * 描述：票務訂單的詳情
 */
public class TicketOrderDetailsActivity extends MyBaseActivity {
    private ImageView mIvBack;

    @Override
    protected int getContentView() {
        return R.layout.activity_ticket_order_details;
    }
    @Override
    protected void initView() {
        super.initView();
        mIvBack = findViewById(R.id.iv_back);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void listenEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
