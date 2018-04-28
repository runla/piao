package com.yinghai.piaowan.bean;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/25
 *         Description：下单的 bean
 * @Param
 */

public class CreateOrderBean {

    /**
     * code : 1
     * msg : success
     * data : {"ticketOrderHelper":{"order":{"oActualMoney":90,"oCancelReason":"","oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2018-04-16 18:53:28","oOrderNo":"20180416185301086197051","oPayStatus":1,"oPayTime":"","oPayType":0,"oPrice":90,"oQty":1,"oStatus":1,"oTotalMoney":90,"oType":1,"oUpdateTime":"","oUserId":4,"orderId":2},"ticket":null,"ticketOrder":{"orderTicketId":1,"otGetNo":"","otOrderId":2,"otPayNo":"","otPriceId":2,"otTicketId":51,"otTicketType":1,"otTsId":2,"otUseStatus":0}}}
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
         * ticketOrderHelper : {"order":{"oActualMoney":90,"oCancelReason":"","oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2018-04-16 18:53:28","oOrderNo":"20180416185301086197051","oPayStatus":1,"oPayTime":"","oPayType":0,"oPrice":90,"oQty":1,"oStatus":1,"oTotalMoney":90,"oType":1,"oUpdateTime":"","oUserId":4,"orderId":2},"ticket":null,"ticketOrder":{"orderTicketId":1,"otGetNo":"","otOrderId":2,"otPayNo":"","otPriceId":2,"otTicketId":51,"otTicketType":1,"otTsId":2,"otUseStatus":0}}
         */

        private TicketOrderHelperBean ticketOrderHelper;

        public TicketOrderHelperBean getTicketOrderHelper() {
            return ticketOrderHelper;
        }

        public void setTicketOrderHelper(TicketOrderHelperBean ticketOrderHelper) {
            this.ticketOrderHelper = ticketOrderHelper;
        }

        public static class TicketOrderHelperBean {
            /**
             * order : {"oActualMoney":90,"oCancelReason":"","oCancelTime":"","oCancelType":0,"oCompleteTime":"","oCreateTime":"2018-04-16 18:53:28","oOrderNo":"20180416185301086197051","oPayStatus":1,"oPayTime":"","oPayType":0,"oPrice":90,"oQty":1,"oStatus":1,"oTotalMoney":90,"oType":1,"oUpdateTime":"","oUserId":4,"orderId":2}
             * ticket : null
             * ticketOrder : {"orderTicketId":1,"otGetNo":"","otOrderId":2,"otPayNo":"","otPriceId":2,"otTicketId":51,"otTicketType":1,"otTsId":2,"otUseStatus":0}
             */

            private OrderBean order;
            private Object ticket;
            private TicketOrderBean ticketOrder;

            public OrderBean getOrder() {
                return order;
            }

            public void setOrder(OrderBean order) {
                this.order = order;
            }

            public Object getTicket() {
                return ticket;
            }

            public void setTicket(Object ticket) {
                this.ticket = ticket;
            }

            public TicketOrderBean getTicketOrder() {
                return ticketOrder;
            }

            public void setTicketOrder(TicketOrderBean ticketOrder) {
                this.ticketOrder = ticketOrder;
            }

            public static class OrderBean {
                /**
                 * oActualMoney : 90
                 * oCancelReason :
                 * oCancelTime :
                 * oCancelType : 0
                 * oCompleteTime :
                 * oCreateTime : 2018-04-16 18:53:28
                 * oOrderNo : 20180416185301086197051
                 * oPayStatus : 1
                 * oPayTime :
                 * oPayType : 0
                 * oPrice : 90
                 * oQty : 1
                 * oStatus : 1
                 * oTotalMoney : 90
                 * oType : 1
                 * oUpdateTime :
                 * oUserId : 4
                 * orderId : 2
                 */

                private int oActualMoney;
                private String oCancelReason;
                private String oCancelTime;
                private int oCancelType;
                private String oCompleteTime;
                private String oCreateTime;
                private String oOrderNo;
                private int oPayStatus;
                private String oPayTime;
                private int oPayType;
                private int oPrice;
                private int oQty;
                private int oStatus;
                private int oTotalMoney;
                private int oType;
                private String oUpdateTime;
                private int oUserId;
                private int orderId;

                public int getOActualMoney() {
                    return oActualMoney;
                }

                public void setOActualMoney(int oActualMoney) {
                    this.oActualMoney = oActualMoney;
                }

                public String getOCancelReason() {
                    return oCancelReason;
                }

                public void setOCancelReason(String oCancelReason) {
                    this.oCancelReason = oCancelReason;
                }

