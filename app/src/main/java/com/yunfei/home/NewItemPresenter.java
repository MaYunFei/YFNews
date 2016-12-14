package com.yunfei.home;

import com.yunfei.entity.BaseResponse;
import com.yunfei.entity.NewItem;
import com.yunfei.net.NewsService;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 */

public class NewItemPresenter implements HomeContract.Presenter {

    private final HomeContract.View mNewListView;

    private final NewsService mNewsService;

    private final String mType;

    private CompositeSubscription mSubscriptions;


    public NewItemPresenter(HomeContract.View mNewListView, NewsService mNewsService, String type) {
        this.mNewListView = mNewListView;
        this.mNewsService = mNewsService;
        this.mType = type;
        mSubscriptions = new CompositeSubscription();
        //记得调用这个方法绑定
        mNewListView.setPresenter(this);
    }

    @Override
    public void getNews() {
        mNewListView.setLoadingIndicator(true);
        mNewsService.getNewsByType(mType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<List<NewItem>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseResponse<List<NewItem>> listBaseResponse) {
                        //private 为什么不行？
                        BaseResponse.ResultEntity<List<NewItem>> result = listBaseResponse.getResult();
                        List<NewItem> data = result.getData();
                        mNewListView.showNews(data);
                        mNewListView.setLoadingIndicator(false);
                    }
                });
    }

    @Override
    public void subscribe() {
        getNews();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
