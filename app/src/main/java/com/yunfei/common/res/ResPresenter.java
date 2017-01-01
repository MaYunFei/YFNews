package com.yunfei.common.res;

import com.yunfei.core.mvp.imple.RxPresenter;
import rx.functions.Action1;

/**
 * Created by yunfei on 2017/1/1.
 * email mayunfei6@gmail.com
 */

public class ResPresenter<M, V extends ResView<M>> extends RxPresenter<V> {

  public Action1<M> getResNextAction() {
    return new Action1<M>() {
      @Override public void call(M m) {
        getMvpView().setData(m);
        getMvpView().showContent();
      }
    };
  }
}
