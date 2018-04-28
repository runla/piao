package com.yinghai.piaowan.module.ticket.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseMvpFragment;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;

import java.util.HashMap;
import java.util.Map;

import static com.yinghai.piaowan.module.ticket.fragment.SelectTicketFragment.TICKET_SINGLE;
import static com.yinghai.piaowan.module.ticket.fragment.SelectTicketFragment.TICKET_SINGLE_RARLY;
import static com.yinghai.piaowan.module.ticket.fragment.SelectTicketFragment.TICKET_SINGLE_RARLY_SPECIAL;
import static com.yinghai.piaowan.module.ticket.fragment.SelectTicketFragment.TICKET_SINGLE_SPECIAL;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/25
 *         Description：票务支付的 fragment
 * @Param
 */
public class PayTicketFragment extends MyBaseMvpFragment<PayTicketPresenter> implements PayTicketContract.IView {

    public static final int ALI_PAY = 0;
    public static final int WECHAT_PAY = 1;
    Map<String, Object> maps = new HashMap<>();
    private TextView mTvUpdate;
    private Button mBtnConfirm;
    private TextView mTvDate;                   //  日期
    private TextView mTvAddress;                //  地點
    private TextView mTvTicketNum;              //  正常票或者早鳥票的數量
    private TextView mTvTicketFee;              //  正常票或者早鳥票的費用
    private TextView mTvTotalFee;               //  總費用
    private RelativeLayout mRlWechatPay;        //  微信支付
    private RelativeLayout mRlAliPay;           //  支付寶支付
    private View mWechatDot;                    //
    private View mAliDot;                       //
    private ImageView mIvSpecial;               //  限定字样的 icon
    private TextView mTvTikcetType;
    private TicketDetailBean.DataBean mTicketBean;
    private int mTicketPrice;
    private String mTicketTime;
    private String mTicketAddress;
    private int mTicketType;
    private float mTicketTotalFee;
    private int mTicketQuantity;

    public static PayTicketFragment newInstance(Bundle bundle) {
        PayTicketFragment fragment = new PayTicketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected PayTicketPresenter createPresenter() {
        return new PayTicketPresenter(this, hostActivity);
    }

    @Override
    public void showPayTicketSuccess() {
        ShowToast.singleLong("支付成功");
        hideLoading();
    }

    @Override
    public void showPayTicketFailure(String errorMsg) {
        hideLoading();
        ShowToast.singleLong(errorMsg);
    }

    @Override
    public int getPayType() {
        if (mWechatDot.isSelected()) {
            // 微信支付
            return WECHAT_PAY;
        } else if (mAliDot.isSelected()) {
            // 支付寶支付
            return ALI_PAY;
        }
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ticket_pre_pay;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.TICKET_BACK);
                }
            }
        });


        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.TICKET_GO_TO_PAY);
                }
                if (!mWechatDot.isSelected() && !mAliDot.isSelected()) {
                    ShowToast.singleLong(getString(R.string.please_select_pay_type));
                    return;
                }
                mPresenter.createAndPayOrder();

            }
        });

        mRlWechatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWechatDot.setSelected(true);
                mAliDot.setSelected(false);
            }
        });

        mRlAliPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWechatDot.setSelected(false);
                mAliDot.setSelected(true);
            }
        });


    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mTvDate = view.findViewById(R.id.tv_date);
        mTvAddress = view.findViewById(R.id.tv_address);
        mTvTikcetType = view.findViewById(R.id.tv_ticket_type);
        mTvTicketFee = view.findViewById(R.id.tv_ticket_normal_fee);
        mTvTicketNum = view.findViewById(R.id.tv_ticket_normal_num);
        mTvTotalFee = view.findViewById(R.id.tv_total_fee);
        mRlWechatPay = view.findViewById(R.id.rl_wechat_pay);
        mRlAliPay = view.findViewById(R.id.rl_ali_pay);
        mWechatDot = view.findViewById(R.id.view_dot_wechat);
        mAliDot = view.findViewById(R.id.view_dot_alipay);
        mBtnConfirm = view.findViewById(R.id.btn_go_to_pay);
        mTvUpdate = view.findViewById(R.id.tv_update);
        mTvUpdate.setVisibility(View.VISIBLE);
        mIvSpecial = findMyViewId(R.id.iv_ticket_limit);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            mTicketPrice = getArguments().getInt("price");
            mTicketQuantity = getArguments().getInt("quantity");
            mTicketBean = getArguments().getParcelable("ticketBean");
            mTicketTotalFee = mTicketPrice * mTicketQuantity / 100.0f;

            TicketDetailBean.DataBean.SchedulePriceBean schedulePriceBean = mTicketBean.getSchedulePrice().get(0);
            mTicketAddress = mTicketBean.getTicket().getTLocale();
            mTicketTime = mTicketBean.getSchedulePrice().get(0).getTsTime();
            maps.put("time", mTicketTime);
            maps.put("quantity", mTicketQuantity);
            maps.put("ticketId", mTicketBean.getTicket().getTicketId());
            mTicketType = getArguments().getInt("pType");
            if (schedulePriceBean.getActivity() != null) {
                maps.put("actId", schedulePriceBean.getActivity().getActivityId());
                maps.put("actType", getArguments().getInt("pType"));
                //  設置顯示價格
            } else {
                maps.put("pType", getArguments().getInt("pType"));
                maps.put("pTypeName", getArguments().getString("pName"));
            }

            mTvDate.setText(mTicketTime);
            mTvAddress.setText(mTicketAddress);
            mTvTicketNum.setText(String.valueOf(mTicketQuantity));
            mTvTicketFee.setText(getString(R.string.price_unit) + String.valueOf(mTicketPrice / 100.0f));
            mTvTotalFee.setText(getString(R.string.price_unit) + String.valueOf(mTicketTotalFee));

            switch (mTicketType) {
                case TICKET_SINGLE_RARLY:
                    mTvTikcetType.setText(getString(R.string.early_fee));
                    break;
                case TICKET_SINGLE_RARLY_SPECIAL:
                    mTvTikcetType.setText(getString(R.string.early_fee));
                    mIvSpecial.setVisibility(View.VISIBLE);
                    break;
                case TICKET_SINGLE:
                    mTvTikcetType.setText(getString(R.string.normal_fee));
                    break;
                case TICKET_SINGLE_SPECIAL:
                    mTvTikcetType.setText(getString(R.string.normal_fee));
                    mIvSpecial.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public String getTitle() {
        return getString(R.string.order_pay);
    }

    @Override
    public Map<String, Object> getMaps() {
        return maps;
    }
}