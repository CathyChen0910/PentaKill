package com.sf.oarage.pentakillclient.storelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sf.oarage.pentakillclient.R;

/**
 * Created by tangbin on 2017/12/15.
 */

public class StoreListAdapter extends RecyclerView.Adapter {

    private Context mContext;

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
        if (holder instanceof StoreListCommListHolder) {
            ((StoreListCommListHolder) holder).mPurChaseName.setText("123");
            ((StoreListCommListHolder) holder).mPurChaseCount.setText("11/22sss");
            ((StoreListCommListHolder) holder).mPurChaseJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "进去", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
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
