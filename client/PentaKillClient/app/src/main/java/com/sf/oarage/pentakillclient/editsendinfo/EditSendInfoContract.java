package com.sf.oarage.pentakillclient.editsendinfo;

import com.sf.oarage.pentakillclient.base.BasePresenter;
import com.sf.oarage.pentakillclient.base.BaseView;

/**
 * Created by liushihan on 2017/12/15.
 */

public interface EditSendInfoContract {
    interface View extends BaseView<Presenter> {
        void showToast(String message);
    }

    interface Presenter extends BasePresenter {
        void signOfStore();
        boolean checkRequiredFill();
    }
}
