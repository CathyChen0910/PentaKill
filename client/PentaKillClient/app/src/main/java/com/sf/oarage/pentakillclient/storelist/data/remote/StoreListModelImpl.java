package com.sf.oarage.pentakillclient.storelist.data.remote;

import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.network.Callback;
import com.sf.oarage.pentakillclient.network.RestGetRequester;

/**
 * Created by Cathy on 2017/12/15.
 */

public class StoreListModelImpl implements StoreListModel{

    @Override
    public void reqStoreList(Callback callback) {
        RestGetRequester requester = new RestGetRequester(Constants.Method.STORE_LIST);
        requester.execute(callback);
    }
}
