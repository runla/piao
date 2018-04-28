package com.yinghai.piaowan.base;

import android.support.v4.content.ContextCompat;

import com.example.fansonlib.base.BaseMvpActivity;
import com.example.fansonlib.base.BasePresenter;
import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.callback.OnFragmentListener;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.widget.LoadingDialog;

/**
 * Created by：fanson
 * Created on：2017/10/17 14:57
 * Describe：本项目的MyBaseMvpActivity
 */

public abstract class MyBaseMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> implements OnFragmentListener {

    private LoadingDialog mLoadingDialog;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
        mLoadingDialog.show(getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SampleApplicationLike.getRefWatcher().watch(this);
    }

    @Override
    public void onFragmentCallback(Object... object) {
        switch ((int) object[0]) {
            case ConFragmentResponseCode.FRAGMENT_BACK:
                onBackPressed();
                break;
            case ConFragmentResponseCode.SHOW_LOADING:
                showLoading();
                break;

            case ConFragmentResponseCode.HIDE_LOADING:
                hideLoading();
                break;
            default:
                break;
        }
    }
}
