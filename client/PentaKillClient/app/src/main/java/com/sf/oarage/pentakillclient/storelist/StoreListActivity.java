package com.sf.oarage.pentakillclient.storelist;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListBean;

import java.util.List;

/**
 * 集货助手列表页
 * Created by Spawn on 2017/12/15.
 */

public class StoreListActivity extends AppCompatActivity implements StoreListContract.StoreListView {


    private StoreListAdapter mStoreListAdapter;
    private StoreListContract.StoreListPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        ActionBar actionBar = getSupportActionBar();
        //actionbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initViews();
        StoreListContract.StoreListPresenter presenter = new StoreListPresenterImpl();
        presenter.start(this);
        initData();
    }

    private void initData() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中...");
        mProgressDialog.show();
        mPresenter.loadStoreList();
    }

    private void initViews() {
        RecyclerView mStoreListView = findViewById(R.id.rv_store_list);
        mStoreListAdapter = new StoreListAdapter(this);
        mStoreListView.setLayoutManager(new LinearLayoutManager(this));
        mStoreListView.setAdapter(mStoreListAdapter);
    }

    @Override
    public void setPresenter(StoreListContract.StoreListPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDataListSuccess(List<StoreListBean> storeListBean) {
        mProgressDialog.dismiss();
        mStoreListAdapter.setData(storeListBean);
    }

    @Override
    public void onDataListFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, getString(R.string.load_fail) + message, Toast.LENGTH_SHORT).show();
    }
}
