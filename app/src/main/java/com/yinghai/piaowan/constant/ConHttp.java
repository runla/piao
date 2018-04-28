package com.yinghai.piaowan.constant;

/**
 * Created by：fanson
 * Created on：2017/9/28 16:58
 * Describe：Http接口列表
 */

public class ConHttp {

//    public static final String BASE_URL = "http://192.168.0.110:8084/";
    public static final String BASE_URL = "http://119.28.15.112:8084/";

    /**
     * 获取验证码
     */
    public static final String GET_VERIFYCODE = "datingticket/app/user/sendVerifyCode";

    /**
     * 验证码登录
     */
    public static final String LOGIN_BY_VERIFYCODE = "datingticket/app/user/verification";

    /**
     * 密码登录
     */
    public static final String LOGIN_BY_PASSWORD = "datingticket/app/user/accountLogin";

    /**
     * 找回密码
     */
    public static final String FIND_PASSWORD = "datingticket/app/user/forgetPwd";

    /**
     * 账号注册
     */
    public static final String REGISTER = "datingticket/app/user/verificationLogin";

    /**
     * 获取票务列表
     */
    public static final String TICKET_LIST= "datingticket/app/ticket/list";

    /**
     * 获取票务详情
     */
    public static final String TICKET_DETAILS = "datingticket/app/ticket/ticketDetail";

    /**
     * 检测更新
     */
    public static final String CHECK_VERSION = "datingticket/app/user/verificationLogin";


    /**
     * 下单
     */
    public static final String CREATE_ORDER = "datingticket/app/order/generateOrder";

    /**
     * 微信支付
     */
    public static final String WECHAT_PAY = "datingticket/app/pay/wechat";


}
