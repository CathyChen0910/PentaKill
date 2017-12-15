package com.sf.oarage.pentakillclient.storedetail;

import com.sf.oarage.pentakillclient.base.BasePresenter;
import com.sf.oarage.pentakillclient.base.BaseView;
import com.sf.oarage.pentakillclient.storedetail.data.remote.StoreDetailBean;

import java.util.List;

/**
 * Created by cathy chen on 2017/12/15.
 */

public interface StoreDetailContract {
    interface StoreDetailPresenter extends BasePresenter<StoreDetailView> {

        /**
         * 加载列表
         */
        void loadStoreList();
    }

    interface StoreDetailView extends BaseView<StoreDetailPresenter> {

        /**
         * 数据回来的时候
         * @param
         */
//        void onDataListSuccess(StoreDetailBean> );

        /**
         * 数据请求失败
         * @param message 错误消息
         */
        void onDataListFail(String message);
    }
}
