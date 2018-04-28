package com.yinghai.piaowan.module.main.ticketlist;

import com.example.fansonlib.base.BaseModel;
import com.example.fansonlib.http.HttpResponseCallback;
import com.example.fansonlib.http.HttpUtils;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.piaowan.bean.TicketsListBean;
import com.yinghai.piaowan.constant.ConHttp;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.constant.ConstantPreference;
import com.yinghai.piaowan.utils.ValidateAPITokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 11:35
 *         Describe：获取票务列表Repository
 */
public class TicketListRepository extends BaseModel implements TicketListContract.IRepository {

    private ITicketListCallback mITicketListCallback;
    private static final int PAGE_SIZE = 5;
    private static final int TICKET_TYPE = 1;

    @Override
    public void onTicketList(int page,ITicketListCallback callback) {
        mITicketListCallback = callback;
        String time = String.valueOf(System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>(6);
        maps.put("userId", SharePreferenceHelper.getInt(ConstantPreference.I_USER_ID,-1));
        maps.put("contentType",TICKET_TYPE);
        maps.put("page",page);
        maps.put("pageSize",PAGE_SIZE);
        maps.put("apiSendTime",time);
        maps.put("apiToken", ValidateAPITokenUtil.ctreatTokenStringByTimeString(time));

        HttpUtils.getHttpUtils().post(ConHttp.TICKET_LIST,maps, new HttpResponseCallback<TicketsListBean>() {
            @Override
            public void onSuccess(TicketsListBean bean) {
                if (mITicketListCallback ==null){
                    return;
                }
                switch (bean.getCode()) {
                    case ConResultCode.SUCCESS:
                        mITicketListCallback.onTicketListSuccess(bean.getData());
                        break;

                    default:
                        mITicketListCallback.handlerResultCode(bean.getCode());
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (mITicketListCallback!=null){
                    mITicketListCallback.onTicketListFailure(errorMsg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mITicketListCallback = null;
    }

}