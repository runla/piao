package com.yinghai.piaowan.module.ticket.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.widget.DrawableTextView;
import com.yinghai.piaowan.widget.LikeShape;
import com.yinghai.piaowan.widget.NumberCounterView;

/**
 * 选择购票类型及购票数量的 fragment
 */

public class SelectTicketFragment extends MyBaseFragment {

    public static final int SINGLE_TYPE = 0;    //  單人票類型
    public static final int MULT_TYPE = 1;      //  多人票類型
    public static final int TICKET_SINGLE = 1;                  //  单人正价票
    public static final int TICKET_SINGLE_RARLY = 3;            //  单人早鸟票
    public static final int TICKET_SINGLE_SPECIAL = 4;          //  单人正价限定票
    public static final int TICKET_SINGLE_RARLY_SPECIAL = 5;    //  单人早鸟限定票
    private static final int PERIOD_IN_ACTIVITY = 1;            //  活动期(处于正常售票时间)
    private static final int PERIOD_IN_ACTIVITY_NO_TICKET = 2;  //  活动期(不在正常售票时间)
    private static final int PERIOD_NORMAL = 3;                 //  正常售票时间
    private static final int PERIOD_EARLY = 4;                  //  预售时间
    private static final int PERIOD_NO_TICKET = 5;              //  非售票时间

    private Button mBtnConfirm;
    private RelativeLayout mRlTicketSingle;
    private DrawableTextView mTvTicketSingle;
    private LikeShape mLikeTicketSingle;
    private LinearLayout mLlSingleNormal;               //  单人正常票
    private TextView mTvSingleNormalLinePrice;          //  单人正常划线价
    private TextView mTvSingleNormalPrice;              //  单人正常购买价
    private NumberCounterView mCountSingleNormalNum;    //  单人正常购买数量
    private LinearLayout mLlSingleSpecial;              //  单人限定票
    private TextView mTvSingleSpecialLinePrice;         //  单人限定划线价
    private TextView mTvSingleSpecialPrice;             //  单人限定购买价


    private NumberCounterView mCountSingleSpecialNum;   //  单人限定购买数量


    private ImageView mIvTicketType1;
    private ImageView mIvTicketType2;

    private TicketDetailBean.DataBean mTicketBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ticket_time;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mBtnConfirm = view.findViewById(R.id.btn_confirm_buy);


        mRlTicketSingle = view.findViewById(R.id.rl_single_ticket);
        mTvTicketSingle = view.findViewById(R.id.tv_single);
        mLikeTicketSingle = view.findViewById(R.id.like_single);
        mTvTicketSingle.setSelected(false);
        mLikeTicketSingle.setSelected(false);

