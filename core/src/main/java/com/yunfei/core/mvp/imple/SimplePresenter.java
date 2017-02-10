package com.yunfei.core.mvp.imple;

import com.yunfei.core.mvp.core.MvpPresenter;
import com.yunfei.core.mvp.core.MvpView;

/**
 * Created by yunfei on 2017/1/12.
 * email mayunfei6@gmail.com
 */

public class SimplePresenter<V extends MvpView> implements MvpPresenter<V> {

  protected V mView;

  @Override public void attachView(V view) {
    mView = view;
  }

  @Override public void detachView() {
    mView = null;
  }

  protected V getMvpView() {
    return mView;
  }
}
