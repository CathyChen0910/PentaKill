package com.sf.oarage.pentakillclient.editsendinfo.data.remote;

import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.network.Callback;
import com.sf.oarage.pentakillclient.network.RestPostRequester;

/**
 * Created by liushihan on 2017/12/15.
 */

public class EditSendInfoModelImpl implements EditSendInfoModel{
    @Override
    public void doSign(String result, Callback callback) {
        RestPostRequester postRequester = new RestPostRequester(Constants.Method.DO_SIGN);
        postRequester.execute("",callback);
    }
}
