package com.sf.oarage.pentakillclient.base;

/**
 * Created by liushihan on 2017/12/14.
 */

public interface BasePresenter<T extends BaseView> {

    void start(T view);

}
