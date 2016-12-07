package com.yunfei.net;

import com.yunfei.entity.BaseResponse;
import com.yunfei.entity.NewItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yunfei on 2016/12/6.
 * email mayunfei6@gmail.com
 */

public interface NewsService {
    @GET("toutiao/index")
    Call<ResponseBody> getNewsUseJson(@Query("type") String type);

    @GET("toutiao/index")
    Observable<BaseResponse<List<NewItem>>> getNewsByType(@Query("type") String type);

}
