package com.yinghai.piaowan.base;

import com.example.fansonlib.base.AppUtils;
import com.example.fansonlib.base.BasePresenterWithM;
import com.example.fansonlib.base.BaseView;
import com.example.fansonlib.base.IBaseModel;
import com.example.fansonlib.utils.ShowToast;
import com.yinghai.piaowan.R;
import com.yinghai.piaowan.constant.ConResultCode;
import com.yinghai.piaowan.wxapi.pay.WechatPay;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/2 11:39
 *         Describe：本项目的BasePresenter，带统一处理ResultCode
 */

public abstract class MyBasePresenter<M extends IBaseModel, V extends BaseView> extends BasePresenterWithM<M,V> {

    /**
     * 统一处理Result Code
     * @param code 接收到的code
     */
    protected void handleResultCode(int code){
        if (isViewAttached()){
            getBaseView().hideLoading();
        }
        switch (code){
            case ConResultCode.LOSS_PARAMS:
                ShowToast.singleShort(AppUtils.getString(R.string.loss_params));
                break;

            case ConResultCode.VERIFY_CODE_ERROR:
                ShowToast.singleShort(AppUtils.getString(R.string.loss_params));
                break;

            case ConResultCode.PASSWORD_FORMAT_ERROR:
                ShowToast.singleShort(AppUtils.getString(R.string.password_format_error));
                break;

            case ConResultCode.VERIFICATION_OVERDUE:
                ShowToast.singleShort(AppUtils.getString(R.string.verify_code_useless));
                break;

            case ConResultCode.ACCOUNT_HAS_EXIST:
                ShowToast.singleShort(AppUtils.getString(R.string.account_has_exist));
                break;

            case WechatPay.ERROR_CODE_WECHAT_NOT_EXIST:
                ShowToast.singleLong(AppUtils.getString(R.string.wechat_uninstall));
                break;

            case WechatPay.ERROR_CODE_NULL:
                ShowToast.singleLong(AppUtils.getString(R.string.wechat_param_has_null));
                break;

            case WechatPay.ERROR_CODE_CALL_PAY_FAILED:
                ShowToast.singleLong(AppUtils.getString(R.string.wechat_pay_failure));
                break;

            case WechatPay.ERROR_CODE_CALL_PAY_CANCEL:
                ShowToast.singleLong(AppUtils.getString(R.string.wechat_pay_cancel));
                break;


            default:
                ShowToast.singleShort(AppUtils.getString(R.string.result_unknown)+code);
                break;
        }
    }

}
