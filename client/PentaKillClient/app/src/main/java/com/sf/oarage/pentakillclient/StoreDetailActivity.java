package com.sf.oarage.pentakillclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sf.oarage.pentakillclient.editsendinfo.EditSendInfoActivity;

import java.util.Timer;
import java.util.TimerTask;

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

    //进度百分比
    private int progress;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        initView();
        initListener();
        loadData();
        initProgress();

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
     * 初始化点击事件
     */
    private void initListener() {
        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });

    }

    /**
     * 加载后台对应的集货团信息
     */
    private void loadData() {
        //网络请求返回一个数据集

    }

    private void updateView() {

    }

    private void nextStep() {
        Intent intent = new Intent();
        intent.setClass(StoreDetailActivity.this, EditSendInfoActivity.class);
        startActivity(intent);
    }

    private void initProgress(){
        progress = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mIvProgress.setProgress(progress);
                progress ++;
                if (progress > 100) {
                    timer.cancel();
                }
            }
        }, 0, 30);

    }


}
