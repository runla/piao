package com.yinghai.piaowan.bean;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/16
 *         Description：商城的 bean
 * @Param
 */

public class ShopBean {
    private String imageUrl;
    private String goodDescrip;
    private int goodPrice;

    public ShopBean(String imageUrl, String goodDescrip, int goodPrice) {
        this.imageUrl = imageUrl;
        this.goodDescrip = goodDescrip;
        this.goodPrice = goodPrice;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodDescrip() {
        return goodDescrip == null ? "" : goodDescrip;
    }

    public void setGoodDescrip(String goodDescrip) {
        this.goodDescrip = goodDescrip;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }
}
