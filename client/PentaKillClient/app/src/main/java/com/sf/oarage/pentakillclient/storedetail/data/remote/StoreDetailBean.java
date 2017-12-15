package com.sf.oarage.pentakillclient.storedetail.data.remote;

/**
 * Created by tangbin on 2017/12/15.
 */

public class StoreDetailBean{
    /**
     * 期数
     */
    private String periodNum;
    /**
     * 最低价
     */
    private double minWeight;
    /**
     * 每日最低件数
     */
    private int minBagNum;

    /**
     * 截止时间
     * @return
     */
    private String endTime;

    /**
     * 图片地址
     * @return
     */
    private String picture;

    /**
     * 当前已报名人数
     */
    private int signedNum;

    public int getSignedNum() {
        return signedNum;
    }

    public void setSignedNum(int signedNum) {
        this.signedNum = signedNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public int getMinBagNum() {
        return minBagNum;
    }

    public void setMinBagNum(int minBagNum) {
        this.minBagNum = minBagNum;
    }

    @Override
    public String toString() {
        return "StoreDetailBean{" +
                "periodNum='" + periodNum + '\'' +
                ", minWeight=" + minWeight +
                ", minBagNum=" + minBagNum +
                ", endTime='" + endTime + '\'' +
                ", picture='" + picture + '\'' +
                ", signedNum=" + signedNum +
                '}';
    }
}
