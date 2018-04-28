package com.yinghai.piaowan.base;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fansonlib.base.BaseFragment;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.callback.IBackFragmentListener;
import com.yinghai.piaowan.callback.OnFragmentListener;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.manager.MyFragmentManager;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/3 18:05
 *         Describe：本项目的BaseFragment
 */

public abstract class MyBaseFragment extends BaseFragment implements MyFragmentManager.OnBackClickListener{

    public OnFragmentListener mFragmentListener;
    private IBackFragmentListener mIBackFragmentListener;
    protected ImageView mIvBack;
    protected TextView mTvTitle;
    protected TextView mTvRightTitle;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFragmentListener = (OnFragmentListener) activity;
        mIBackFragmentListener = (IBackFragmentListener) hostActivity;
    }

    @Override
    public void onStart() {
        super.onStart();
        mIBackFragmentListener.currentFragmentBack(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
        mIBackFragmentListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        SampleApplicationLike.getRefWatcher().watch(this);
    }

    @Override
    protected View initView(View view, Bundle bundle) {
        mIvBack = view.findViewById(R.id.iv_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvRightTitle = view.findViewById(R.id.tv_update);

        return null;
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(getTitle()) && mTvTitle != null) {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(getTitle());
        }

        if (!TextUtils.isEmpty(getRightTitle()) && mTvRightTitle != null) {
            mTvRightTitle.setVisibility(View.VISIBLE);
            mTvRightTitle.setText(getRightTitle());
        }

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

    /**
     * 設置標題
     * @return
     */
    public abstract String getTitle();

    /**
     * toolbar 右边的标题
     * @return
     */
    public String getRightTitle(){
        return "";
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
