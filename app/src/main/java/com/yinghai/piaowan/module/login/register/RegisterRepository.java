package com.yinghai.piaowan.module.login.register;

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
 *         Created Time: 2018/4/16 9:33
 *         Describe：注册的Repository
 */

public class RegisterRepository extends BaseModel implements RegisterContract.IModel {

    private IRegisterCallback mIRegisterCallback;

    @Override
    public void register(String countryCode, String tel,String verifyCode, String password, String deviceId, IRegisterCallback callback) {
        mIRegisterCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(8);
        maps.put("countryCode", countryCode);
        maps.put("tel", tel);
        maps.put("verification", verifyCode);
        maps.put("password", password);
        maps.put("deviceId", deviceId);
        maps.put("deviceType", Config.DEVICE_TYPE_ANDROID);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.REGISTER, maps, new HttpResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (mIRegisterCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        SavePersonInfoManager.saveInfoBySp(bean.getData());
                        mIRegisterCallback.onRegisterSuccess(bean.getData());
                        break;

                    default:
                        mIRegisterCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mIRegisterCallback != null) {
                    mIRegisterCallback.onRegisterFailure(errorMsg);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIRegisterCallback = null;
    }
}
