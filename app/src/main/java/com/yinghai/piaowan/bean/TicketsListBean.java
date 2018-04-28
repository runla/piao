package com.yinghai.piaowan.bean;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 11:32
 *         Describe：票务列表Bean
 */

public class TicketsListBean {

    /**
     * code : 1
     * msg : success
     * data : {"ticketList":[{"tBrief":"","tCity":"1","tContentType":1,"tCreateTime":null,"tDetails":"","tEndTime":"2018-04-16 14:30:00","tImg":"http:/localhost:8080/labixiaoxi","tLocX":0,"tLocY":0,"tLocale":"拱北口岸","tName":"蜡笔小新","tNotes":"","tPriceRange":"价格待定","tShelf":1,"tStartTime":"2018-04-16 14:30:00","tType":1,"tUpdateTime":null,"tVideo":"","ticketId":1}]}
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
        private List<TicketListBean> ticketList;

        public List<TicketListBean> getTicketList() {
            return ticketList;
        }

        public void setTicketList(List<TicketListBean> ticketList) {
            this.ticketList = ticketList;
        }

        public static class TicketListBean {
            /**
             * tBrief :
             * tCity : 1
             * tContentType : 1
             * tCreateTime : null
             * tDetails :
             * tEndTime : 2018-04-16 14:30:00
             * tImg : http:/localhost:8080/labixiaoxi
             * tLocX : 0
             * tLocY : 0
             * tLocale : 拱北口岸
             * tName : 蜡笔小新
             * tNotes :
             * tPriceRange : 价格待定
             * tShelf : 1
             * tStartTime : 2018-04-16 14:30:00
             * tType : 1
             * tUpdateTime : null
             * tVideo :
             * ticketId : 1
             */

            private String tBrief;
            private String tCity;
            private int tContentType;
            private Object tCreateTime;
            private String tDetails;
            private String tEndTime;
            private String tImg;
            private int tLocX;
            private int tLocY;
            private String tLocale;
            private String tName;
            private String tNotes;
            private String tPriceRange;
            private int tShelf;
            private String tStartTime;
            private int tType;
            private Object tUpdateTime;
            private String tVideo;
            private int ticketId;

            public String getTBrief() {
                return tBrief;
            }

            public void setTBrief(String tBrief) {
                this.tBrief = tBrief;
            }

            public String getTCity() {
                return tCity;
            }

            public void setTCity(String tCity) {
                this.tCity = tCity;
            }

            public int getTContentType() {
                return tContentType;
            }

            public void setTContentType(int tContentType) {
                this.tContentType = tContentType;
            }

            public Object getTCreateTime() {
                return tCreateTime;
            }

            public void setTCreateTime(Object tCreateTime) {
                this.tCreateTime = tCreateTime;
            }

            public String getTDetails() {
                return tDetails;
            }

            public void setTDetails(String tDetails) {
                this.tDetails = tDetails;
            }

            public String getTEndTime() {
                return tEndTime;
            }

            public void setTEndTime(String tEndTime) {
                this.tEndTime = tEndTime;
            }

            public String getTImg() {
                return tImg;
            }

            public void setTImg(String tImg) {
                this.tImg = tImg;
            }

            public int getTLocX() {
                return tLocX;
            }

            public void setTLocX(int tLocX) {
                this.tLocX = tLocX;
            }

            public int getTLocY() {
                return tLocY;
            }

            public void setTLocY(int tLocY) {
                this.tLocY = tLocY;
            }

            public String getTLocale() {
                return tLocale;
            }

            public void setTLocale(String tLocale) {
                this.tLocale = tLocale;
            }

            public String getTName() {
                return tName;
            }

            public void setTName(String tName) {
                this.tName = tName;
            }

            public String getTNotes() {
                return tNotes;
            }

            public void setTNotes(String tNotes) {
                this.tNotes = tNotes;
            }

            public String getTPriceRange() {
                return tPriceRange;
            }

            public void setTPriceRange(String tPriceRange) {
                this.tPriceRange = tPriceRange;
            }

            public int getTShelf() {
                return tShelf;
            }

            public void setTShelf(int tShelf) {
                this.tShelf = tShelf;
            }

            public String getTStartTime() {
                return tStartTime;
            }

            public void setTStartTime(String tStartTime) {
                this.tStartTime = tStartTime;
            }

            public int getTType() {
                return tType;
            }

            public void setTType(int tType) {
                this.tType = tType;
            }

            public Object getTUpdateTime() {
                return tUpdateTime;
            }

            public void setTUpdateTime(Object tUpdateTime) {
                this.tUpdateTime = tUpdateTime;
            }

            public String getTVideo() {
                return tVideo;
            }

            public void setTVideo(String tVideo) {
                this.tVideo = tVideo;
            }

            public int getTicketId() {
                return ticketId;
            }

            public void setTicketId(int ticketId) {
                this.ticketId = ticketId;
            }
        }
    }
}
