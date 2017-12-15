package com.sf.oarage.pentakillclient.editsendinfo;


import android.util.Log;

import com.google.gson.Gson;
import com.sf.oarage.pentakillclient.editsendinfo.data.remote.EditSendInfoModel;
import com.sf.oarage.pentakillclient.editsendinfo.data.remote.EditSendInfoModelImpl;
import com.sf.oarage.pentakillclient.editsendinfo.data.remote.SignUpBean;
import com.sf.oarage.pentakillclient.network.Callback;
import com.sf.oarage.pentakillclient.utils.StringUtils;

/**
 * Created by liushihan on 2017/12/15.
 */

public class EditSendInfoPresenter implements EditSendInfoContract.Presenter {
    EditSendInfoContract.View mView;
    EditSendInfoModel editSendInfoModel;
    Long GroupId;

    @Override
    public void start(EditSendInfoContract.View view) {
        this.mView = view;
        view.setPresenter(this);
        editSendInfoModel = new EditSendInfoModelImpl();
    }

    @Override
    public void setGroupId(Long gid) {
        this.GroupId = gid;
    }


    @Override
    public void doSignUp() {
        editSendInfoModel.doSign("", new Callback() {
            @Override
            public void onSuccess(String data) {
                Log.d("success", data);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    @Override
    public void signOfStore() {
        mView.showWechatDialog();
    }

    @Override
    public boolean checkRequiredFill() {
        if (StringUtils.isEmpty(mView.getAddress())) {
            mView.showToast("未选择地址");
            return false;
        }
        if (StringUtils.isEmpty(mView.getAddressDetail())) {
            mView.showToast("未填写详细地址");
            return false;
        }
        if (!StringUtils.isEmpty(mView.getAddressDetail()) && mView.getAddressDetail().length() < 5) {
            mView.showToast("详细地址最少5位");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderName())) {
            mView.showToast("寄件人姓名为空");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderPhone())) {
            mView.showToast("手机号为空");
            return false;
        }
        if (!StringUtils.isEmpty(mView.getSenderPhone()) && !(StringUtils.isChinaPhoneLegal(mView.getSenderPhone()) || StringUtils.isHKPhoneLegal(mView.getSenderPhone()))) {
            mView.showToast("手机号码不正确");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderWeight())) {
            mView.showToast("未填写重量");
            return false;
        }
        return true;
    }

    @Override
    public String getDataOfDetail() {
        SignUpBean signUpBean = new SignUpBean();
        signUpBean.setGroupId(GroupId);
        signUpBean.setUserAddress(mView.getAddress()+mView.getAddressDetail());
        signUpBean.setUserCellphone(mView.getSenderPhone());
        signUpBean.setWeightPerDay(StringUtils.toDouble(mView.getSenderWeight()));
        signUpBean.setNumPerDay(StringUtils.toInt(mView.getSenderCount(),0));
        signUpBean.setUserName(mView.getSenderName());
        Gson gson = new Gson();
        String jsonStr = gson.toJson(signUpBean);
        return jsonStr;
    }


}
