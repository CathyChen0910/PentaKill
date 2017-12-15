package com.sf.oarage.pentakillclient.storelist;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sf.oarage.pentakillclient.network.MainThreadCallback;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListBean;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListModel;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListModelImpl;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Cathy on 2017/12/15.
 */

public class StoreListPresenterImpl implements StoreListContract.StoreListPresenter {

    private StoreListContract.StoreListView mView;
    private StoreListModel mStoreListModel;

    @Override
    public void start(StoreListContract.StoreListView view) {
        this.mView = view;
        view.setPresenter(this);
        mStoreListModel = new StoreListModelImpl();
    }

    @Override
    public void loadStoreList() {
        mStoreListModel.reqStoreList(new MainThreadCallback() {
            @Override
            public void onMainThreadSuccess(String data) throws JSONException {
                Gson gson = new Gson();
                JsonParser parser=new JsonParser();
                JsonArray asJsonArray = parser.parse(data).getAsJsonArray();
                ArrayList<StoreListBean> userBeanList = new ArrayList<>();

                //加强for循环遍历JsonArray
                for (JsonElement user : asJsonArray) {
                    //使用GSON，直接转成Bean对象
                    StoreListBean userBean = gson.fromJson(user, StoreListBean.class);
                    userBeanList.add(userBean);
                }

                if (!userBeanList.isEmpty()) {
                    mView.onDataListSuccess(userBeanList);
                }else {
                    onMainThreadError(new Throwable("数据为空"));
                }
            }

            @Override
            public void onMainThreadError(Throwable t) {
                mView.onDataListFail(t.getMessage());
            }
        });
    }
}
