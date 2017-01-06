package com.yunfei.home;

import com.yunfei.entity.NewItem;
import com.yunfei.net.ApiClient;
import com.yunfei.net.BaseResponse;
import com.yunfei.net.NewsService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import rx.functions.Action1;

/**
 * Created by yunfei on 2017/1/5.
 * email mayunfei6@gmail.com
 */
public class NewItemPresenterTest {
  @Before public void setUp() throws Exception {

  }

  @Test public void getNews() throws Exception {
    NewsService newsService = ApiClient.retrofit().create(NewsService.class);
    newsService.getNewsByType("top").subscribe(new Action1<BaseResponse<List<NewItem>>>() {
      @Override public void call(BaseResponse<List<NewItem>> listBaseResponse) {
      }
    });
  }
}