package com.yinghai.piaowan.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/16 11:42
 *         Describe：获取验证码Bean
 */

public class VerifyCodeBean {


    /**
     * code : 1
     * msg : 发送验证成功
     * data : {"sessionId":"70C6EEE032F52618E9FDD5B1080D404C"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sessionId : 70C6EEE032F52618E9FDD5B1080D404C
         */

        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
