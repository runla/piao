package com.yinghai.piaowan.module.oder;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseActivity;
import com.yinghai.piaowan.module.oder.adapter.OrderViewPagerAdapter;
import com.yinghai.piaowan.module.oder.fragment.ShopOrderFragment;
import com.yinghai.piaowan.module.oder.fragment.TicketOderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends MyBaseActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = findViewById(R.id.iv_back);
        mTvTitle = findViewById(R.id.tv_title);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList<>();
        list.add(new ShopOrderFragment());
        list.add(new TicketOderFragment());
        list.add(new TicketOderFragment());
        mViewPager.setAdapter(new OrderViewPagerAdapter(getSupportFragmentManager(),list));
        mTabLayout.setupWithViewPager(mViewPager);

        mTvTitle.setText(getString(R.string.my_order));
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setTextColor(ContextCompat.getColor(this,R.color.primaryBlue));

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
