package com.yinghai.piaowan.http;

import com.example.fansonlib.http.retrofit.IApiFactory;
import com.example.fansonlib.http.retrofit.RetrofitClient;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.constant.ConHttp;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:35
 * Describe：工厂生成Retrofit用的Api
 */

public class ApiFactoryImpl implements IApiFactory {

    private Flowable mFlowable;

    @Override
    public Flowable createApi(String url, Map params) {
        String sessionId = SharePreferenceHelper.getString(ConstantPreference.S_SESSION_ID, null);
        String authorization = SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null);
        switch (url) {
            case ConHttp.GET_VERIFYCODE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getVerifyCode(url, params);
                break;

            case ConHttp.REGISTER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).register("JSESSIONID=" + sessionId,url, params);
                break;

            case ConHttp.LOGIN_BY_VERIFYCODE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).loginByVerifyCode("JSESSIONID=" + sessionId,url, params);
                break;

            case ConHttp.LOGIN_BY_PASSWORD:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).loginByPassword("JSESSIONID=" + sessionId,url, params);
                break;

            case ConHttp.FIND_PASSWORD:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).findPassword("JSESSIONID=" + sessionId,url, params);
                break;

            case ConHttp.TICKET_LIST:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getTicketList(authorization,url, params);
                break;

            case ConHttp.TICKET_DETAILS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getTicketDetails(authorization,url, params);
                break;

             case ConHttp.WECHAT_PAY:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).wechatPay(authorization,url, params);
                break;

             case ConHttp.CREATE_ORDER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).createOrder(authorization,url, params);
                break;

            default:
                break;

        }
        return mFlowable;
    }
}
