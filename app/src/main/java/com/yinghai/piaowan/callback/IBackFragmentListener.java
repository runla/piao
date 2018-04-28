package com.yinghai.piaowan.callback;

import android.support.v4.app.Fragment;

/**
 * Created by：fanson
 * Created on：2017/10/11 12:00
 * Describe：监听在fragment点击返回键的接口
 */

public interface IBackFragmentListener {

    void currentFragmentBack(Fragment fragment);
}
