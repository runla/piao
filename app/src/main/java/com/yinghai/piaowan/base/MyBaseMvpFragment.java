package com.yinghai.piaowan.base;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.base.BaseMvpFragment;
import com.example.fansonlib.base.BasePresenter;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.callback.IBackFragmentListener;
import com.yinghai.piaowan.callback.OnFragmentListener;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.manager.MyFragmentManager;

/**
 * @author Created by：fanson
 *         Created on：2017/10/13 13:18
 *         Describe：本项目的BaseMvpFragment
 */

public abstract class MyBaseMvpFragment<P extends BasePresenter> extends BaseMvpFragment<P> implements MyFragmentManager.OnBackClickListener{

    public OnFragmentListener mFragmentListener;
    protected ImageView mIvBack;
    protected TextView mTvTitle;
    protected TextView mTvRightTitle;
    private IBackFragmentListener mIBackFragmentListener;

    @Override
    public void onStart() {
        super.onStart();
        mIBackFragmentListener.currentFragmentBack(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        SampleApplicationLike.getRefWatcher().watch(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
        mIBackFragmentListener = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentListener = (OnFragmentListener) activity;
        mIBackFragmentListener = (IBackFragmentListener) hostActivity;
    }

    @Override
    protected void listenEvent() {
        super.listenEvent();

        if (mIvBack != null) {
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFragmentListener != null) {
                        mFragmentListener.onFragmentCallback(ConFragmentResponseCode.FRAGMENT_BACK);
                    }
                }
            });
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mIvBack = view.findViewById(R.id.iv_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvRightTitle = view.findViewById(R.id.tv_update);
        return rootView;
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(getTitle()) && mTvTitle != null) {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(getTitle());
        }

        if (!TextUtils.isEmpty(getRightTitle()) && mTvRightTitle != null) {
            mTvRightTitle.setVisibility(View.VISIBLE);
            mTvRightTitle.setText(getTitle());
        }
    }

    /**
     * 設置標題
     *
     * @return
     */
    public abstract String getTitle();

    /**
     * toolbar 右边的标题
     *
     * @return
     */
    public String getRightTitle() {
        return "";
    }

    @Override
    public void showLoading() {
        if (mFragmentListener != null) {
            mFragmentListener.onFragmentCallback(ConFragmentResponseCode.SHOW_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mFragmentListener != null) {
            mFragmentListener.onFragmentCallback(ConFragmentResponseCode.HIDE_LOADING);
        }
    }

    @Override
    public boolean onBackClick() {
        if (getFragmentManager()==null){
            return false;
        }{
            return MyFragmentManager.popTopFragment(getFragmentManager());
        }
    }
}
