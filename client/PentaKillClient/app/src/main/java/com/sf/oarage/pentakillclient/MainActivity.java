package com.sf.oarage.pentakillclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sf.oarage.pentakillclient.network.Callback;
import com.sf.oarage.pentakillclient.network.MainThreadCallback;
import com.sf.oarage.pentakillclient.network.RestGetRequester;
import com.sf.oarage.pentakillclient.network.RestRequester;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.click);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RestGetRequester("http://10.2.4.31:8080/pentaKill/test/1").execute(new MainThreadCallback() {

                    @Override
                    public void onMainThreadSuccess(String data) throws JSONException {
                        Log.d("result",data);
                    }

                    @Override
                    public void onMainThreadError(Throwable t) {
                        Log.d("result",t.getMessage());
                    }
                });
            }
        });
    }
}
