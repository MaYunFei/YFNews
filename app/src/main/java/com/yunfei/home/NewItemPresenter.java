package com.yunfei.home;

import com.yunfei.core.mvp.imple.RxPresenter;
import com.yunfei.entity.NewItem;
import com.yunfei.net.BaseResponse;
import com.yunfei.net.NewsService;
import com.yunfei.net.RetrofitUtil;
import com.yunfei.net.ServerException;
import com.yunfei.utils.L;
import java.util.List;
import java.util.concurrent.TimeoutException;
import rx.functions.Action1;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 *
 * 可以添加的逻辑 记录请求时间 再一段时间内再去请求
 * 根据是否有数据请求
 */

public class NewItemPresenter extends RxPresenter<NiewItemView> {

  private final NewsService mNewsService;
  public static int Count = 0;
  private final String mType;

  //应该判断是否为第一次 选择 显示的 loading
  public NewItemPresenter(NewsService mNewsService, String type) {
    this.mNewsService = mNewsService;
    this.mType = type;
    Count++;
    L.i("fragment count = " + Count);
  }

  public void getNews() {
    addSuscription(mNewsService.getNewsByType(mType).compose(RetrofitUtil.<List<NewItem>, BaseResponse<List<NewItem>>>getSimpleHttpTransformer()).subscribe(new Action1<List<NewItem>>() {
      @Override public void call(List<NewItem> data) {
        getMvpView().setData(data);
        getMvpView().showContent();
      }
    }, new Action1<Throwable>() {
      @Override public void call(Throwable throwable) {
        if (throwable instanceof ServerException) {
          L.i("服务错误" + throwable.getMessage());
        } else if (throwable instanceof TimeoutException) {
          L.i("超时。。。");
        } else {

        }
      }
    }));
  }
}
