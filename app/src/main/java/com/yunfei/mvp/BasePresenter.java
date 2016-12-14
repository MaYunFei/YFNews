package com.yunfei.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yunfei on 2016/12/7.
 * email mayunfei6@gmail.com
 */

public abstract class BasePresenter<T extends IPresenter> implements IPresenter {
    protected T mView;

    public BasePresenter(T view) {
        mView = view;
    }

    //保证生命周期内存不泄露
    protected CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(s);
    }

    public void unSubscription() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

    @Override
    public void unsubscribe() {
        unSubscription();
        mView = null;
    }
}
