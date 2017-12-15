package com.sf.oarage.pentakillclient.storedetail.data.remote;

import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.network.Callback;
import com.sf.oarage.pentakillclient.network.RestPostRequester;

/**
 * Created by tangbin on 2017/12/15.
 */

public class StoreDetailModelImpl implements StoreDetailModel {
    @Override
    public void reqStoreDetail(String storeId, Callback callback) {
        RestPostRequester requester = new RestPostRequester(Constants.Method.STORE_DETAIL);
        requester.execute(storeId, callback);
    }

    @Override
    public void reqMarket(String marketId, Callback callback) {
        RestPostRequester requester = new RestPostRequester(Constants.Method.MARKET_DATA);
        requester.execute(marketId, callback);
    }

}
