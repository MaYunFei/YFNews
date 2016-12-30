package com.yunfei.core.mvp.core;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public interface MvpPresenter<V extends MvpView> {
  public void attachView(V view);

  public void detachView();
}