        mLlSingleNormal = findMyViewId(R.id.linear_single_normal);
        mTvSingleNormalLinePrice = mLlSingleNormal.findViewById(R.id.tv_discount_price);
        mTvSingleNormalPrice = mLlSingleNormal.findViewById(R.id.tv_norma_price);
        mCountSingleNormalNum = mLlSingleNormal.findViewById(R.id.num_counter_view);
        mIvTicketType1 = mLlSingleNormal.findViewById(R.id.iv_ticket_type);
        mTvSingleNormalLinePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mLlSingleSpecial = findMyViewId(R.id.linear_single_special);
        mTvSingleSpecialLinePrice = mLlSingleSpecial.findViewById(R.id.tv_discount_price);
        mTvSingleSpecialPrice = mLlSingleSpecial.findViewById(R.id.tv_norma_price);
        mCountSingleSpecialNum = mLlSingleSpecial.findViewById(R.id.num_counter_view);
        mIvTicketType2 = mLlSingleSpecial.findViewById(R.id.iv_ticket_type);
        mTvSingleSpecialLinePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mTvTicketSingle.setSelected(true);
        mLikeTicketSingle.setSelected(true);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            mTicketBean = getArguments().getParcelable("ticketBean");
        }
        if (mTicketBean != null
                && mTicketBean.getSchedulePrice() != null
                && mTicketBean.getSchedulePrice().size() != 0) {
            setTicketBg();
            setTicketPrice();
            showTicketInfo();
        }
    }

    /**
     * 设置票价类型 icon
     */
    private void setTicketBg() {
        if (mTicketBean.getPeriod() == PERIOD_NO_TICKET
                || mTicketBean.getPeriod() == PERIOD_IN_ACTIVITY_NO_TICKET) {
            // 不售票

        } else if (mTicketBean.getPeriod() == PERIOD_EARLY) {
            // 早鸟票
            mIvTicketType1.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_early_white));
            mIvTicketType2.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_early_white));

        } else if (mTicketBean.getPeriod() == PERIOD_IN_ACTIVITY) {
            //  秒杀活动票
            mIvTicketType1.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_activity_black));
            mIvTicketType2.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_activity_black));
        } else {
            //  正常票
            mIvTicketType1.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_normal_white));
            mIvTicketType2.setImageDrawable(ContextCompat.getDrawable(hostActivity, R.mipmap.pic_ticket_type_normal_white));
        }
    }

    /**
     * 设置票的价格
     */
    private void setTicketPrice() {
        // 不售票
        if (mTicketBean.getPeriod() == PERIOD_NO_TICKET
                || mTicketBean.getPeriod() == PERIOD_IN_ACTIVITY_NO_TICKET) {
            return;
        }

        // 秒杀活动
        if (mTicketBean.getPeriod() == PERIOD_IN_ACTIVITY) {
            mLlSingleNormal.setTag(mTicketBean.getSchedulePrice().get(0).getActivity().getActPrice());
            mIvTicketType1.setTag(TICKET_SINGLE);
            mIvTicketType2.setTag(TICKET_SINGLE_SPECIAL);
            mLlSingleSpecial.setTag(mTicketBean.getSchedulePrice().get(0).getActivity().getActSpecialPrice());
            mTvSingleNormalPrice.setText(getString(R.string.price_unit) + mTicketBean.getSchedulePrice().get(0).getActivity().getActPrice());
            mTvSingleSpecialPrice.setText(getString(R.string.price_unit) + mTicketBean.getSchedulePrice().get(0).getActivity().getActSpecialPrice());
            return;
        }

        // 預售期间
        if (mTicketBean.getPeriod() == PERIOD_EARLY) {
            for (TicketDetailBean.DataBean.SchedulePriceBean.PricesBean pricesBean : mTicketBean.getSchedulePrice().get(0).getPrices()) {
                if (pricesBean.getPType() == TICKET_SINGLE_RARLY) {
                    mIvTicketType1.setTag(pricesBean.getPType());
                    mLlSingleNormal.setTag(pricesBean.getPPrice());
                    mTvSingleNormalPrice.setText(getString(R.string.price_unit) + pricesBean.getPPrice() / 100.0f);
                    mTvSingleNormalLinePrice.setText(getString(R.string.price_unit) + getOriginalPrice(TICKET_SINGLE));
                    mCountSingleNormalNum.setTag(pricesBean.getPTypeName());
                } else if (pricesBean.getPType() == TICKET_SINGLE_RARLY_SPECIAL) {
                    mLlSingleSpecial.setTag(pricesBean.getPPrice());
                    mIvTicketType2.setTag(pricesBean.getPType());
                    mTvSingleSpecialPrice.setText(getString(R.string.price_unit) + pricesBean.getPPrice() / 100.0f);
                    mTvSingleSpecialLinePrice.setText(getString(R.string.price_unit) + getOriginalPrice(TICKET_SINGLE_SPECIAL));
                    mCountSingleSpecialNum.setTag(pricesBean.getPTypeName());
                }
            }
            return;
        }

        // 正常价期间
        if (mTicketBean.getPeriod() == PERIOD_NORMAL) {
            for (TicketDetailBean.DataBean.SchedulePriceBean.PricesBean pricesBean : mTicketBean.getSchedulePrice().get(0).getPrices()) {
                if (pricesBean.getPType() == TICKET_SINGLE) {
                    mIvTicketType1.setTag(pricesBean.getPType());
                    mTvSingleNormalPrice.setText(getString(R.string.price_unit) + pricesBean.getPPrice()/ 100.0f);
                    mLlSingleNormal.setTag(pricesBean.getPPrice());
                    mCountSingleNormalNum.setTag(pricesBean.getPTypeName());
                } else if (pricesBean.getPType() == TICKET_SINGLE_SPECIAL) {
                    mIvTicketType2.setTag(pricesBean.getPType());
                    mTvSingleSpecialPrice.setText(getString(R.string.price_unit) + pricesBean.getPPrice()/ 100.0f);
                    mLlSingleSpecial.setTag(pricesBean.getPPrice());
                    mCountSingleSpecialNum.setTag(pricesBean.getPTypeName());
                }
            }
            return;
        }

    }

    /**
     * 默认所有的票都是已售完的，有票则才需要显示
     */
    private void showTicketInfo() {
        for (TicketDetailBean.DataBean.SchedulePriceBean.PricesBean pricesBean : mTicketBean.getSchedulePrice().get(0).getPrices()) {
            if (pricesBean.getPType() == TICKET_SINGLE_RARLY || pricesBean.getPType() == TICKET_SINGLE) {
                mTvSingleNormalPrice.setVisibility(View.VISIBLE);
                mCountSingleNormalNum.setVisibility(View.VISIBLE);
                mLlSingleNormal.findViewById(R.id.tv_no_stoke).setVisibility(View.GONE);
                if (mTicketBean.getPeriod() == PERIOD_EARLY) {
                    mTvSingleNormalLinePrice.setVisibility(View.VISIBLE);
                }

            } else if (pricesBean.getPType() == TICKET_SINGLE_RARLY_SPECIAL || pricesBean.getPType() == TICKET_SINGLE_SPECIAL) {
                mTvSingleSpecialPrice.setVisibility(View.VISIBLE);
                mCountSingleSpecialNum.setVisibility(View.VISIBLE);
                mLlSingleSpecial.findViewById(R.id.tv_no_stoke).setVisibility(View.GONE);
                if (mTicketBean.getPeriod() == PERIOD_EARLY) {
                    mTvSingleSpecialLinePrice.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * 获取原始价格
     *
     * @param type 票价格类型
     */
    private float getOriginalPrice(int type) {
        for (TicketDetailBean.DataBean.SchedulePriceBean.StandardBean standardBean : mTicketBean.getSchedulePrice().get(0).getStandard()) {
            if (standardBean.getPType() == type) {
                return standardBean.getPPrice() / 100.0f;
            }
        }
        return 0;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mCountSingleNormalNum.setNumChangeCallback(new NumberCounterView.INumChangeCallback() {
            @Override
            public void onNumChangeResult(int num) {
                if (num > 0) {
                    mCountSingleSpecialNum.setCurrentNum(0);
                }
            }
        });

        mCountSingleSpecialNum.setNumChangeCallback(new NumberCounterView.INumChangeCallback() {
            @Override
            public void onNumChangeResult(int num) {
                if (num > 0) {
                    mCountSingleNormalNum.setCurrentNum(0);
                }
            }
        });



        mRlTicketSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvTicketSingle.setSelected(true);
                mLikeTicketSingle.setSelected(true);
            }
        });


        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null && getPayTicketData() != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.TICKET_PRE_PAY, getPayTicketData());
                }
            }
        });

    }

    @Override
    public String getTitle() {
        return null;
    }

    /**
     * 進入訂單支付界面時，對選擇中的票類型及數量進行判斷
     */
    private Bundle getPayTicketData() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ticketBean", mTicketBean);

        if (mCountSingleSpecialNum.getCountNum() != 0) {
            bundle.putInt("quantity", mCountSingleSpecialNum.getCountNum());
            bundle.putInt("pType", (Integer) mIvTicketType2.getTag());
            bundle.putInt("price", (Integer) mLlSingleSpecial.getTag());
            bundle.putString("pName", (String) mCountSingleSpecialNum.getTag());
        } else if (mCountSingleNormalNum.getCountNum() != 0) {
            bundle.putInt("quantity", mCountSingleNormalNum.getCountNum());
            bundle.putInt("pType", (Integer) mIvTicketType1.getTag());
            bundle.putInt("price", (Integer) mLlSingleNormal.getTag());
            bundle.putString("pName", (String) mCountSingleNormalNum.getTag());
        } else {
            ShowToast.singleLong("還沒有選擇單人票的數量");
            return null;
        }
        return bundle;
    }

    /**
     * @param ticketBean
     */
    public void setTicketBean(TicketDetailBean.DataBean ticketBean) {
        mTicketBean = ticketBean;
    }
}
