package com.sf.oarage.pentakillclient.storelist;

import com.sf.oarage.pentakillclient.base.BaseView;

/**
 * Created by Cathy on 2017/12/15.
 */

public class StoreListPresenterImpl implements StoreListContract.StoreListPresenter {

    private BaseView mView;

    @Override
    public void start(StoreListContract.StoreListView view) {
        this.mView = view;
    }

}
