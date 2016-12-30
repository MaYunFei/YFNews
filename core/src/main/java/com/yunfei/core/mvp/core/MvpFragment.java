package com.yunfei.core.mvp.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public abstract class MvpFragment<P extends MvpPresenter> extends BaseFragment implements MvpView {

  protected P mPresenter;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = createPresenter();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.attachView(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  protected abstract P createPresenter();
}
