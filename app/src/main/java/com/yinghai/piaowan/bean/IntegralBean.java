package com.yinghai.piaowan.bean;

/**
 * @author Created by：chenjianrun
 *         Created on：2018/4/24
 *         Description：积分领取的 bean
 * @Param
 */

public class IntegralBean {
    private int day;
    private int point;

    public IntegralBean(int day, int point) {
        this.day = day;
        this.point = point;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
