package com.sf.oarage.pentakillclient.network;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 主线程回调Callback
 * Created by spawn on 17-10-25.
 */

public abstract class MainThreadCallback implements Callback {

    private final MainHandler mainHandler;

    public MainThreadCallback() {
        mainHandler = new MainHandler();
    }

    @Override
    public void onSuccess(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            if (jsonObject.has("success")) {
                boolean success = jsonObject.optBoolean("success");
                if (success) {
                    Message message = Message.obtain();
                    message.what = MainHandler.WHAT_ON_SUCCESS;
                    message.obj = jsonObject.optString("result");
                    mainHandler.sendMessage(message);
                } else {
                    onError(new Throwable(jsonObject.optString("msg")));
                }

            }

        } catch (Exception e) {
            onError(e);

        }
    }

    @Override
    public void onError(Throwable t) {
        Message message = Message.obtain();
        message.what = MainHandler.WHAT_ON_ERROR;
        message.obj = t;
        mainHandler.sendMessage(message);
    }

    @SuppressLint("HandlerLeak")
    private class MainHandler extends Handler {

        private static final int WHAT_ON_SUCCESS = 834;
        private static final int WHAT_ON_ERROR = 443;

        private MainHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_ON_SUCCESS:
                    try {
                        onMainThreadSuccess((String) msg.obj);
                    } catch (JSONException e) {
                        //Ignore
                    }
                    break;
                case WHAT_ON_ERROR:
                    onMainThreadError((Throwable) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }

    public abstract void onMainThreadSuccess(String data) throws JSONException;

    public abstract void onMainThreadError(Throwable t);
}
