package com.yunfei.common.res;

/**
 * Created by yunfei on 2017/1/1.
 * email mayunfei6@gmail.com
 */

import com.yunfei.core.mvp.core.MvpView;

public interface ResView<M> extends MvpView {
  //显示绑定数据
  void setData(M data);

  void showEmpty();

  void showError(String error);

  void showNetError();

  void showContent();
}
