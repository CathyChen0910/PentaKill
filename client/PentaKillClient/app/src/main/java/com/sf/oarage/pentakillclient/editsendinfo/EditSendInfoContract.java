package com.sf.oarage.pentakillclient.editsendinfo;

import com.sf.oarage.pentakillclient.base.BasePresenter;
import com.sf.oarage.pentakillclient.base.BaseView;

/**
 * Created by liushihan on 2017/12/15.
 */

public interface EditSendInfoContract {
    interface View extends BaseView<Presenter> {
        String getAddress();
        String getAddressDetail();
        String getSenderName();
        String getSenderPhone();
        String getSenderCount();
        String getSenderWeight();
        void showToast(String message);
        void showWechatDialog();
    }

    interface Presenter extends BasePresenter<EditSendInfoContract.View> {
        void signOfStore();
        boolean checkRequiredFill();
    }
}
