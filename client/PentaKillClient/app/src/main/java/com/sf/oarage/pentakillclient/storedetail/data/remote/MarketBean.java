package com.sf.oarage.pentakillclient.storedetail.data.remote;

/**
 * Created by Cathy on 2017/12/15.
 */

public class MarketBean {
    /**
     * 重量区间（低）
     */
    private double minWeight;
    /**
     * 重量区间（高）
     */
    private double maxWeight;
    /**
     * 总限制人数
     */
    private int limitNum;

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    @Override
    public String toString() {
        return "MarketBean{" +
                "minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", limitNum=" + limitNum +
                '}';
    }
}
