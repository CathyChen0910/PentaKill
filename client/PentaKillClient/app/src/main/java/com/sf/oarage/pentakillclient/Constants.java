package com.sf.oarage.pentakillclient;

/**
 * Created by spawn on 17-12-14.
 */

public interface Constants {
    String HOST = "http://192.168.100.27:8080/pentaKill";

    interface Method {
        /**
         * 获取集货团列表
         */
        String STORE_LIST = "/group/get";
        /**
         * 获取集货团详情
         */
        String STORE_DETAIL = "/group/getOne";
        /**
         * 获取市场数据
         */
        String MARKET_DATA ="/group/getOne/market";

        /**
         * 集货报名
         */
        String DO_SIGN = "/signUp";
    }

}
