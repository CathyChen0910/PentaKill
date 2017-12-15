package com.sf.oarage.pentakillclient.network;

/**
 * Created by Cathy on 2017/12/15.
 */

public class BaseBean {

    private boolean success;
    private String result;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "success=" + success +
                ", result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
