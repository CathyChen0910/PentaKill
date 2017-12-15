package com.sf.oarage.pentakillclient.editsendinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.qrcode.QrCodeActivity;
import com.sf.oarage.pentakillclient.utils.StringUtils;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * Created by liushihan on 2017/12/15.
 */

public class EditSendInfoActivity extends AppCompatActivity implements EditSendInfoContract.View, View.OnClickListener {
    TextView textAddressOfPicker;
    EditText editAddressDetail;
    EditText editName;
    EditText editPhone;
    EditText editSendCount;
    EditText editWeight;
    Button btnSign;
    EditSendInfoContract.Presenter mPresenter;
    private long groupId;
    private long marketId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sendinfo);
        textAddressOfPicker = findViewById(R.id.textAddress);
        editAddressDetail = findViewById(R.id.textAddressDetail);
        editName = findViewById(R.id.edit_sender_name);
        editPhone = findViewById(R.id.edit_send_phone);
        editSendCount = findViewById(R.id.edit_send_count);
        editWeight = findViewById(R.id.edit_single_weight);
        btnSign = findViewById(R.id.btn_sign);
        setListener();
        EditSendInfoPresenter editSendInfoPresenter = new EditSendInfoPresenter();
        editSendInfoPresenter.start(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null) {
            groupId = extras.getLong("groupId");
            marketId = extras.getLong("marketId");
            mPresenter.setGroupId(groupId);
        }

    }

    private void setListener() {
        textAddressOfPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialog dialog = new BottomDialog(EditSendInfoActivity.this);
                dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                    @Override
                    public void onAddressSelected(Province province, City city, County county, Street street) {
                        //判空
                        String address = null;
                        if (province != null) {
                            address = province.name;
                        }
                        if (city != null) {
                            address += city.name;
                        }
                        if (county != null) {
                            address += county.name;
                        }
                        if (street != null) {
                            address += street.name;
                        }
                        textAddressOfPicker.setText(address);
                    }
                });
                dialog.show();
            }
        });
        btnSign.setOnClickListener(this);
    }

    @Override
    public void setPresenter(EditSendInfoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getAddress() {
        if (StringUtils.isBlank(textAddressOfPicker.getText().toString())) {
            return "";
        }
        return textAddressOfPicker.getText().toString();
    }

    @Override
    public String getAddressDetail() {
        if (StringUtils.isBlank(editAddressDetail.getText().toString())) {
            return "";
        }
        return editAddressDetail.getText().toString();
    }

    @Override
    public String getSenderName() {
        if (StringUtils.isBlank(editName.getText().toString())) {
            return "";
        }
        return editName.getText().toString();
    }

    @Override
    public String getSenderPhone() {
        if (StringUtils.isBlank(editPhone.getText().toString())) {
            return "";
        }
        return editPhone.getText().toString();
    }

    @Override
    public String getSenderCount() {
        if (StringUtils.isBlank(editSendCount.getText().toString())) {
            return "";
        }
        return editSendCount.getText().toString();
    }

    @Override
    public String getSenderWeight() {
        if (StringUtils.isBlank(editWeight.getText().toString())) {
            return "";
        }
        return editWeight.getText().toString();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(EditSendInfoActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWechatDialog() {
        new AlertDialog.Builder(this)
                .setTitle("报名成功")
                .setMessage("转发给更多的朋友或生成图片分享至朋友圈，即可加快集货进度~")
                .setPositiveButton("发送微信好友", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.doSignUp();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    @Override
    public void jumpQr() {
        Intent intent = new Intent(this, QrCodeActivity.class);
        intent.putExtra("id",groupId);
        intent.putExtra("market_id",marketId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign && mPresenter.checkRequiredFill()) {
            mPresenter.signOfStore();
        }
    }
}
