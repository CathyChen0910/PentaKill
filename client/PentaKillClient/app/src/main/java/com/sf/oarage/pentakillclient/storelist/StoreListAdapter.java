package com.sf.oarage.pentakillclient.storelist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.storelist.data.remote.StoreListBean;

import java.util.List;

/**
 * Created by Spawn on 2017/12/15.
 */

public class StoreListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<StoreListBean> mStoreListBeans;

    public StoreListAdapter(Context context){
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoreListCommListHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.store_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final StoreListBean storeListBean = mStoreListBeans.get(position);
        if (holder instanceof StoreListCommListHolder) {

            ((StoreListCommListHolder) holder).mPurChaseName.setText(String.valueOf(storeListBean.getPeriodNum()));
            ((StoreListCommListHolder) holder).mPurChaseCount.setText(String.valueOf(storeListBean.getId()));
            ((StoreListCommListHolder) holder).mPurChaseJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = storeListBean.getId();
                    int marketId = storeListBean.getMarketId();
                    Uri uri = Uri.parse("cby://oarage.sf.com/openwith?store_id=" + id + "&market_id=" + marketId);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mStoreListBeans!=null){
            return mStoreListBeans.size();
        }
        return 0;
    }

    public void setData(List<StoreListBean> storeListBean) {
        mStoreListBeans = storeListBean;
        notifyDataSetChanged();
    }

    private static class StoreListCommListHolder extends RecyclerView.ViewHolder {

        TextView mPurChaseName;
        TextView mPurChaseJoin;
        TextView mPurChaseCount;

        StoreListCommListHolder(View itemView) {
            super(itemView);
            mPurChaseName = itemView.findViewById(R.id.tv_purchase_name);
            mPurChaseJoin = itemView.findViewById(R.id.tv_join_purchase);
            mPurChaseCount = itemView.findViewById(R.id.tv_purchase_count);
        }
    }
}
