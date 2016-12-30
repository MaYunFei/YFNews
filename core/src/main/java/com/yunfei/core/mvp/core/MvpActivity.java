package com.yunfei.core.mvp.core;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public abstract class MvpActivity<P extends MvpPresenter> extends BaseActivity implements MvpView {

  protected P mPresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = createPresenter();
    mPresenter.attachView(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  protected abstract P createPresenter();
}
