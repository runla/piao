package com.yinghai.piaowan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2018/4/20 14:14
 *         Describe：票务详情Bean
 */

public class TicketDetailBean implements Parcelable {


    public static final Parcelable.Creator<TicketDetailBean> CREATOR = new Parcelable.Creator<TicketDetailBean>() {
        @Override
        public TicketDetailBean createFromParcel(Parcel source) {
            return new TicketDetailBean(source);
        }

        @Override
        public TicketDetailBean[] newArray(int size) {
            return new TicketDetailBean[size];
        }
    };
    /**
     * code : 1
     * msg : success
     * data : {"ticket":{"images":[{"iAbsolute":"http://localhost:8084/home/file/datingticket/images/ticketImageimage5.jpg","iCreateTime":"","iKeyId":3,"iType":3,"iUpdateTime":"","iUrl":"/home/file/datingticket/images/ticketImageimage5.jpg","imgId":6,"itAppPath":"","itIsUserd":0,"itSize":""}],"tBrief":"<p>珠海情侣路是珠海沿海道路<\/p>","tCity":"珠海","tContentType":1,"tCreateTime":"2018-04-24 09:31:54","tDetails":"<p>珠海情侣路是珠海的一大看点，风光无限。<\/p>","tEndTime":"2018-05-03 00:00:00","tImg":"/images/headImage3.jpg","tLocX":0,"tLocY":0,"tLocale":"珠海情侣中路","tName":"测试票1","tNotes":"<p>1. 乘4路、9路、13路、。<\/p>","tPriceRange":"100-200","tShelf":2,"tStartTime":"2018-05-01 00:00:00","tType":1,"tUpdateTime":"","tVideo":"/images/headImage3.mp4","ticketId":3},"schedulePrice":[{"activity":null,"prices":[{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":130,"pRangeId":0,"pRemark":"预售票","pTicketId":3,"pTsId":5,"pType":3,"pTypeName":"预售票","pUpdateTime":"","pUsed":1,"priceId":4,"priceRange":[]},{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":17,"pRangeId":0,"pRemark":"团体票","pTicketId":4,"pTsId":6,"pType":6,"pTypeName":"团体票","pUpdateTime":"","pUsed":1,"priceId":15,"priceRange":[{"prNumber":10,"prPrice":16,"prPriceId":15,"rangeId":4}]}],"standard":[{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":150,"pRangeId":0,"pRemark":"正价票","pTicketId":3,"pTsId":5,"pType":1,"pTypeName":"正价票","pUpdateTime":"","pUsed":1,"priceId":3,"priceRange":[]}],"tsAddress":"珠海市香洲区情侣路","tsAvailable":1,"tsCity":"珠海","tsCreateTime":"2018-04-24 09:36:19","tsEndTime":"2018-05-03 09:30:00","tsHallId":0,"tsId":5,"tsLocale":"情侣路","tsNation":"中国","tsPresaleEnd":"2018-04-28 09:35:14","tsPresaleSpecialStock":1,"tsPresaleSpecialTotal":0,"tsPresaleStart":"2018-04-24 09:35:12","tsPresaleStock":197,"tsPresaleTotal":200,"tsSaleEnd":"2018-05-02 09:35:30","tsSaleStart":"2018-04-29 09:35:24","tsSpecialStock":300,"tsSpecialTotal":300,"tsStartTime":"2018-05-01 09:00:00","tsTicketId":3,"tsTicketStock":997,"tsTicketTotal":1000,"tsTime":"2018-05-01 09:00:00","tsUpdateTime":"2018-04-24 10:49:29"}],"period":4}
     */

    private int code;
    private String msg;
    private DataBean data;

    public TicketDetailBean() {
    }

    protected TicketDetailBean(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

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
    }    @Override
    public int describeContents() {
        return 0;
    }

