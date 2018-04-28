package com.yinghai.piaowan.http;


import com.yinghai.piaowan.bean.CreateOrderBean;
import com.yinghai.piaowan.bean.LoginBean;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.bean.TicketsListBean;
import com.yinghai.piaowan.bean.VerifyCodeBean;
import com.yinghai.piaowan.bean.WechatPayBean;
import com.yinghai.piaowan.constant.ConHttp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:35
 * Describe：网络请求的API列表
 */

public interface ApiStores {

    /**
     * baseUrl
     */
    String API_SERVER_URL = ConHttp.BASE_URL;

    /**
     * 获取验证码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<VerifyCodeBean> getVerifyCode(@Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 账号注册
     * @param sessionId 后台需要
     * @param url URL
     * @param maps 参数
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<LoginBean> register(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 密码登录
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<LoginBean> loginByPassword(@Header("Cookie") String sessionId,  @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 找回密码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<LoginBean> findPassword(@Header("Cookie") String sessionId,  @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 手机验证码登录
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<LoginBean> loginByVerifyCode(@Header("Cookie") String sessionId,  @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取票务列表
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<TicketsListBean> getTicketList(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取票务详情
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<TicketDetailBean> getTicketDetails(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 下单
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CreateOrderBean> createOrder(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 微信支付
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<WechatPayBean> wechatPay(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);


}
