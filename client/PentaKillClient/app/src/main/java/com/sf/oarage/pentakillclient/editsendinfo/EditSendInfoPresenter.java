package com.sf.oarage.pentakillclient.editsendinfo;


import com.sf.oarage.pentakillclient.editsendinfo.data.remote.EditSendInfoModel;
import com.sf.oarage.pentakillclient.editsendinfo.data.remote.EditSendInfoModelImpl;
import com.sf.oarage.pentakillclient.utils.StringUtils;

/**
 * Created by liushihan on 2017/12/15.
 */

public class EditSendInfoPresenter implements EditSendInfoContract.Presenter {
    EditSendInfoContract.View mView;
    EditSendInfoModel editSendInfoModel;

    @Override
    public void start(EditSendInfoContract.View view) {
        this.mView = view;
        editSendInfoModel = new EditSendInfoModelImpl();
    }

    @Override
    public void signOfStore() {
        mView.showWechatDialog();
    }

    @Override
    public boolean checkRequiredFill() {
        if (StringUtils.isEmpty(mView.getAddress())){
            mView.showToast("未选择地址");
            return false;
        }
        if (StringUtils.isEmpty(mView.getAddressDetail())){
            mView.showToast("未填写详细地址");
            return false;
        }
        if (!StringUtils.isEmpty(mView.getAddressDetail()) && mView.getAddressDetail().length()<5){
            mView.showToast("详细地址最少5位");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderName())){
            mView.showToast("寄件人姓名为空");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderPhone())){
            mView.showToast("手机号为空");
            return false;
        }
        if (!StringUtils.isEmpty(mView.getSenderPhone()) && !(StringUtils.isChinaPhoneLegal(mView.getSenderPhone()) || StringUtils.isHKPhoneLegal(mView.getSenderPhone()))){
            mView.showToast("手机号码不正确");
            return false;
        }
        if (StringUtils.isEmpty(mView.getSenderWeight())){
            mView.showToast("未填写重量");
            return false;
        }
        return true;
    }


}