                public String getOCancelTime() {
                    return oCancelTime;
                }

                public void setOCancelTime(String oCancelTime) {
                    this.oCancelTime = oCancelTime;
                }

                public int getOCancelType() {
                    return oCancelType;
                }

                public void setOCancelType(int oCancelType) {
                    this.oCancelType = oCancelType;
                }

                public String getOCompleteTime() {
                    return oCompleteTime;
                }

                public void setOCompleteTime(String oCompleteTime) {
                    this.oCompleteTime = oCompleteTime;
                }

                public String getOCreateTime() {
                    return oCreateTime;
                }

                public void setOCreateTime(String oCreateTime) {
                    this.oCreateTime = oCreateTime;
                }

                public String getOOrderNo() {
                    return oOrderNo;
                }

                public void setOOrderNo(String oOrderNo) {
                    this.oOrderNo = oOrderNo;
                }

                public int getOPayStatus() {
                    return oPayStatus;
                }

                public void setOPayStatus(int oPayStatus) {
                    this.oPayStatus = oPayStatus;
                }

                public String getOPayTime() {
                    return oPayTime;
                }

                public void setOPayTime(String oPayTime) {
                    this.oPayTime = oPayTime;
                }

                public int getOPayType() {
                    return oPayType;
                }

                public void setOPayType(int oPayType) {
                    this.oPayType = oPayType;
                }

                public int getOPrice() {
                    return oPrice;
                }

                public void setOPrice(int oPrice) {
                    this.oPrice = oPrice;
                }

                public int getOQty() {
                    return oQty;
                }

                public void setOQty(int oQty) {
                    this.oQty = oQty;
                }

                public int getOStatus() {
                    return oStatus;
                }

                public void setOStatus(int oStatus) {
                    this.oStatus = oStatus;
                }

                public int getOTotalMoney() {
                    return oTotalMoney;
                }

                public void setOTotalMoney(int oTotalMoney) {
                    this.oTotalMoney = oTotalMoney;
                }

                public int getOType() {
                    return oType;
                }

                public void setOType(int oType) {
                    this.oType = oType;
                }

                public String getOUpdateTime() {
                    return oUpdateTime;
                }

                public void setOUpdateTime(String oUpdateTime) {
                    this.oUpdateTime = oUpdateTime;
                }

                public int getOUserId() {
                    return oUserId;
                }

                public void setOUserId(int oUserId) {
                    this.oUserId = oUserId;
                }

                public int getOrderId() {
                    return orderId;
                }

                public void setOrderId(int orderId) {
                    this.orderId = orderId;
                }
            }

            public static class TicketOrderBean {
                /**
                 * orderTicketId : 1
                 * otGetNo :
                 * otOrderId : 2
                 * otPayNo :
                 * otPriceId : 2
                 * otTicketId : 51
                 * otTicketType : 1
                 * otTsId : 2
                 * otUseStatus : 0
                 */

                private int orderTicketId;
                private String otGetNo;
                private int otOrderId;
                private String otPayNo;
                private int otPriceId;
                private int otTicketId;
                private int otTicketType;
                private int otTsId;
                private int otUseStatus;

                public int getOrderTicketId() {
                    return orderTicketId;
                }

                public void setOrderTicketId(int orderTicketId) {
                    this.orderTicketId = orderTicketId;
                }

                public String getOtGetNo() {
                    return otGetNo;
                }

                public void setOtGetNo(String otGetNo) {
                    this.otGetNo = otGetNo;
                }

                public int getOtOrderId() {
                    return otOrderId;
                }

                public void setOtOrderId(int otOrderId) {
                    this.otOrderId = otOrderId;
                }

                public String getOtPayNo() {
                    return otPayNo;
                }

                public void setOtPayNo(String otPayNo) {
                    this.otPayNo = otPayNo;
                }

                public int getOtPriceId() {
                    return otPriceId;
                }

                public void setOtPriceId(int otPriceId) {
                    this.otPriceId = otPriceId;
                }

                public int getOtTicketId() {
                    return otTicketId;
                }

                public void setOtTicketId(int otTicketId) {
                    this.otTicketId = otTicketId;
                }

                public int getOtTicketType() {
                    return otTicketType;
                }

                public void setOtTicketType(int otTicketType) {
                    this.otTicketType = otTicketType;
                }

                public int getOtTsId() {
                    return otTsId;
                }

                public void setOtTsId(int otTsId) {
                    this.otTsId = otTsId;
                }

                public int getOtUseStatus() {
                    return otUseStatus;
                }

                public void setOtUseStatus(int otUseStatus) {
                    this.otUseStatus = otUseStatus;
                }
            }
        }
    }
}
