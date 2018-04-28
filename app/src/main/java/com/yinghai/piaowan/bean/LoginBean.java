package com.yinghai.piaowan.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/13 13:49
 *         Describe：
 */

public class LoginBean {


    /**
     * code : 1
     * msg : 登录操作成功
     * data : {"user":{"uAreaCode":"853","uBirthday":"","uCreateTime":"2018-04-09 11:33:02","uDeleted":false,"uDeviceId":"123456","uDeviceType":2,"uHeadImg":"","uIm":"user2","uNick":"用户65696898","uPassword":"25f9e794323b453885f5181f1b624d0b","uSex":true,"uStatus":1,"uTel":"65696898","uToken":"xoEFuZiYQi7HDNK8l2lLcYyPQ+jKI/5BjXyWBHKEwneCRgAC8IOqrz2t++dcELGAdotE8EDfFc3QXw+X5X8/Cg==","uUpdateTime":"2018-04-09 11:43:39","userId":2}}
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
         * user : {"uAreaCode":"853","uBirthday":"","uCreateTime":"2018-04-09 11:33:02","uDeleted":false,"uDeviceId":"123456","uDeviceType":2,"uHeadImg":"","uIm":"user2","uNick":"用户65696898","uPassword":"25f9e794323b453885f5181f1b624d0b","uSex":true,"uStatus":1,"uTel":"65696898","uToken":"xoEFuZiYQi7HDNK8l2lLcYyPQ+jKI/5BjXyWBHKEwneCRgAC8IOqrz2t++dcELGAdotE8EDfFc3QXw+X5X8/Cg==","uUpdateTime":"2018-04-09 11:43:39","userId":2}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * uAreaCode : 853
             * uBirthday :
             * uCreateTime : 2018-04-09 11:33:02
             * uDeleted : false
             * uDeviceId : 123456
             * uDeviceType : 2
             * uHeadImg :
             * uIm : user2
             * uNick : 用户65696898
             * uPassword : 25f9e794323b453885f5181f1b624d0b
             * uSex : true
             * uStatus : 1
             * uTel : 65696898
             * uToken : xoEFuZiYQi7HDNK8l2lLcYyPQ+jKI/5BjXyWBHKEwneCRgAC8IOqrz2t++dcELGAdotE8EDfFc3QXw+X5X8/Cg==
             * uUpdateTime : 2018-04-09 11:43:39
             * userId : 2
             */

            private String uAreaCode;
            private String uBirthday;
            private String uHeadImg;
            private String uIm;
            private String uNick;
            private boolean uSex;
            private int uStatus;
            private String uTel;
            private String uToken;
            private int userId;

            public String getUAreaCode() {
                return uAreaCode;
            }

            public void setUAreaCode(String uAreaCode) {
                this.uAreaCode = uAreaCode;
            }

            public String getUBirthday() {
                return uBirthday;
            }

            public void setUBirthday(String uBirthday) {
                this.uBirthday = uBirthday;
            }

            public String getUHeadImg() {
                return uHeadImg;
            }

            public void setUHeadImg(String uHeadImg) {
                this.uHeadImg = uHeadImg;
            }

            public String getUIm() {
                return uIm;
            }

            public void setUIm(String uIm) {
                this.uIm = uIm;
            }

            public String getUNick() {
                return uNick;
            }

            public void setUNick(String uNick) {
                this.uNick = uNick;
            }

            public boolean isUSex() {
                return uSex;
            }

            public void setUSex(boolean uSex) {
                this.uSex = uSex;
            }

            public int getUStatus() {
                return uStatus;
            }

            public void setUStatus(int uStatus) {
                this.uStatus = uStatus;
            }

            public String getUTel() {
                return uTel;
            }

            public void setUTel(String uTel) {
                this.uTel = uTel;
            }

            public String getUToken() {
                return uToken;
            }

            public void setUToken(String uToken) {
                this.uToken = uToken;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
