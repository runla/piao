package com.yinghai.piaowan.module.ticket.pay;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.bean.CreateOrderBean;
import com.yinghai.piaowan.bean.WechatPayBean;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/25
 *         Description：票务支付
 * @Param
 */
public class PayTicketRepository extends BaseModel implements PayTicketContract.IRepository {
    private static int count = 1;
    private IPayTicketCallback mIPayTicketCallback;
    private ICreateTicketCallback mICreateTicketCallback;

    @Override
    public void createOrder(Map<String,Object> maps,ICreateTicketCallback callback) {
        mICreateTicketCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.CREATE_ORDER, maps, new HttpResponseCallback<CreateOrderBean>() {
            @Override
            public void onSuccess(CreateOrderBean createOrderBean) {
                if (createOrderBean.getCode() == ConResultCode.SUCCESS){
                    mICreateTicketCallback.onCreateTicketSuccess(createOrderBean);
                }else{
                    if (mICreateTicketCallback != null) {
                        mICreateTicketCallback.handlerResultCode(createOrderBean.getCode());
                        return;
                    }
                }
            }

            @Override
            public void onFailure(String s) {
                if (mICreateTicketCallback != null) {
                    mICreateTicketCallback.onCreateTicketFailure(s);
                }
            }
        });
    }

    @Override
    public void onWechatPayTicket(String orderNo,IPayTicketCallback callback) {
        mIPayTicketCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("orderNo",orderNo);
        maps.put("orderType",1);
        maps.put("spbillCreateIp","192.168.0.111");
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));
        HttpUtils.getHttpUtils().post(ConHttp.WECHAT_PAY,maps, new HttpResponseCallback<WechatPayBean>() {

            @Override
            public void onSuccess(WechatPayBean wechatPayBean) {
                if (mIPayTicketCallback == null) {
                    return;
                }

                if (wechatPayBean.getCode() == 1) {
                    mIPayTicketCallback.onWechatPayTicketSuccess(wechatPayBean);
                }else{
                    mIPayTicketCallback.handlerResultCode(wechatPayBean.getCode());
                }
            }

            @Override
            public void onFailure(String s) {
                if (mIPayTicketCallback != null) {
                    mIPayTicketCallback.onWechatPayTicketFailure(s);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIPayTicketCallback = null;
        mICreateTicketCallback = null;
    }

}