    public void setData(DataBean data) {
        this.data = data;
    }    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeParcelable(this.data, flags);
    }

    public static class DataBean implements Parcelable {
        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
        /**
         * ticket : {"images":[{"iAbsolute":"http://localhost:8084/home/file/datingticket/images/ticketImageimage5.jpg","iCreateTime":"","iKeyId":3,"iType":3,"iUpdateTime":"","iUrl":"/home/file/datingticket/images/ticketImageimage5.jpg","imgId":6,"itAppPath":"","itIsUserd":0,"itSize":""}],"tBrief":"<p>珠海情侣路是珠海沿海道路<\/p>","tCity":"珠海","tContentType":1,"tCreateTime":"2018-04-24 09:31:54","tDetails":"<p>珠海情侣路是珠海的一大看点，风光无限。<\/p>","tEndTime":"2018-05-03 00:00:00","tImg":"/images/headImage3.jpg","tLocX":0,"tLocY":0,"tLocale":"珠海情侣中路","tName":"测试票1","tNotes":"<p>1. 乘4路、9路、13路、。<\/p>","tPriceRange":"100-200","tShelf":2,"tStartTime":"2018-05-01 00:00:00","tType":1,"tUpdateTime":"","tVideo":"/images/headImage3.mp4","ticketId":3}
         * schedulePrice : [{"activity":null,"prices":[{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":130,"pRangeId":0,"pRemark":"预售票","pTicketId":3,"pTsId":5,"pType":3,"pTypeName":"预售票","pUpdateTime":"","pUsed":1,"priceId":4,"priceRange":[]},{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":17,"pRangeId":0,"pRemark":"团体票","pTicketId":4,"pTsId":6,"pType":6,"pTypeName":"团体票","pUpdateTime":"","pUsed":1,"priceId":15,"priceRange":[{"prNumber":10,"prPrice":16,"prPriceId":15,"rangeId":4}]}],"standard":[{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":150,"pRangeId":0,"pRemark":"正价票","pTicketId":3,"pTsId":5,"pType":1,"pTypeName":"正价票","pUpdateTime":"","pUsed":1,"priceId":3,"priceRange":[]}],"tsAddress":"珠海市香洲区情侣路","tsAvailable":1,"tsCity":"珠海","tsCreateTime":"2018-04-24 09:36:19","tsEndTime":"2018-05-03 09:30:00","tsHallId":0,"tsId":5,"tsLocale":"情侣路","tsNation":"中国","tsPresaleEnd":"2018-04-28 09:35:14","tsPresaleSpecialStock":1,"tsPresaleSpecialTotal":0,"tsPresaleStart":"2018-04-24 09:35:12","tsPresaleStock":197,"tsPresaleTotal":200,"tsSaleEnd":"2018-05-02 09:35:30","tsSaleStart":"2018-04-29 09:35:24","tsSpecialStock":300,"tsSpecialTotal":300,"tsStartTime":"2018-05-01 09:00:00","tsTicketId":3,"tsTicketStock":997,"tsTicketTotal":1000,"tsTime":"2018-05-01 09:00:00","tsUpdateTime":"2018-04-24 10:49:29"}]
         * period : 4
         */

        private TicketBean ticket;
        private int period;
        private List<SchedulePriceBean> schedulePrice;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.ticket = in.readParcelable(TicketBean.class.getClassLoader());
            this.period = in.readInt();
            this.schedulePrice = in.createTypedArrayList(SchedulePriceBean.CREATOR);
        }

        public TicketBean getTicket() {
            return ticket;
        }

        public void setTicket(TicketBean ticket) {
            this.ticket = ticket;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public List<SchedulePriceBean> getSchedulePrice() {
            return schedulePrice;
        }

        public void setSchedulePrice(List<SchedulePriceBean> schedulePrice) {
            this.schedulePrice = schedulePrice;
        }        @Override
        public int describeContents() {
            return 0;
        }

        public static class TicketBean implements Parcelable {
            /**
             * images : [{"iAbsolute":"http://localhost:8084/home/file/datingticket/images/ticketImageimage5.jpg","iCreateTime":"","iKeyId":3,"iType":3,"iUpdateTime":"","iUrl":"/home/file/datingticket/images/ticketImageimage5.jpg","imgId":6,"itAppPath":"","itIsUserd":0,"itSize":""}]
             * tBrief : <p>珠海情侣路是珠海沿海道路</p>
             * tCity : 珠海
             * tContentType : 1
             * tCreateTime : 2018-04-24 09:31:54
             * tDetails : <p>珠海情侣路是珠海的一大看点，风光无限。</p>
             * tEndTime : 2018-05-03 00:00:00
             * tImg : /images/headImage3.jpg
             * tLocX : 0
             * tLocY : 0
             * tLocale : 珠海情侣中路
             * tName : 测试票1
             * tNotes : <p>1. 乘4路、9路、13路、。</p>
             * tPriceRange : 100-200
             * tShelf : 2
             * tStartTime : 2018-05-01 00:00:00
             * tType : 1
             * tUpdateTime :
             * tVideo : /images/headImage3.mp4
             * ticketId : 3
             */

            private String tBrief;
            private String tCity;
            private int tContentType;
            private String tCreateTime;
            private String tDetails;
            private String tEndTime;
            private String tImg;
            private double tLocX;
            private double tLocY;
            private String tLocale;
            private String tName;
            private String tNotes;
            private String tPriceRange;
            private int tShelf;
            private String tStartTime;
            private int tType;
            private String tUpdateTime;
            private String tVideo;
            private int ticketId;
            private List<ImagesBean> images;

            public TicketBean() {
            }

            public double gettLocX() {
                return tLocX;
            }

            public void settLocX(double tLocX) {
                this.tLocX = tLocX;
            }

            public double gettLocY() {
                return tLocY;
            }

            public void settLocY(double tLocY) {
                this.tLocY = tLocY;
            }

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

            public String getTCreateTime() {
                return tCreateTime;
            }

            public void setTCreateTime(String tCreateTime) {
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

            public String getTUpdateTime() {
                return tUpdateTime;
            }

            public void setTUpdateTime(String tUpdateTime) {
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

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean implements Parcelable {
                /**
                 * iAbsolute : http://localhost:8084/home/file/datingticket/images/ticketImageimage5.jpg
                 * iCreateTime :
                 * iKeyId : 3
                 * iType : 3
                 * iUpdateTime :
                 * iUrl : /home/file/datingticket/images/ticketImageimage5.jpg
                 * imgId : 6
                 * itAppPath :
                 * itIsUserd : 0
                 * itSize :
                 */

                private String iAbsolute;
                private String iCreateTime;
                private int iKeyId;
                private int iType;
                private String iUpdateTime;
                private String iUrl;
                private int imgId;
                private String itAppPath;
                private int itIsUserd;
                private String itSize;

                public String getIAbsolute() {
                    return iAbsolute;
                }

                public void setIAbsolute(String iAbsolute) {
                    this.iAbsolute = iAbsolute;
                }

                public String getICreateTime() {
                    return iCreateTime;
                }

                public void setICreateTime(String iCreateTime) {
                    this.iCreateTime = iCreateTime;
                }

                public int getIKeyId() {
                    return iKeyId;
                }

                public void setIKeyId(int iKeyId) {
                    this.iKeyId = iKeyId;
                }

                public int getIType() {
                    return iType;
                }

                public void setIType(int iType) {
                    this.iType = iType;
                }

                public String getIUpdateTime() {
                    return iUpdateTime;
                }

                public void setIUpdateTime(String iUpdateTime) {
                    this.iUpdateTime = iUpdateTime;
                }

                public String getIUrl() {
                    return iUrl;
                }

                public void setIUrl(String iUrl) {
                    this.iUrl = iUrl;
                }

                public int getImgId() {
                    return imgId;
                }

                public void setImgId(int imgId) {
                    this.imgId = imgId;
                }

                public String getItAppPath() {
                    return itAppPath;
                }

                public void setItAppPath(String itAppPath) {
                    this.itAppPath = itAppPath;
                }

                public int getItIsUserd() {
                    return itIsUserd;
                }

                public void setItIsUserd(int itIsUserd) {
                    this.itIsUserd = itIsUserd;
                }

                public String getItSize() {
                    return itSize;
                }

                public void setItSize(String itSize) {
                    this.itSize = itSize;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.iAbsolute);
                    dest.writeString(this.iCreateTime);
                    dest.writeInt(this.iKeyId);
                    dest.writeInt(this.iType);
                    dest.writeString(this.iUpdateTime);
                    dest.writeString(this.iUrl);
                    dest.writeInt(this.imgId);
                    dest.writeString(this.itAppPath);
                    dest.writeInt(this.itIsUserd);
                    dest.writeString(this.itSize);
                }

                public ImagesBean() {
                }

                protected ImagesBean(Parcel in) {
                    this.iAbsolute = in.readString();
                    this.iCreateTime = in.readString();
                    this.iKeyId = in.readInt();
                    this.iType = in.readInt();
                    this.iUpdateTime = in.readString();
                    this.iUrl = in.readString();
                    this.imgId = in.readInt();
                    this.itAppPath = in.readString();
                    this.itIsUserd = in.readInt();
                    this.itSize = in.readString();
                }

                public static final Creator<ImagesBean> CREATOR = new Creator<ImagesBean>() {
                    @Override
                    public ImagesBean createFromParcel(Parcel source) {
                        return new ImagesBean(source);
                    }

                    @Override
                    public ImagesBean[] newArray(int size) {
                        return new ImagesBean[size];
                    }
                };
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.tBrief);
                dest.writeString(this.tCity);
                dest.writeInt(this.tContentType);
                dest.writeString(this.tCreateTime);
                dest.writeString(this.tDetails);
                dest.writeString(this.tEndTime);
                dest.writeString(this.tImg);
                dest.writeDouble(this.tLocX);
                dest.writeDouble(this.tLocY);
                dest.writeString(this.tLocale);
                dest.writeString(this.tName);
                dest.writeString(this.tNotes);
                dest.writeString(this.tPriceRange);
                dest.writeInt(this.tShelf);
                dest.writeString(this.tStartTime);
                dest.writeInt(this.tType);
                dest.writeString(this.tUpdateTime);
                dest.writeString(this.tVideo);
                dest.writeInt(this.ticketId);
                dest.writeTypedList(this.images);
            }

            protected TicketBean(Parcel in) {
                this.tBrief = in.readString();
                this.tCity = in.readString();
                this.tContentType = in.readInt();
                this.tCreateTime = in.readString();
                this.tDetails = in.readString();
                this.tEndTime = in.readString();
                this.tImg = in.readString();
                this.tLocX = in.readDouble();
                this.tLocY = in.readDouble();
                this.tLocale = in.readString();
                this.tName = in.readString();
                this.tNotes = in.readString();
                this.tPriceRange = in.readString();
                this.tShelf = in.readInt();
                this.tStartTime = in.readString();
                this.tType = in.readInt();
                this.tUpdateTime = in.readString();
                this.tVideo = in.readString();
                this.ticketId = in.readInt();
                this.images = in.createTypedArrayList(ImagesBean.CREATOR);
            }

            public static final Creator<TicketBean> CREATOR = new Creator<TicketBean>() {
                @Override
                public TicketBean createFromParcel(Parcel source) {
                    return new TicketBean(source);
                }

                @Override
                public TicketBean[] newArray(int size) {
                    return new TicketBean[size];
                }
            };
        }        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.ticket, flags);
            dest.writeInt(this.period);
            dest.writeTypedList(this.schedulePrice);
        }

        public static class SchedulePriceBean implements Parcelable {
            public static final Creator<SchedulePriceBean> CREATOR = new Creator<SchedulePriceBean>() {
                @Override
                public SchedulePriceBean createFromParcel(Parcel source) {
                    return new SchedulePriceBean(source);
                }

                @Override
                public SchedulePriceBean[] newArray(int size) {
                    return new SchedulePriceBean[size];
                }
            };
            /**
             * activity : null
             * prices : [{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":130,"pRangeId":0,"pRemark":"预售票","pTicketId":3,"pTsId":5,"pType":3,"pTypeName":"预售票","pUpdateTime":"","pUsed":1,"priceId":4,"priceRange":[]},{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":17,"pRangeId":0,"pRemark":"团体票","pTicketId":4,"pTsId":6,"pType":6,"pTypeName":"团体票","pUpdateTime":"","pUsed":1,"priceId":15,"priceRange":[{"prNumber":10,"prPrice":16,"prPriceId":15,"rangeId":4}]}]
             * standard : [{"pArea":0,"pCreateTime":"","pMinNumber":1,"pPrice":150,"pRangeId":0,"pRemark":"正价票","pTicketId":3,"pTsId":5,"pType":1,"pTypeName":"正价票","pUpdateTime":"","pUsed":1,"priceId":3,"priceRange":[]}]
             * tsAddress : 珠海市香洲区情侣路
             * tsAvailable : 1
             * tsCity : 珠海
             * tsCreateTime : 2018-04-24 09:36:19
             * tsEndTime : 2018-05-03 09:30:00
             * tsHallId : 0
             * tsId : 5
             * tsLocale : 情侣路
             * tsNation : 中国
             * tsPresaleEnd : 2018-04-28 09:35:14
             * tsPresaleSpecialStock : 1
             * tsPresaleSpecialTotal : 0
             * tsPresaleStart : 2018-04-24 09:35:12
             * tsPresaleStock : 197
             * tsPresaleTotal : 200
             * tsSaleEnd : 2018-05-02 09:35:30
             * tsSaleStart : 2018-04-29 09:35:24
             * tsSpecialStock : 300
             * tsSpecialTotal : 300
             * tsStartTime : 2018-05-01 09:00:00
             * tsTicketId : 3
             * tsTicketStock : 997
             * tsTicketTotal : 1000
             * tsTime : 2018-05-01 09:00:00
             * tsUpdateTime : 2018-04-24 10:49:29
             */

            private TicketActivity activity;
            private String tsAddress;
            private int tsAvailable;
            private String tsCity;
            private String tsCreateTime;
            private String tsEndTime;
            private int tsHallId;
            private int tsId;
            private String tsLocale;
            private String tsNation;
            private String tsPresaleEnd;
            private int tsPresaleSpecialStock;
            private int tsPresaleSpecialTotal;
            private String tsPresaleStart;
            private int tsPresaleStock;
            private int tsPresaleTotal;
            private String tsSaleEnd;
            private String tsSaleStart;
            private int tsSpecialStock;
            private int tsSpecialTotal;
            private String tsStartTime;
            private int tsTicketId;
            private int tsTicketStock;
            private int tsTicketTotal;
            private String tsTime;
            private String tsUpdateTime;
            private List<PricesBean> prices;
            private List<StandardBean> standard;

            public SchedulePriceBean() {
            }

            protected SchedulePriceBean(Parcel in) {
                this.activity = in.readParcelable(TicketActivity.class.getClassLoader());
                this.tsAddress = in.readString();
                this.tsAvailable = in.readInt();
                this.tsCity = in.readString();
                this.tsCreateTime = in.readString();
                this.tsEndTime = in.readString();
                this.tsHallId = in.readInt();
                this.tsId = in.readInt();
                this.tsLocale = in.readString();
                this.tsNation = in.readString();
                this.tsPresaleEnd = in.readString();
                this.tsPresaleSpecialStock = in.readInt();
                this.tsPresaleSpecialTotal = in.readInt();
                this.tsPresaleStart = in.readString();
                this.tsPresaleStock = in.readInt();
                this.tsPresaleTotal = in.readInt();
                this.tsSaleEnd = in.readString();
                this.tsSaleStart = in.readString();
                this.tsSpecialStock = in.readInt();
                this.tsSpecialTotal = in.readInt();
                this.tsStartTime = in.readString();
                this.tsTicketId = in.readInt();
                this.tsTicketStock = in.readInt();
                this.tsTicketTotal = in.readInt();
                this.tsTime = in.readString();
                this.tsUpdateTime = in.readString();
                this.prices = in.createTypedArrayList(PricesBean.CREATOR);
                this.standard = in.createTypedArrayList(StandardBean.CREATOR);
            }

            public TicketActivity getActivity() {
                return activity;
            }

            public void setActivity(TicketActivity activity) {
                this.activity = activity;
            }

            public String getTsAddress() {
                return tsAddress;
            }

            public void setTsAddress(String tsAddress) {
                this.tsAddress = tsAddress;
            }

            public int getTsAvailable() {
                return tsAvailable;
            }

            public void setTsAvailable(int tsAvailable) {
                this.tsAvailable = tsAvailable;
            }

            public String getTsCity() {
                return tsCity;
            }

            public void setTsCity(String tsCity) {
                this.tsCity = tsCity;
            }

            public String getTsCreateTime() {
                return tsCreateTime;
            }

            public void setTsCreateTime(String tsCreateTime) {
                this.tsCreateTime = tsCreateTime;
            }

            public String getTsEndTime() {
                return tsEndTime;
            }

            public void setTsEndTime(String tsEndTime) {
                this.tsEndTime = tsEndTime;
            }

            public int getTsHallId() {
                return tsHallId;
            }

            public void setTsHallId(int tsHallId) {
                this.tsHallId = tsHallId;
            }

            public int getTsId() {
                return tsId;
            }

            public void setTsId(int tsId) {
                this.tsId = tsId;
            }

            public String getTsLocale() {
                return tsLocale;
            }

            public void setTsLocale(String tsLocale) {
                this.tsLocale = tsLocale;
            }

            public String getTsNation() {
                return tsNation;
            }

            public void setTsNation(String tsNation) {
                this.tsNation = tsNation;
            }

            public String getTsPresaleEnd() {
                return tsPresaleEnd;
            }

            public void setTsPresaleEnd(String tsPresaleEnd) {
                this.tsPresaleEnd = tsPresaleEnd;
            }

            public int getTsPresaleSpecialStock() {
                return tsPresaleSpecialStock;
            }

            public void setTsPresaleSpecialStock(int tsPresaleSpecialStock) {
                this.tsPresaleSpecialStock = tsPresaleSpecialStock;
            }

            public int getTsPresaleSpecialTotal() {
                return tsPresaleSpecialTotal;
            }

            public void setTsPresaleSpecialTotal(int tsPresaleSpecialTotal) {
                this.tsPresaleSpecialTotal = tsPresaleSpecialTotal;
            }

            public String getTsPresaleStart() {
                return tsPresaleStart;
            }

            public void setTsPresaleStart(String tsPresaleStart) {
                this.tsPresaleStart = tsPresaleStart;
            }

            public int getTsPresaleStock() {
                return tsPresaleStock;
            }

            public void setTsPresaleStock(int tsPresaleStock) {
                this.tsPresaleStock = tsPresaleStock;
            }

            public int getTsPresaleTotal() {
                return tsPresaleTotal;
            }

            public void setTsPresaleTotal(int tsPresaleTotal) {
                this.tsPresaleTotal = tsPresaleTotal;
            }

            public String getTsSaleEnd() {
                return tsSaleEnd;
            }

            public void setTsSaleEnd(String tsSaleEnd) {
                this.tsSaleEnd = tsSaleEnd;
            }

            public String getTsSaleStart() {
                return tsSaleStart;
            }

            public void setTsSaleStart(String tsSaleStart) {
                this.tsSaleStart = tsSaleStart;
            }

            public int getTsSpecialStock() {
                return tsSpecialStock;
            }

            public void setTsSpecialStock(int tsSpecialStock) {
                this.tsSpecialStock = tsSpecialStock;
            }

            public int getTsSpecialTotal() {
                return tsSpecialTotal;
            }

            public void setTsSpecialTotal(int tsSpecialTotal) {
                this.tsSpecialTotal = tsSpecialTotal;
            }

            public String getTsStartTime() {
                return tsStartTime;
            }

            public void setTsStartTime(String tsStartTime) {
                this.tsStartTime = tsStartTime;
            }

            public int getTsTicketId() {
                return tsTicketId;
            }

            public void setTsTicketId(int tsTicketId) {
                this.tsTicketId = tsTicketId;
            }

            public int getTsTicketStock() {
                return tsTicketStock;
            }

            public void setTsTicketStock(int tsTicketStock) {
                this.tsTicketStock = tsTicketStock;
            }

            public int getTsTicketTotal() {
                return tsTicketTotal;
            }

            public void setTsTicketTotal(int tsTicketTotal) {
                this.tsTicketTotal = tsTicketTotal;
            }

            public String getTsTime() {
                return tsTime;
            }

            public void setTsTime(String tsTime) {
                this.tsTime = tsTime;
            }

            public String getTsUpdateTime() {
                return tsUpdateTime;
            }

            public void setTsUpdateTime(String tsUpdateTime) {
                this.tsUpdateTime = tsUpdateTime;
            }

            public List<PricesBean> getPrices() {
                return prices;
            }

            public void setPrices(List<PricesBean> prices) {
                this.prices = prices;
            }

            public List<StandardBean> getStandard() {
                return standard;
            }

            public void setStandard(List<StandardBean> standard) {
                this.standard = standard;
            }

            public static class TicketActivity implements Parcelable {

                public static final Creator<TicketActivity> CREATOR = new Creator<TicketActivity>() {
                    @Override
                    public TicketActivity createFromParcel(Parcel source) {
                        return new TicketActivity(source);
                    }

                    @Override
                    public TicketActivity[] newArray(int size) {
                        return new TicketActivity[size];
                    }
                };
                /**
                 * activityId : 1
                 * actType : 1
                 * actName : 秒杀活动
                 * actStart : 2018-04-24 09:36:19
                 * actEnd : 2018-04-24 09:36:19
                 * actTicketId : 4
                 * actTsId : 5
                 * actTotal : 100
                 * actSpecialTotal : 50
                 * actStock : 100
                 * actSpecialStock : 50
                 * actLimit : 10
                 * actPrice : 60
                 * actSpecialPrice : 80
                 * actImg :
                 * actVideo :
                 * actCreateTime : 2018-04-24 09:36:19
                 * actUpdateTime : 2018-04-24 09:36:19
                 * actInfo :
                 */

                private int activityId;
                private int actType;
                private String actName;
                private String actStart;
                private String actEnd;
                private int actTicketId;
                private int actTsId;
                private int actTotal;
                private int actSpecialTotal;
                private int actStock;
                private int actSpecialStock;
                private int actLimit;
                private int actPrice;
                private int actSpecialPrice;
                private String actImg;
                private String actVideo;
                private String actCreateTime;
                private String actUpdateTime;
                private String actInfo;

                public TicketActivity() {
                }

                protected TicketActivity(Parcel in) {
                    this.activityId = in.readInt();
                    this.actType = in.readInt();
                    this.actName = in.readString();
                    this.actStart = in.readString();
                    this.actEnd = in.readString();
                    this.actTicketId = in.readInt();
                    this.actTsId = in.readInt();
                    this.actTotal = in.readInt();
                    this.actSpecialTotal = in.readInt();
                    this.actStock = in.readInt();
                    this.actSpecialStock = in.readInt();
                    this.actLimit = in.readInt();
                    this.actPrice = in.readInt();
                    this.actSpecialPrice = in.readInt();
                    this.actImg = in.readString();
                    this.actVideo = in.readString();
                    this.actCreateTime = in.readString();
                    this.actUpdateTime = in.readString();
                    this.actInfo = in.readString();
                }

                public int getActivityId() {
                    return activityId;
                }

                public void setActivityId(int activityId) {
                    this.activityId = activityId;
                }

                public int getActType() {
                    return actType;
                }

                public void setActType(int actType) {
                    this.actType = actType;
                }

                public String getActName() {
                    return actName;
                }

                public void setActName(String actName) {
                    this.actName = actName;
                }

                public String getActStart() {
                    return actStart;
                }

                public void setActStart(String actStart) {
                    this.actStart = actStart;
                }

                public String getActEnd() {
                    return actEnd;
                }

                public void setActEnd(String actEnd) {
                    this.actEnd = actEnd;
                }

                public int getActTicketId() {
                    return actTicketId;
                }

                public void setActTicketId(int actTicketId) {
                    this.actTicketId = actTicketId;
                }

                public int getActTsId() {
                    return actTsId;
                }

                public void setActTsId(int actTsId) {
                    this.actTsId = actTsId;
                }

                public int getActTotal() {
                    return actTotal;
                }

                public void setActTotal(int actTotal) {
                    this.actTotal = actTotal;
                }

                public int getActSpecialTotal() {
                    return actSpecialTotal;
                }

                public void setActSpecialTotal(int actSpecialTotal) {
                    this.actSpecialTotal = actSpecialTotal;
                }

                public int getActStock() {
                    return actStock;
                }

                public void setActStock(int actStock) {
                    this.actStock = actStock;
                }

                public int getActSpecialStock() {
                    return actSpecialStock;
                }

                public void setActSpecialStock(int actSpecialStock) {
                    this.actSpecialStock = actSpecialStock;
                }

                public int getActLimit() {
                    return actLimit;
                }

                public void setActLimit(int actLimit) {
                    this.actLimit = actLimit;
                }

                public int getActPrice() {
                    return actPrice;
                }

                public void setActPrice(int actPrice) {
                    this.actPrice = actPrice;
                }

                public int getActSpecialPrice() {
                    return actSpecialPrice;
                }

                public void setActSpecialPrice(int actSpecialPrice) {
                    this.actSpecialPrice = actSpecialPrice;
                }

                public String getActImg() {
                    return actImg;
                }

                public void setActImg(String actImg) {
                    this.actImg = actImg;
                }

                public String getActVideo() {
                    return actVideo;
                }

                public void setActVideo(String actVideo) {
                    this.actVideo = actVideo;
                }

                public String getActCreateTime() {
                    return actCreateTime;
                }

                public void setActCreateTime(String actCreateTime) {
                    this.actCreateTime = actCreateTime;
                }

                public String getActUpdateTime() {
                    return actUpdateTime;
                }

                public void setActUpdateTime(String actUpdateTime) {
                    this.actUpdateTime = actUpdateTime;
                }                @Override
                public int describeContents() {
                    return 0;
                }

                public String getActInfo() {
                    return actInfo;
                }                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.activityId);
                    dest.writeInt(this.actType);
                    dest.writeString(this.actName);
                    dest.writeString(this.actStart);
                    dest.writeString(this.actEnd);
                    dest.writeInt(this.actTicketId);
                    dest.writeInt(this.actTsId);
                    dest.writeInt(this.actTotal);
                    dest.writeInt(this.actSpecialTotal);
                    dest.writeInt(this.actStock);
                    dest.writeInt(this.actSpecialStock);
                    dest.writeInt(this.actLimit);
                    dest.writeInt(this.actPrice);
                    dest.writeInt(this.actSpecialPrice);
                    dest.writeString(this.actImg);
                    dest.writeString(this.actVideo);
                    dest.writeString(this.actCreateTime);
                    dest.writeString(this.actUpdateTime);
                    dest.writeString(this.actInfo);
                }

                public void setActInfo(String actInfo) {
                    this.actInfo = actInfo;
                }




            }

            public static class PricesBean implements Parcelable {
                public static final Parcelable.Creator<PricesBean> CREATOR = new Parcelable.Creator<PricesBean>() {
                    @Override
                    public PricesBean createFromParcel(Parcel source) {
                        return new PricesBean(source);
                    }

                    @Override
                    public PricesBean[] newArray(int size) {
                        return new PricesBean[size];
                    }
                };
                /**
                 * pArea : 0
                 * pCreateTime :
                 * pMinNumber : 1
                 * pPrice : 130
                 * pRangeId : 0
                 * pRemark : 预售票
                 * pTicketId : 3
                 * pTsId : 5
                 * pType : 3
                 * pTypeName : 预售票
                 * pUpdateTime :
                 * pUsed : 1
                 * priceId : 4
                 * priceRange : []
                 */

                private int pArea;
                private String pCreateTime;
                private int pMinNumber;
                private int pPrice;
                private int pRangeId;
                private String pRemark;
                private int pTicketId;
                private int pTsId;
                private int pType;
                private String pTypeName;
                private String pUpdateTime;
                private int pUsed;
                private int priceId;
//                private List<String> priceRange;

                public PricesBean() {
                }

                protected PricesBean(Parcel in) {
                    this.pArea = in.readInt();
                    this.pCreateTime = in.readString();
                    this.pMinNumber = in.readInt();
                    this.pPrice = in.readInt();
                    this.pRangeId = in.readInt();
                    this.pRemark = in.readString();
                    this.pTicketId = in.readInt();
                    this.pTsId = in.readInt();
                    this.pType = in.readInt();
                    this.pTypeName = in.readString();
                    this.pUpdateTime = in.readString();
                    this.pUsed = in.readInt();
                    this.priceId = in.readInt();
                }

                public int getPArea() {
                    return pArea;
                }

                public void setPArea(int pArea) {
                    this.pArea = pArea;
                }

                public String getPCreateTime() {
                    return pCreateTime;
                }

                public void setPCreateTime(String pCreateTime) {
                    this.pCreateTime = pCreateTime;
                }

                public int getPMinNumber() {
                    return pMinNumber;
                }

                public void setPMinNumber(int pMinNumber) {
                    this.pMinNumber = pMinNumber;
                }

                public int getPPrice() {
                    return pPrice;
                }

                public void setPPrice(int pPrice) {
                    this.pPrice = pPrice;
                }

                public int getPRangeId() {
                    return pRangeId;
                }

                public void setPRangeId(int pRangeId) {
                    this.pRangeId = pRangeId;
                }

                public String getPRemark() {
                    return pRemark;
                }

                public void setPRemark(String pRemark) {
                    this.pRemark = pRemark;
                }

                public int getPTicketId() {
                    return pTicketId;
                }

                public void setPTicketId(int pTicketId) {
                    this.pTicketId = pTicketId;
                }

                public int getPTsId() {
                    return pTsId;
                }

                public void setPTsId(int pTsId) {
                    this.pTsId = pTsId;
                }

                public int getPType() {
                    return pType;
                }

                public void setPType(int pType) {
                    this.pType = pType;
                }

                public String getPTypeName() {
                    return pTypeName;
                }

                public void setPTypeName(String pTypeName) {
                    this.pTypeName = pTypeName;
                }

                public String getPUpdateTime() {
                    return pUpdateTime;
                }

                public void setPUpdateTime(String pUpdateTime) {
                    this.pUpdateTime = pUpdateTime;
                }

                public int getPUsed() {
                    return pUsed;
                }

                public void setPUsed(int pUsed) {
                    this.pUsed = pUsed;
                }

                public int getPriceId() {
                    return priceId;
                }

                public void setPriceId(int priceId) {
                    this.priceId = priceId;
                }                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.pArea);
                    dest.writeString(this.pCreateTime);
                    dest.writeInt(this.pMinNumber);
                    dest.writeInt(this.pPrice);
                    dest.writeInt(this.pRangeId);
                    dest.writeString(this.pRemark);
                    dest.writeInt(this.pTicketId);
                    dest.writeInt(this.pTsId);
                    dest.writeInt(this.pType);
                    dest.writeString(this.pTypeName);
                    dest.writeString(this.pUpdateTime);
                    dest.writeInt(this.pUsed);
                    dest.writeInt(this.priceId);
                }





            }            @Override
            public int describeContents() {
                return 0;
            }

            public static class StandardBean implements Parcelable {
                public static final Creator<StandardBean> CREATOR = new Creator<StandardBean>() {
                    @Override
                    public StandardBean createFromParcel(Parcel source) {
                        return new StandardBean(source);
                    }

                    @Override
                    public StandardBean[] newArray(int size) {
                        return new StandardBean[size];
                    }
                };
                /**
                 * pArea : 0
                 * pCreateTime :
                 * pMinNumber : 1
                 * pPrice : 150
                 * pRangeId : 0
                 * pRemark : 正价票
                 * pTicketId : 3
                 * pTsId : 5
                 * pType : 1
                 * pTypeName : 正价票
                 * pUpdateTime :
                 * pUsed : 1
                 * priceId : 3
                 * priceRange : []
                 */

                private int pArea;
                private String pCreateTime;
                private int pMinNumber;
                private int pPrice;
                private int pRangeId;
                private String pRemark;
                private int pTicketId;
                private int pTsId;
                private int pType;
                private String pTypeName;
                private String pUpdateTime;
                private int pUsed;
                private int priceId;

                public StandardBean() {
                }

                protected StandardBean(Parcel in) {
                    this.pArea = in.readInt();
                    this.pCreateTime = in.readString();
                    this.pMinNumber = in.readInt();
                    this.pPrice = in.readInt();
                    this.pRangeId = in.readInt();
                    this.pRemark = in.readString();
                    this.pTicketId = in.readInt();
                    this.pTsId = in.readInt();
                    this.pType = in.readInt();
                    this.pTypeName = in.readString();
                    this.pUpdateTime = in.readString();
                    this.pUsed = in.readInt();
                    this.priceId = in.readInt();
                }

                public int getPArea() {
                    return pArea;
                }

                public void setPArea(int pArea) {
                    this.pArea = pArea;
                }

                public String getPCreateTime() {
                    return pCreateTime;
                }

                public void setPCreateTime(String pCreateTime) {
                    this.pCreateTime = pCreateTime;
                }

                public int getPMinNumber() {
                    return pMinNumber;
                }

                public void setPMinNumber(int pMinNumber) {
                    this.pMinNumber = pMinNumber;
                }

                public int getPPrice() {
                    return pPrice;
                }

                public void setPPrice(int pPrice) {
                    this.pPrice = pPrice;
                }

                public int getPRangeId() {
                    return pRangeId;
                }

                public void setPRangeId(int pRangeId) {
                    this.pRangeId = pRangeId;
                }

                public String getPRemark() {
                    return pRemark;
                }

                public void setPRemark(String pRemark) {
                    this.pRemark = pRemark;
                }

                public int getPTicketId() {
                    return pTicketId;
                }

                public void setPTicketId(int pTicketId) {
                    this.pTicketId = pTicketId;
                }

                public int getPTsId() {
                    return pTsId;
                }

                public void setPTsId(int pTsId) {
                    this.pTsId = pTsId;
                }

                public int getPType() {
                    return pType;
                }

                public void setPType(int pType) {
                    this.pType = pType;
                }

                public String getPTypeName() {
                    return pTypeName;
                }

                public void setPTypeName(String pTypeName) {
                    this.pTypeName = pTypeName;
                }

                public String getPUpdateTime() {
                    return pUpdateTime;
                }

                public void setPUpdateTime(String pUpdateTime) {
                    this.pUpdateTime = pUpdateTime;
                }

                public int getPUsed() {
                    return pUsed;
                }

                public void setPUsed(int pUsed) {
                    this.pUsed = pUsed;
                }

                public int getPriceId() {
                    return priceId;
                }

                public void setPriceId(int priceId) {
                    this.priceId = priceId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.pArea);
                    dest.writeString(this.pCreateTime);
                    dest.writeInt(this.pMinNumber);
                    dest.writeInt(this.pPrice);
                    dest.writeInt(this.pRangeId);
                    dest.writeString(this.pRemark);
                    dest.writeInt(this.pTicketId);
                    dest.writeInt(this.pTsId);
                    dest.writeInt(this.pType);
                    dest.writeString(this.pTypeName);
                    dest.writeString(this.pUpdateTime);
                    dest.writeInt(this.pUsed);
                    dest.writeInt(this.priceId);
                }




            }            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.activity, flags);
                dest.writeString(this.tsAddress);
                dest.writeInt(this.tsAvailable);
                dest.writeString(this.tsCity);
                dest.writeString(this.tsCreateTime);
                dest.writeString(this.tsEndTime);
                dest.writeInt(this.tsHallId);
                dest.writeInt(this.tsId);
                dest.writeString(this.tsLocale);
                dest.writeString(this.tsNation);
                dest.writeString(this.tsPresaleEnd);
                dest.writeInt(this.tsPresaleSpecialStock);
                dest.writeInt(this.tsPresaleSpecialTotal);
                dest.writeString(this.tsPresaleStart);
                dest.writeInt(this.tsPresaleStock);
                dest.writeInt(this.tsPresaleTotal);
                dest.writeString(this.tsSaleEnd);
                dest.writeString(this.tsSaleStart);
                dest.writeInt(this.tsSpecialStock);
                dest.writeInt(this.tsSpecialTotal);
                dest.writeString(this.tsStartTime);
                dest.writeInt(this.tsTicketId);
                dest.writeInt(this.tsTicketStock);
                dest.writeInt(this.tsTicketTotal);
                dest.writeString(this.tsTime);
                dest.writeString(this.tsUpdateTime);
                dest.writeTypedList(this.prices);
                dest.writeTypedList(this.standard);
            }




        }




    }




}
