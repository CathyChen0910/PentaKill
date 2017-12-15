package com.sf.oarage.pentakillclient.storelist;

import com.sf.oarage.pentakillclient.base.BasePresenter;
import com.sf.oarage.pentakillclient.base.BaseView;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cathy on 2017/12/15.
 */

public interface StoreListContract {

    interface StoreListPresenter extends BasePresenter<StoreListView>{

        /**
         * 加载列表
         */
        void loadStoreList();
    }

    interface StoreListView extends BaseView<StoreListPresenter>{

        /**
         * 数据回来的时候
         * @param storeListBean bean
         */
        void onDataListSuccess(List<StoreListBean> storeListBean);

        /**
         * 数据请求失败
         * @param message 错误消息
         */
        void onDataListFail(String message);
    }

}
