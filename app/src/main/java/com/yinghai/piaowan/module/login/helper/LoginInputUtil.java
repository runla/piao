package com.yinghai.piaowan.module.login.helper;

import android.text.TextUtils;

/**
 * Created by chenjianrun on 2018/4/9.
 * 描述：登錄、註冊、找回密碼時對用戶的輸入進行正確性檢查
 */

public class LoginInputUtil {

    /**
     * 檢查手機號碼的正確性
     *
     * @param countryCode
     * @param telNo
     * @return 正確返回 null，錯誤則返回相對應的 errMsg
     */
    public static String checkPhone(String countryCode, String telNo) {
        if (TextUtils.isEmpty(telNo)) {
            return "請輸入手機號碼";
        }

        if (countryCode.equals("86") && telNo.length() != 11) {
            return "請輸入正確的手機號碼";
        }

        if (countryCode.equals("853") && telNo.length() != 8) {
            return "請輸入正確的手機號碼";
        }

        if (countryCode.equals("852") && telNo.length() != 8) {
            return "請輸入正確的手機號碼";
        }
        return null;
    }

    /**
     * 检测密码正确性
     *
     * @param password
     * @return
     */

    public static String checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return "請輸入密碼";
        }
        if (password.length() < 6) {
            return "請輸入 6 位以上密碼";
        }
        return null;
    }

    /**
     * 检测验证码正确性
     *
     * @param verifyCode
     * @return
     */

    public static String checkVerifyCode(String verifyCode) {
        if (TextUtils.isEmpty(verifyCode)) {
            return "請輸入密碼";
        }
        if (verifyCode.length() < 4) {
            return "請輸入完整的驗證碼";
        }
        return null;
    }

    /**
     * 检测填写的账号和密码正确性
     *
     * @param countryCode
     * @param telNo
     * @param password
     * @return
     */
    public static String checkPhoneAndPwd(String countryCode, String telNo, String password) {
        if (checkPhone(countryCode, telNo) == null) {
            return checkPassword(password);
        } else {
            return checkPhone(countryCode, telNo);
        }
    }

    /**
     * 检测填写的账号和密码,验证码的正确性
     *
     * @param countryCode
     * @param telNo
     * @param password
     * @return
     */
    public static String checkAll(String countryCode, String telNo, String verifyCode, String password) {
        if (checkPhoneAndPwd(countryCode, telNo,password) != null)  {
            return checkPhoneAndPwd(countryCode, telNo,password);
        } else if (checkVerifyCode(verifyCode) != null) {
            checkVerifyCode(verifyCode);
        }
        return null;
    }
}
