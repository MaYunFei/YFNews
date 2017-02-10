package com.yunfei.core.mvp.imple;

import android.support.annotation.NonNull;
import com.yunfei.core.mvp.core.MvpView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yunfei on 2016/12/20.
 * email mayunfei6@gmail.com
 */

public abstract class RxPresenter<V extends MvpView> extends SimplePresenter<V> {

  protected CompositeSubscription mSubscriptions;

  protected void addSuscription(@NonNull Subscription s) {
    if (mSubscriptions == null) {
      mSubscriptions = new CompositeSubscription();
    }

    mSubscriptions.add(s);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscriptions != null) {
      mSubscriptions.clear();
    }
  }
}
