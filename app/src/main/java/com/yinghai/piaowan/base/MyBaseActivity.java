package com.yinghai.piaowan.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.example.fansonlib.base.BaseActivity;
import com.example.fansonlib.utils.StatusBarUtil;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.callback.IBackFragmentListener;
import com.yinghai.piaowan.callback.OnFragmentListener;
import com.yinghai.piaowan.constant.BroadCastAction;
import com.yinghai.piaowan.constant.ConFragmentResponseCode;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.service.CheckVersionService;
import com.yinghai.piaowan.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by：fanson
 * Created on：2017/10/12 18:14
 * Describe：本项目的BaseActivity
 */

public abstract class MyBaseActivity extends BaseActivity implements OnFragmentListener,IBackFragmentListener {

    private static final String TAG = MyBaseActivity.class.getSimpleName();
    /**
     * 记录当前Activity的Fragment
     */
    public List<Fragment> mCurrentFragmentList;
    /**
     * 現在依賴的 fragment
     */
    public Fragment mCurrentFragment;

    private LoadingDialog mLoadingDialog;
    private CheckVersionReceiver mCheckVersionReceiver;
    String mUpdateUrl = ConHttp.BASE_URL + "/twentyfour/qRCode/downapk?realm=master";

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this, R.color.md_grey_100_color_code));
        mCurrentFragmentList = new ArrayList<>();
        initReceiver();
    }

    @Override
    public void onBackPressed() {
        if ((getSupportFragmentManager().getBackStackEntryCount() > 1 && (mCurrentFragmentList.size() > getSupportFragmentManager().getBackStackEntryCount() - 1))) {
            mCurrentFragmentList.remove((getSupportFragmentManager().getBackStackEntryCount() - 1));
            getSupportFragmentManager().popBackStack();
        } else {
            this.finish();
            mCurrentFragmentList.clear();
        }
    }

    /**
     * 返回当前栈顶的Fragment
     *
     * @return 栈顶的Fragment
     */
    public Fragment getCurrentFragment() {
        if (mCurrentFragmentList != null && ((getSupportFragmentManager().getBackStackEntryCount() - 1) < mCurrentFragmentList.size())) {
            return mCurrentFragmentList.get((getSupportFragmentManager().getBackStackEntryCount() - 1));
        }
        return null;
    }

    @Override
    public void currentFragmentBack(Fragment fragment) {
        mCurrentFragment = fragment;
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
        mLoadingDialog.show(getSupportFragmentManager());
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            try {
                mLoadingDialog.dismiss();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            mLoadingDialog = null;
        }
    }

    /**
     * 注册接收检测更新版本号的广播
     */
    private void initReceiver() {
        mCheckVersionReceiver = new CheckVersionReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadCastAction.CHECK_VERSION);
        registerReceiver(mCheckVersionReceiver, filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCurrentFragmentList != null) {
            mCurrentFragmentList.clear();
            mCurrentFragmentList = null;
        }
        //注销广播
        if (mCheckVersionReceiver != null) {
            try {
                unregisterReceiver(mCheckVersionReceiver);
            } catch (IllegalArgumentException ex) {
                if (ex.getMessage().contains("Receiver not registered")) {
                    //Ignore this exception
                } else {
                    throw ex;
                }
            }
        }
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

    /**
     * 接收到版本更新的广播
     */
    private class CheckVersionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            String versionDescribe = intent.getStringExtra(CheckVersionService.KEY_VERSION_DESCRIBE);
            boolean isForceUpdate = intent.getBooleanExtra(CheckVersionService.KEY_FORCE_UPDATE, false);

            DownloadBuilder builder = AllenVersionChecker
                    .getInstance()
                    .downloadOnly(crateUIData(versionDescribe));
            if (isForceUpdate) {
                builder.setForceUpdateListener(new ForceUpdateListener() {
                    @Override
                    public void onShouldForceUpdate() {
                        log.e(TAG,"need force update");
                    }
                });
            }
            builder.excuteMission(context);
        }
    }

    /**
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData(String versionDescribe) {
        UIData uiData = UIData.create();
        uiData.setTitle(getString(R.string.find_latest_version));
        uiData.setDownloadUrl(mUpdateUrl);
        uiData.setContent(versionDescribe);
        return uiData;
    }

    protected void switchFragmentWithAnim(int idContent, Fragment fromFragment, Fragment toFragment, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if(toFragment.isAdded()) {
            transaction.hide(fromFragment).setCustomAnimations(enter, exit,popEnter,popExit).show(toFragment).commitAllowingStateLoss();
        } else {
            transaction.setCustomAnimations(enter, exit,popEnter,popExit).add(idContent, toFragment).hide(fromFragment).addToBackStack((String)null).commitAllowingStateLoss();
        }

//        FragmentTransaction transaction2 = this.getSupportFragmentManager.beginTransaction();
//        transaction2.setCustomAnimations(enter, exit);
//        transaction2.replace(id_content, fragment);
//        transaction2.commitAllowingStateLoss();

    }


}
