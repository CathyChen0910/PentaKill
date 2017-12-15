package com.sf.oarage.pentakillclient.editsendinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sf.oarage.pentakillclient.R;

/**
 * Created by liushihan on 2017/12/15.
 */

public class EditSendInfoActivity extends AppCompatActivity{
    TextView textAddressOfPicker;
    EditText editAddressDetail;
    EditText editName;
    EditText editPhone;
    EditText editSendCount;
    EditText editWeight;
    Button btnSign;
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
}
