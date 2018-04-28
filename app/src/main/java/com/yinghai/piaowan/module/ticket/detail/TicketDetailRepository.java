package com.yinghai.piaowan.module.ticket.detail;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.bean.TicketDetailBean;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 14:14
 *         Describe：票务详情Repository
 */
public class TicketDetailRepository extends BaseModel implements TicketDetailContract.IRepository {

    private ITicketDetailCallback mITicketDetailCallback;

    @Override
    public void onTicketDetail(int ticketId,ITicketDetailCallback callback) {
        mITicketDetailCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(4);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("ticketId",ticketId);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.TICKET_DETAILS,maps, new HttpResponseCallback<TicketDetailBean>() {
            @Override
            public void onSuccess(TicketDetailBean bean) {
                if (mITicketDetailCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mITicketDetailCallback.onTicketDetailSuccess(bean.getData());
                        break;

                    default:
                        mITicketDetailCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mITicketDetailCallback!=null){
                    mITicketDetailCallback.onTicketDetailFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mITicketDetailCallback = null;
    }

}