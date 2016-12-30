package com.yunfei.core.mvp.imple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.yunfei.core.R;
import com.yunfei.core.mvp.core.MvpFragment;
import com.yunfei.core.mvp.core.MvpPresenter;
import com.yunfei.lceelayout.LceeLayout;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public abstract class MvpLceeFragment<M, V extends MvpLceeView, P extends MvpPresenter<V>> extends MvpFragment<P> implements MvpLceeView<M> {

  protected LceeLayout mLceelayout;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mLceelayout = (LceeLayout) view.findViewById(R.id.lcee_layout);
  }

  @Override public void showContent() {
    mLceelayout.showContent();
  }

  @Override public void showEmpty() {
    mLceelayout.showEmpty();
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    mLceelayout.showError();
  }

  @Override public void showNetError() {
    mLceelayout.showNetError();
  }

  @Override public void showLoading(boolean pullToRefresh) {
    if (!pullToRefresh) {
      mLceelayout.showLoading();
    } else {
      showPullToRefresh();
    }
  }

  protected void showPullToRefresh() {
  }

  public LceeLayout getLceelayout() {
    return mLceelayout;
  }
}
