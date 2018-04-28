package com.yinghai.piaowan.module.person.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.base.MyBaseFragment;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.widget.MyRippleDrawable;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/13
 *         Description：${VULUES}
 * @Param
 */

public class PersonFragment extends MyBaseFragment implements View.OnClickListener {
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvPoint;
    private TextView mTvOrder;
    private TextView mTvCollection;
    private TextView mTvAddress;
    private TextView mTvBindAccount;
    private CircleImageView mIvHeader;
    private ImageView mIvBg;
    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            mIvBg.setImageDrawable(new MyRippleDrawable(bitmap, mIvBg));
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        super.initView(view, bundle);
        mTvName = view.findViewById(R.id.tv_name);
        mTvPhone = view.findViewById(R.id.tv_phone);
        mTvPoint = view.findViewById(R.id.tv_point);
        mTvOrder = view.findViewById(R.id.tv_my_order);
        mTvCollection = view.findViewById(R.id.tv_my_collect);
        mTvAddress = view.findViewById(R.id.tv_my_address);
        mTvBindAccount = view.findViewById(R.id.tv_my_account);
        mIvHeader = view.findViewById(R.id.circleImageView);
        mTvTitle.setTextColor(ContextCompat.getColor(hostActivity,R.color.black));
        mIvBg = view.findViewById(R.id.iv_bg);
        Glide.with(this).load(R.mipmap.pic_person_bg).asBitmap().into(target);

        return view;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();
        mTvOrder.setOnClickListener(this);
        mTvCollection.setOnClickListener(this);
        mTvAddress.setOnClickListener(this);
        mTvBindAccount.setOnClickListener(this);
        mIvHeader.setOnClickListener(this);

    }

    @Override
    public String getTitle() {
        return getString(R.string.person_info);
    }

    @Override
    public void onClick(View view) {
        if (mFragmentListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_my_order:
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_ORDER);
                break;

            case R.id.tv_my_address:
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_ADDRESS);
                break;

            case R.id.tv_my_collect:
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_COLLECT);
                break;

            case R.id.circleImageView:
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_HEADER);
                break;

            case R.id.tv_my_account:
                mFragmentListener.onFragmentCallback(ConFragmentResponseCode.PERSON_MY_ACCOUNT);
                break;

            default:
                break;
        }
    }

    private void startAnim() {
        mTvName.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvPhone.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvPoint.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvOrder.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvAddress.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvCollection.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
        mTvBindAccount.startAnimation(AnimationUtils.loadAnimation(hostActivity, R.anim.item_slide_to_left));
    }
}
