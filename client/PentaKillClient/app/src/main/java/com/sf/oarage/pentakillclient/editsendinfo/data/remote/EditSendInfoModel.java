package com.sf.oarage.pentakillclient.editsendinfo.data.remote;

import com.sf.oarage.pentakillclient.network.Callback;

/**
 * Created by liushihan on 2017/12/15.
 */

public interface EditSendInfoModel {
    /**
     * 集货报名
     */
    void doSign(String result, Callback callback);
}
