package com.yunfei.home;

import com.yunfei.entity.NewItem;
import com.yunfei.mvp.IPresenter;
import com.yunfei.mvp.IView;

import java.util.List;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 */

public interface HomeContract {
    interface View extends IView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showNews(List<NewItem> newItems);
    }

    interface Presenter extends IPresenter {
        void getNews();
    }
}
