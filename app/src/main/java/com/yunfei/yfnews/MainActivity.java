package com.yunfei.yfnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yunfei.entity.BaseResponse;
import com.yunfei.entity.NewItem;
import com.yunfei.net.ApiClient;
import com.yunfei.net.NewsService;
import com.yunfei.utils.L;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yunfei.net.Config.REQUEST_TYPE.TOP;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiClient.retrofit().create(NewsService.class)
                .getNewsByType(TOP)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse<List<NewItem>>>() {
                    @Override
                    public void call(BaseResponse<List<NewItem>> listBaseResponse) {
                        L.i(listBaseResponse.getReason());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        L.i(throwable.getMessage());
                    }
                })
        ;
    }




    public void test(){

    }
}
