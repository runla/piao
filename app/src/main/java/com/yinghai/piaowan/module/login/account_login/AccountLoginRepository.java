package com.yinghai.piaowan.module.login.account_login;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.module.login.helper.SavePersonInfoManager;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 14:04
 *         Describe：账号登录Repository
 */

public class AccountLoginRepository extends BaseModel implements AccountLoginContract.IRepository{

    private IAccountLoginCallback mIAccountLoginCallback;

    @Override
    public void loginByAccount(String countryCode,String tel,String password,String deviceId,IAccountLoginCallback callback) {
        mIAccountLoginCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(7);
        maps.put("countryCode",countryCode);
        maps.put("tel",tel);
        maps.put("password", password);
        maps.put("deviceId", deviceId);
        maps.put("deviceType", Config.DEVICE_TYPE_ANDROID);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.LOGIN_BY_PASSWORD,maps, new HttpResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (mIAccountLoginCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SavePersonInfoManager.saveInfoBySp(bean.getData());
                        mIAccountLoginCallback.onAccountLoginSuccess(bean.getData());
                        break;

                    case ConResultCode.NOT_REGISTER:
                        mIAccountLoginCallback.onAccountLoginFailure(bean.getMsg());
                        break;

                    default:
                        mIAccountLoginCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mIAccountLoginCallback!=null){
                    mIAccountLoginCallback.onAccountLoginFailure(errorMsg);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIAccountLoginCallback = null;
    }
}
