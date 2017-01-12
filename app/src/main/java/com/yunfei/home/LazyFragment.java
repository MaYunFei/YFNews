package com.yunfei.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.yunfei.common.res.MvpResFragment;
import com.yunfei.common.res.ResPresenter;

/**
 * Created by yunfei on 2017/1/12.
 * email mayunfei6@gmail.com
 */

public abstract class LazyFragment<M, P extends ResPresenter> extends MvpResFragment<M, P> {

  private boolean isInit = false;//真正要显示的View是否已经被初始化（正常加载）
  private boolean isViewCreated;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    isViewCreated = true;
    if (getUserVisibleHint() && !isInit) {
      lazyLoad();
      isInit = true;
    }
  }

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser && !isInit && isViewCreated) {
      lazyLoad();
      isInit = true;
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    isViewCreated = false;
  }

  protected abstract void lazyLoad();
}
