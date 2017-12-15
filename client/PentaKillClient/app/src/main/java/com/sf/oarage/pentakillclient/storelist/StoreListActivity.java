package com.sf.oarage.pentakillclient.storelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sf.oarage.pentakillclient.R;

/**
 * 集货助手列表页
 * Created by Spawn on 2017/12/15.
 */

public class StoreListActivity extends AppCompatActivity {


    private RecyclerView mStoreListView;
    private StoreListAdapter mStoreListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        initViews();
    }

    private void initViews() {
        mStoreListView = findViewById(R.id.rv_store_list);
        mStoreListAdapter = new StoreListAdapter(this);
        mStoreListView.setLayoutManager(new LinearLayoutManager(this));
        mStoreListView.setAdapter(mStoreListAdapter);
    }
}
