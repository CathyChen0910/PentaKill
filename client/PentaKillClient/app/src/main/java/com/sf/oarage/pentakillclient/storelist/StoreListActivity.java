package com.sf.oarage.pentakillclient.storelist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sf.oarage.pentakillclient.R;

/**
 * 集货助手列表页
 * Created by Spawn on 2017/12/15.
 */

public class StoreListActivity extends AppCompatActivity implements StoreListContract.StoreListView {


    private RecyclerView mStoreListView;
    private StoreListAdapter mStoreListAdapter;
    private StoreListContract.StoreListPresenter mPresenter;

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
        StoreListContract.StoreListPresenter presenter = new StoreListPresneterImpl();
        presenter.start();
    }

    private void initViews() {
        mStoreListView = findViewById(R.id.rv_store_list);
        mStoreListAdapter = new StoreListAdapter(this);
        mStoreListView.setLayoutManager(new LinearLayoutManager(this));
        mStoreListView.setAdapter(mStoreListAdapter);
        //test
        TextView viewById = findViewById(R.id.tv_click_next);
        viewById.setMovementMethod(LinkMovementMethod.getInstance());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewById.setText(Html.fromHtml("<a href=\"cby://oarage.sf.com/openwith?storeId=091318\">启动应用程序</a> ", Html.FROM_HTML_MODE_LEGACY));
        } else {
            viewById.setText(Html.fromHtml("<a href=\"cby://oarage.sf.com/openwith?storeId=091318\">启动应用程序</a> "));
        }

    }

    @Override
    public void setPresenter(StoreListContract.StoreListPresenter presenter) {
        this.mPresenter = presenter;
    }
}
