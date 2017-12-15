package com.sf.oarage.pentakillclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreDetailActivity extends AppCompatActivity {

    //集货团
    private TextView mTvMarket;
    //重量范围
    private TextView mTvWeightRange;
    //最低寄件量
    private TextView mTvMinNum;
    //最低价格单价
    private TextView mTvMinPrice;
    //集货团图片
    private ImageView mIvStorePic;
    //报名进度
    private RoundedRectProgressBar mIvProgress;
    //还差几人成团
    private TextView mTvRemain;
    //截止时间
    private TextView mTvEndTime;
    //使用要求
    private TextView mRequirement;
    //已有几个人参团
    private TextView mTvParticipate;
    //立即参与
    private Button mBtnJoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        initView();
        loadData();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTvMarket = findViewById(R.id.tv_market_id);
        mTvWeightRange = findViewById(R.id.tv_weight_range);
        mTvMinNum = findViewById(R.id.tv_min_num);
        mTvMinPrice = findViewById(R.id.tv_min_price);
        mIvStorePic = findViewById(R.id.iv_store_pic);
        mIvProgress = findViewById(R.id.iv_progress);
        mTvRemain = findViewById(R.id.tv_remaining);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mRequirement = findViewById(R.id.tv_requirement);
        mTvParticipate = findViewById(R.id.tv_participate);
        mBtnJoin = findViewById(R.id.btn_join);
    }

    /**
     *
     */
    private void loadData() {

    }
}
