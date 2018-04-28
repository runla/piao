package com.yinghai.piaowan.module.login.forget_password;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 18:10
 *         Describe：找回密码的Repository
 */

public class ForgetPasswordRepository extends BaseModel implements ForgetPasswordContract.IRepository {

    private IFindPasswordCallback mIFindPasswordCallback;

    @Override
    public void findPassword(String countryCode, String tel, String verification, String password, String deviceId, IFindPasswordCallback callback) {
        mIFindPasswordCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(8);
        maps.put("countryCode", countryCode);
        maps.put("tel", tel);
        maps.put("verification", verification);
        maps.put("password", password);
        maps.put("deviceId", deviceId);
        maps.put("deviceType", Config.DEVICE_TYPE_ANDROID);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.FIND_PASSWORD, maps, new HttpResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (mIFindPasswordCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mIFindPasswordCallback.onFindPasswordSuccess(bean.getData());
                        break;

                    case ConResultCode.NOT_REGISTER:
                        mIFindPasswordCallback.onFindPasswordFailure(bean.getMsg());
                        break;

                    default:
                        mIFindPasswordCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mIFindPasswordCallback != null) {
                    mIFindPasswordCallback.onFindPasswordFailure(errorMsg);
                }
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIFindPasswordCallback = null;
    }
}
