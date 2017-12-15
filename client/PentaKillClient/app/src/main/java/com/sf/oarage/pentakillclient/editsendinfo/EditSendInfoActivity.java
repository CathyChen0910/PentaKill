package com.sf.oarage.pentakillclient.editsendinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sf.oarage.pentakillclient.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sendinfo);
        textAddressOfPicker = findViewById(R.id.textAddress);
        editAddressDetail = findViewById(R.id.textAddressDetail);
        editName = findViewById(R.id.edit_sender_name);
        editPhone = findViewById(R.id.edit_send_count);
        editSendCount = findViewById(R.id.edit_send_count);
        editWeight = findViewById(R.id.edit_single_weight);
        btnSign = findViewById(R.id.btn_sign);
    }

    @Override
    public void setPresenter(EditSendInfoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(EditSendInfoActivity.this,"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign:
                if (mPresenter.checkRequiredFill()){
                    mPresenter.signOfStore();
                }
                break;
        }
    }
}
