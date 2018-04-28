package com.yinghai.piaowan.constant;

/**
 * Created by chenjianrun on 2018/4/9.
 * 描述：fragment 和 Activity 進行交互的 code 的類
 */

public class ConFragmentResponseCode {
    /**
     * 设置toolbar右侧菜单
     */
    public static final int SHOW_TOOLBAR_RIGHT = 998;

    /**
     * 设置toolbar标题
     */
    public static final int SET_TOOLBAR_TITLE = 999;


    /**
     * fragment 中按下返回
     */
    public static final int FRAGMENT_BACK = 1000;

    /**
     * 請求註冊界面
     */
    public static final int REQUEST_REGISTER = 1001;

    /**
     * 請求賬號登錄界面
     */
    public static final int REQUEST_LOGIN = 1002;

    /**
     * 請求找回密碼界面
     */
    public static final int REQUEST_FIND_PASSWORD = 1003;

    /**
     * 註冊時，驗證碼驗證成功
     */
    public static final int VERIFY_SUCCESS_REGISTER = 1004;

    /**
     * 找回密碼驗證碼驗證成功
     */
    public static final int VERIFY_SUCCESS_FIND = 1005;

    /**
     * 註冊成功
     */
    public static final int REGISTER_SUCCESS = 1006;

    /**
     * 找回密碼成功
     */
    public static final int FIND_PASSWORD_SUCCESS = 1007;

    /**
     * 购票进入选择时间
     */
    public static final int TICKET_SELECT_TIME = 1008;

    /**
     * 購票信息確認界面
     */
    public static final int TICKET_PRE_PAY = 1009;

    /**
     * 前往付款
     */
    public static final int TICKET_GO_TO_PAY = 1010;

    /**
     * 票務中的返回
     */
    public static final int TICKET_BACK = 1011;

    /**
     * 顯示 loading
     */
    public static final int SHOW_LOADING = 1012;

    /**
     * 隱藏 loading
     */
    public static final int HIDE_LOADING = 1013;

    /**
     * 通知Activity申请权限
     */
    public static final int APPLY_PERMISSION = 1014;
    /*
     * 显示我的订单
     */
    public static final int PERSON_MY_ORDER = 1014;

    /**
     * 我的地址
     */
    public static final int PERSON_MY_ADDRESS = 1015;

    /**
     * 我的收藏
     */
    public static final int PERSON_MY_COLLECT = 1016;

    /**
     * 我的账号（账号绑定）
     */
    public static final int PERSON_MY_ACCOUNT = 1017;

    /**
     * 我的头像
     */
    public static final int PERSON_MY_HEADER = 1018;

    /**
     * 購物車
     */
    public static final int SHOP_CAR = 1019;

    /**
     * 商品詳情
     */
    public static final int SHOP_DETAILS = 1020;

    /**
     * 商品支付界面
     */
    public static final int SHOP_PAY = 1030;

    /**
     * 購物地址選擇
     */
    public static final int SHOP_SELECT_ADDRESS = 1031;

    /**
     * 商品訂單詳情頁
     */
    public static final int ORDER_SHOP_DETAILS = 1032;

    /**
     * 票務訂單詳情頁
     */
    public static final int ORDER_TICKET_DETAILS = 1033;
}
