package com.yinghai.piaowan.module.login.register;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.yinghai.piaowan.bean.VerifyCodeBean;
import com.yinghai.piaowan.callback.IHandleCodeCallback;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.Config;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 9:50
 *         Describe：获取验证码
 */

public class GetVerifyCodeRepository extends BaseModel {

    private IVerifyCodeCallback mIVerifyCodeCallback;
    /**
     * 验证码类别：登录或注册
     */
    public static final int CODE_TYPE_LOGIN = 1;
    /**
     * 验证码类别：找回密码
     */
    public static final int CODE_TYPE_FIND_PWD= 2;
    /**
     * 验证码类别：绑定微信
     */
    public static final int CODE_TYPE_BIND_WECHAT= 3;
    /**
     * 验证码类别：绑定FaceBook
     */
    public static final int CODE_TYPE_BIND_FACEBOOK= 4;

    /**
     * 获取验证码网络层
     * @param countryCode 区号
     * @param tel 号码
     * @param type  验证码类别
     * @param deviceId 设备ID
     * @param callback 回调
     */
    public void getVerifyCode(String countryCode, String tel, int type, String deviceId,IVerifyCodeCallback callback){
        mIVerifyCodeCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String, Object> maps = new HashMap<>(7);
        maps.put("countryCode", countryCode);
        maps.put("tel", tel);
        maps.put("type", type);
        maps.put("deviceId", deviceId);
        maps.put("deviceType", Config.DEVICE_TYPE_ANDROID);
        maps.put("apiSendTime", time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.GET_VERIFYCODE, maps, new HttpResponseCallback<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (mIVerifyCodeCallback == null) {
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mIVerifyCodeCallback.onGetVerifyCodeSuccess(bean.getData());
                        break;

                    default:
                        mIVerifyCodeCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mIVerifyCodeCallback != null) {
                    mIVerifyCodeCallback.onGetVerifyCodeFailure(errorMsg);
                }
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIVerifyCodeCallback = null;
    }

    public interface IVerifyCodeCallback extends IHandleCodeCallback{
        /**
         * 获取成功
         * @param bean 返回的数据
         */
        void onGetVerifyCodeSuccess(VerifyCodeBean.DataBean bean);

        /**
         * 获取失败
         * @param errorMsg 失败信息
         */
        void onGetVerifyCodeFailure(String errorMsg);
    }

}
