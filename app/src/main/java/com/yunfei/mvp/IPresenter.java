package com.yunfei.mvp;

/**
 * Created by yunfei on 2016/12/7.
 * email mayunfei6@gmail.com
 */

public interface IPresenter {
    //开启订阅
    void subscribe();

    //结束订阅
    void unsubscribe();
}
