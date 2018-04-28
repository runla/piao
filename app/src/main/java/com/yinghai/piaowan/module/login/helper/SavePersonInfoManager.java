package com.yinghai.piaowan.module.login.helper;

import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.constant.ConstantPreference;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 14:11
 *         Describe：登录成功后，保存用户信息
 */

public class SavePersonInfoManager {

    /**
     * 保存用户信息通过SharePreference
     * @param bean 登录成功后返回的Bean
     */
    public static void saveInfoBySp(LoginBean.DataBean bean){
        SharePreferenceHelper.putInt(ConstantPreference.I_USER_ID,bean.getUser().getUserId());
        SharePreferenceHelper.putString(ConstantPreference.S_USER_PHOTO,bean.getUser().getUHeadImg());
        SharePreferenceHelper.putString(ConstantPreference.S_COUNTRY_CODE,bean.getUser().getUAreaCode());
        SharePreferenceHelper.putString(ConstantPreference.S_PHONE,bean.getUser().getUTel());
        SharePreferenceHelper.putString(ConstantPreference.S_USER_NAME,bean.getUser().getUNick());
        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_SEX,bean.getUser().isUSex());
        SharePreferenceHelper.putBoolean(ConstantPreference.B_USER_LOGIN,true);
        SharePreferenceHelper.apply();
    }
}
