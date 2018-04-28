package com.yinghai.piaowan.module.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.Impl.BannerImageLoader;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseMvpActivity;
import com.yinghai.piaowan.bean.TicketsListBean;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.module.game.GameActivity;
import com.yinghai.piaowan.module.main.ticketlist.TicketListContract;
import com.yinghai.piaowan.module.main.ticketlist.TicketListPresenter;
import com.yinghai.piaowan.module.person.PersonActivity;
import com.yinghai.piaowan.module.shop.ShopActivity;
import com.yinghai.piaowan.module.ticket.TicketActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by：fanson
 *         Created on：2018/4/16 17:45
 *         Description：主界面
 */
public class MainActivity extends MyBaseMvpActivity<TicketListPresenter> implements TicketListContract.IView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView mIvGoDetails;
    private Banner mBanner;
    /**
     * 票务列表的封面URL
     */
    private List<String> mImgList = new ArrayList<>();
    /**
     * 票务列表的数据BeanList
     */
    private List<TicketsListBean.DataBean.TicketListBean> mTicketList;
    /**
     * 点击的票务ID
     */
    private int mClickTicketId = 0;
    //默认初始页码
    private static final int DEFUALT_PAGE = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_person:
                    overridePendingTransition(R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
                    startActivity(new Intent(MainActivity.this, PersonActivity.class));
                    return true;
                case R.id.navigation_game:
                    overridePendingTransition(R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
                    startActivity(new Intent(MainActivity.this, GameActivity.class));
                    return true;
                case R.id.navigation_shop:
                    overridePendingTransition(R.anim.fragment_slide_to_left_in, R.anim.fragment_slide_to_left_out);
                    startActivity(new Intent(MainActivity.this, ShopActivity.class));
                    return true;
                default:
                    break;
            }
            return false;
        }

    };

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);
        mIvGoDetails = findViewById(R.id.iv_go_details);
        initBanner();
    }

    /**
     *
     */
    private void initBanner() {
        mBanner = findMyViewId(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR)
                .isAutoPlay(false)
                .setBannerAnimation(Transformer.CubeOut)
                .setImageLoader(new BannerImageLoader());
    }

    /**
     * 设置Banner图片资源
     *
     * @param imgList 图片资源集合
     */
    private void setBannerData(List<String> imgList) {
        mBanner.setImages(imgList).start();
    }

    @Override
    protected void initData() {
        mPresenter.onTicketList(DEFUALT_PAGE);
    }

    @Override
    protected void listenEvent() {
        mIvGoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TicketActivity.startActivityToDetail(MainActivity.this, mClickTicketId);
            }
        });

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                TicketActivity.startActivityToDetail(MainActivity.this, mClickTicketId);
            }
        });

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position - 1 >= 0 && mTicketList.size() > position - 1) {
                    mClickTicketId = mTicketList.get(position - 1).getTicketId();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

    }

    @Override
    protected TicketListPresenter createPresenter() {
        return new TicketListPresenter(this);
    }

    @Override
    public void showTicketListSuccess(TicketsListBean.DataBean bean) {
        if (mTicketList == null) {
            mTicketList = new ArrayList<>();
        }
        mImgList.clear();
        for (TicketsListBean.DataBean.TicketListBean ticketBean : bean.getTicketList()) {
            mImgList.add(ConHttp.BASE_URL + ticketBean.getTImg());
            mTicketList.add(ticketBean);
        }
        setBannerData(mImgList);
    }

    @Override
    public void showTicketListFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
    }
}
