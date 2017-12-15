package com.sf.oarage.pentakillclient.storelist.data.remote;

import com.sf.oarage.pentakillclient.network.Callback;

/**
 * Created by Cathy on 2017/12/15.
 */

public interface StoreListModel {

    /**
     * 拉去集货列表哦
     */
    void reqStoreList(Callback callback);
}
