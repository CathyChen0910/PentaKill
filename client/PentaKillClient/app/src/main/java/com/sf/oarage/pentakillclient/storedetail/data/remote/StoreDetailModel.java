package com.sf.oarage.pentakillclient.storedetail.data.remote;

import com.sf.oarage.pentakillclient.network.Callback;

/**
 * Created by tangbin on 2017/12/15.
 */

public interface StoreDetailModel {

    /**
     * 拉去集货团清单
     */
    void reqStoreDetail(String storeId,Callback callback);

    /**
     * 请求市场数据
     * @param callback
     */
    void reqMarket(String marketId,Callback callback);
}
