package com.yinghai.piaowan.module.ticket.detail;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.image.ImageLoaderUtils;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseMvpFragment;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.module.shop.ShopActivity;
import com.yinghai.piaowan.utils.DimensUtils;

/**
 *
 * @author chenjianrun
 * @date 2018/4/10
 * 票务详情Fragment，显示购票按钮
 */

public class TicketDetailFragment extends MyBaseMvpFragment<TicketDetailPresenter> implements TicketDetailContract.IView {
    private Button mBtnBuyTicket;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private FrameLayout mRlDragView;
    private ImageView mIvGotoShop;
    private int mBottomSheetState = -999;
    private ImageView mIvTicketBg;
    private ImageView mIvActivityPic;
    private NestedScrollView mNestedScrollView;
    private TextView mTvActivityDescription;
    private static final String PARAM_TICKET_ID = "ticketId";
    /**
     * 购买按钮是否显示中
     */
    private boolean mBuyIsShowing = true;

    private TicketDetailBean.DataBean mTicketBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ticket_buy_new;
    }

    public static TicketDetailFragment newInstance(int ticketId) {
        Bundle args = new Bundle();
        args.putInt(PARAM_TICKET_ID,ticketId);
        TicketDetailFragment fragment = new TicketDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mBtnBuyTicket = view.findViewById(R.id.btn_buy_ticket);
        mIvGotoShop = view.findViewById(R.id.iv_goto_shop);
        mRlDragView = view.findViewById(R.id.frameLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.frameLayout));
        mIvTicketBg = findMyViewId(R.id.iv_ticket_bg);
        mIvActivityPic = findMyViewId(R.id.iv_acitivity_pic);
        mTvActivityDescription = findMyViewId(R.id.tv_activity_description);
        mNestedScrollView = findMyViewId(R.id.scrollView);

        hideFabAnim();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBottomSheetState == BottomSheetBehavior.STATE_EXPANDED) {
            mTvTitle.setVisibility(View.VISIBLE);
            mRlDragView.setBackgroundColor(ContextCompat.getColor(hostActivity, R.color.white));
        } else {
            mRlDragView.setBackground(ContextCompat.getDrawable(hostActivity, R.drawable.shape_top_bg_white));
            mTvTitle.setVisibility(View.GONE);
        }
    }

    @Override
    protected TicketDetailPresenter createPresenter() {
        return new TicketDetailPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        if (getArguments()!=null){
            mPresenter.onTicketDetail(getArguments().getInt(PARAM_TICKET_ID));
        }

        mTvTitle.setVisibility(View.GONE);
        mIvGotoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hostActivity.startActivity(new Intent(hostActivity, ShopActivity.class));
                hostActivity.overridePendingTransition(R.anim.fragment_slide_to_left_in,R.anim.fragment_slide_to_left_out);
            }
        });

        mBtnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentListener != null) {
                    mFragmentListener.onFragmentCallback(ConFragmentResponseCode.TICKET_SELECT_TIME,mTicketBean);
                }
            }
        });


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                mBottomSheetState = newState;
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    mTvTitle.setVisibility(View.VISIBLE);
                    mRlDragView.setBackgroundColor(ContextCompat.getColor(hostActivity, R.color.white));
                } else {
                    mRlDragView.setBackground(ContextCompat.getDrawable(hostActivity, R.drawable.shape_top_bg_white));
                    mTvTitle.setVisibility(View.GONE);
                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    //  默认的折叠状态
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    //  完全展開狀態
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    //   过渡状态，拖動中
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    //  视图从脱离手指自由滑动到最终停下的这一小段时间
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    default:
                        break;
                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        //滚动监听，隐藏评论按钮，防止按钮遮挡住“购买”的按钮
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // 向下滑动
                    hideFabAnim();
                }

                if (scrollY < oldScrollY) {
                    // 向上滑动
                    showFabAnim();
                }

                if (scrollY == 0) {
                    // 顶部
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 底部
                }
            }
        });
    }

    /**
     * 隐藏评论按钮的动画
     */
    private void hideFabAnim() {
        if (mBuyIsShowing) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mBtnBuyTicket, "translationY", 0, DimensUtils.dipToPx(hostActivity, 60));
            animator.setDuration(200);
            animator.start();
            mBuyIsShowing = false;
        }

    }

    /**
     * 显示评论按钮的动画
     */
    private void showFabAnim() {
        if (!mBuyIsShowing && mTicketBean != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mBtnBuyTicket, "translationY", DimensUtils.dipToPx(hostActivity, 60), 0);
            animator.setDuration(200);
            animator.start();
            mBuyIsShowing = true;
        }
    }

    @Override
    public String getTitle() {
        return getString(R.string.buy_ticket);
    }


    @Override
    public void showTicketDetailSuccess(TicketDetailBean.DataBean bean) {
        this.mTicketBean = bean;
        showFabAnim();
        ImageLoaderUtils.loadImage(hostActivity,mIvTicketBg, ConHttp.BASE_URL+bean.getTicket().getTImg());
        ImageLoaderUtils.loadImage(hostActivity,mIvActivityPic,ConHttp.BASE_URL+bean.getTicket().getTImg());
        mTvActivityDescription.setText(Html.fromHtml(bean.getTicket().getTDetails()));
    }

    @Override
    public void showTicketDetailFailure(String errorMsg) {
        ShowToast.singleLong(errorMsg);
    }
}
