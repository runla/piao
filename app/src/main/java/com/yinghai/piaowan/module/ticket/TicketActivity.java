package com.yinghai.piaowan.module.ticket;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.manager.MyFragmentManager;
import com.yinghai.piaowan.module.ticket.detail.TicketDetailFragment;
import com.yinghai.piaowan.module.ticket.fragment.SelectTicketFragment;
import com.yinghai.piaowan.module.ticket.pay.PayTicketFragment;

/**
 * @author Created by：fanson
 *         Created on：2018/4/20 14:10
 *         Description：票务详情主页
 */
public class TicketActivity extends MyBaseActivity {
    private TicketDetailFragment mTicketDetailFragment;
    private SelectTicketFragment mSelectTimeFragment;
    private PayTicketFragment mPrePayTicketFragment;
    private static final String PARAM_TICKET_ID = "ticketId";
    private int mTicketId = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_buy_ticket;
    }

    @Override
    protected void initView() {
        super.initView();

    }

    /**
     * 打开票务详情
     *
     * @param activity 开始跳转的Activity
     * @param ticketId 票务ID
     */
    public static void startActivityToDetail(Activity activity, int ticketId) {
        Intent intent = new Intent(activity, TicketActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_TICKET_ID, ticketId);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void initData() {
        if (getIntent().getExtras() != null) {
            mTicketId = getIntent().getExtras().getInt(PARAM_TICKET_ID);
            initTicketDetailFragment(mTicketId);
        }
    }

    @Override
    protected void listenEvent() {

    }

    /**
     * 初始化购票的 fragment
     *
     * @param ticketId 需要显示的票务详情ID
     */
    private void initTicketDetailFragment(int ticketId) {
        if (mTicketDetailFragment == null) {
            mTicketDetailFragment = TicketDetailFragment.newInstance(ticketId);
        }
        MyFragmentManager.replaceFragment(getSupportFragmentManager(), R.id.container, mTicketDetailFragment);
        mCurrentFragment = mTicketDetailFragment;
    }

    /**
     * 描述：初始化选择票的数量、类型的 fragment
     */
    private void initSelectTicketFragment(TicketDetailBean.DataBean ticketBean) {
        if (mSelectTimeFragment == null) {
            mSelectTimeFragment = new SelectTicketFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("ticketBean",ticketBean);
        mSelectTimeFragment.setArguments(bundle);
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.container, mTicketDetailFragment, mSelectTimeFragment, null,
                R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        mCurrentFragment = mSelectTimeFragment;
    }

    /**
     */
    private void initPrePayFragment(Bundle bundle) {
        if (mPrePayTicketFragment == null) {
            mPrePayTicketFragment = new PayTicketFragment();
        }
        mPrePayTicketFragment.setArguments(bundle);
        MyFragmentManager.switchFragment(getSupportFragmentManager(), R.id.container, mSelectTimeFragment, mPrePayTicketFragment, null,
                R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out, R.anim.fragment_slide_to_right_in, R.anim.fragment_slide_to_right_out);
        mCurrentFragment = mPrePayTicketFragment;
    }

    @Override
    public void onFragmentCallback(Object... objects) {
        super.onFragmentCallback(objects);
        switch ((int) objects[0]) {
            case ConFragmentResponseCode.TICKET_SELECT_TIME:
                initSelectTicketFragment((TicketDetailBean.DataBean) objects[1]);
                break;

            case ConFragmentResponseCode.TICKET_PRE_PAY:
                initPrePayFragment((Bundle) objects[1]);
                break;

         /*   case ConFragmentResponseCode.TICKET_GO_TO_PAY:
                initGoTOPayFragment(true);
                break;*/

            case ConFragmentResponseCode.TICKET_BACK:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (MyFragmentManager.handlerBackPress(getSupportFragmentManager()) && getSupportFragmentManager().getBackStackEntryCount() <=0) {
            finish();
        }
    }
}
