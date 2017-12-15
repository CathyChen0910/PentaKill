package com.sf.oarage.pentakillclient.storelist.data.remote;

/**
 * Created by Cathy on 2017/12/15.
 */

public class StoreListBean {
    private int id;
    private int marketId;
    private long createTime;
    private String periodNum;
    private String picture;
    private String groupType;
    private int minBagNum;
    private int itemWeight;
    private int promisePeriod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public int getMinBagNum() {
        return minBagNum;
    }

    public void setMinBagNum(int minBagNum) {
        this.minBagNum = minBagNum;
    }

    public int getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    public int getPromisePeriod() {
        return promisePeriod;
    }

    public void setPromisePeriod(int promisePeriod) {
        this.promisePeriod = promisePeriod;
    }

    @Override
    public String toString() {
        return "StoreListBean{" +
                "id=" + id +
                ", marketId=" + marketId +
                ", createTime=" + createTime +
                ", periodNum='" + periodNum + '\'' +
                ", picture='" + picture + '\'' +
                ", groupType='" + groupType + '\'' +
                ", minBagNum=" + minBagNum +
                ", itemWeight=" + itemWeight +
                ", promisePeriod=" + promisePeriod +
                '}';
    }
}
