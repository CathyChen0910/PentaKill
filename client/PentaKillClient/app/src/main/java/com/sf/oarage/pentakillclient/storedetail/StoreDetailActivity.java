package com.sf.oarage.pentakillclient.storedetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.RoundedRectProgressBar;
import com.sf.oarage.pentakillclient.editsendinfo.EditSendInfoActivity;
import com.sf.oarage.pentakillclient.storedetail.data.remote.MarketBean;
import com.sf.oarage.pentakillclient.storedetail.data.remote.StoreDetailBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StoreDetailActivity extends AppCompatActivity implements StoreDetailContract.StoreDetailView {

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
    private int nowProgress;
    private Timer timer;

    private StoreDetailContract.StoreDetailPresenter mPresenter;
    private ProgressDialog mProgressDialog;
    private StoreDetailBean mStoreDetail;
    private MarketBean mMarketBean;
    private String storeId;
    private String marketId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        initView();
        initListener();
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
        StoreDetailContract.StoreDetailPresenter storeDetailPresenter = new StoreDetailPresenterImpl();
        storeDetailPresenter.start(this);
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
        Uri uri = getIntent().getData();
        if (null != uri) {
            storeId = uri.getQueryParameter("store_id");
            marketId = uri.getQueryParameter("market_id");
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        mPresenter.loadStoreDetail(storeId);
    }

    private void updateStoreView(StoreDetailBean storeDetailBean) {
        mTvMarket.setText(storeDetailBean.getPeriodNum());
        mTvMinNum.setText(Html.fromHtml(getString(R.string.min_number, storeDetailBean.getMinBagNum())));
        mTvMinPrice.setText(Html.fromHtml(getString(R.string.price, storeDetailBean.getMinWeight(), 1)));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        long lcc = Long.valueOf(storeDetailBean.getEndTime());
        String times = simpleDateFormat.format(new Date(lcc));
        mTvEndTime.setText(Html.fromHtml(getString(R.string.end_time, times)));
        Glide.with(this)
                .load(Constants.HOST + "/"+storeDetailBean.getPicture())
                .into(mIvStorePic);
        mTvParticipate.setText(Html.fromHtml(getString(R.string.participate, mStoreDetail.getSignedNum())));
    }

    private void updateMarketView(MarketBean marketBean) {
        mRequirement.setText(Html.fromHtml(marketBean.getUserRequire()));
        mTvWeightRange.setText(String.format(getString(R.string.weight_range), marketBean.getMinWeight(), marketBean.getMaxWeight()));
        mTvRemain.setText(Html.fromHtml(getString(R.string.remaining, marketBean.getLimitNum() - mStoreDetail.getSignedNum())));
        nowProgress = mStoreDetail.getSignedNum() / marketBean.getLimitNum() * 100;
        initProgress();
    }

    private void nextStep() {
        Intent intent = new Intent();
        intent.setClass(StoreDetailActivity.this, EditSendInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("groupId", Long.parseLong(storeId));
        bundle.putLong("marketId", Long.parseLong(marketId));
        startActivity(intent);
    }

    private void initProgress() {
        progress = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mIvProgress.setProgress(progress);
                progress++;
                if (progress > nowProgress) {
                    timer.cancel();
                }
            }
        }, 0, 30);
    }

    @Override
    public void setPresenter(StoreDetailContract.StoreDetailPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDataDetailSuccess(StoreDetailBean storeDetailBean) {
        mProgressDialog.dismiss();
        this.mStoreDetail = storeDetailBean;
        updateStoreView(mStoreDetail);
        mPresenter.loadMarket(marketId);
    }

    @Override
    public void onMarketDataSuccess(MarketBean marketBean) {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        this.mMarketBean = marketBean;
        updateMarketView(marketBean);
    }

    @Override
    public void onDataListFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, getString(R.string.load_fail) + message, Toast.LENGTH_SHORT).show();
    }
}
