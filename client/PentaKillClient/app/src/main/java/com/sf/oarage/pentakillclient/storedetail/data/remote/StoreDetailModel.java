package com.sf.oarage.pentakillclient.storedetail.data.remote;

import com.sf.oarage.pentakillclient.network.Callback;

/**
 * Created by tangbin on 2017/12/15.
 */

public interface StoreDetailModel {

    /**
     * 拉去集货团清单
     */
    void reqStoreDetail(Callback callback);
}
