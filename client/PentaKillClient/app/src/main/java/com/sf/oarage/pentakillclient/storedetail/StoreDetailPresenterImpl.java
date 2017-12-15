package com.sf.oarage.pentakillclient.storedetail;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.sf.oarage.pentakillclient.network.MainThreadCallback;
import com.sf.oarage.pentakillclient.storedetail.data.remote.MarketBean;
import com.sf.oarage.pentakillclient.storedetail.data.remote.StoreDetailBean;
import com.sf.oarage.pentakillclient.storedetail.data.remote.StoreDetailModel;
import com.sf.oarage.pentakillclient.storedetail.data.remote.StoreDetailModelImpl;

import org.json.JSONException;

/**
 * Created by tangbin on 2017/12/15.
 */

public class StoreDetailPresenterImpl implements StoreDetailContract.StoreDetailPresenter {
    private StoreDetailContract.StoreDetailView mView;
    private StoreDetailModel mStoreDetailModel;

    @Override
    public void start(StoreDetailContract.StoreDetailView view) {
        this.mView = view;
        view.setPresenter(this);
        mStoreDetailModel = new StoreDetailModelImpl();
    }

    @Override
    public void loadStoreDetail(String storeId) {
        mStoreDetailModel.reqStoreDetail(storeId,new MainThreadCallback() {
            @Override
            public void onMainThreadSuccess(String data) throws JSONException {
                Gson gson = new Gson();
                StoreDetailBean storeDetail = gson.fromJson(data, StoreDetailBean.class);
                if (null != storeDetail) {
                    mView.onDataDetailSuccess(storeDetail);
                } else {
                    onMainThreadError(new Throwable("数据为空"));
                }
            }

            @Override
            public void onMainThreadError(Throwable t) {
                mView.onDataListFail(t.getMessage());
            }
        });
    }

    @Override
    public void loadMarket(String marketId) {
        mStoreDetailModel.reqMarket(new MainThreadCallback() {
            @Override
            public void onMainThreadSuccess(String data) throws JSONException {
                //处理
                Gson gson = new Gson();
                MarketBean marketBean = gson.fromJson(data, MarketBean.class);
                if (null != marketBean) {
                    mView.onMarketDataSuccess(marketBean);
                } else {
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
