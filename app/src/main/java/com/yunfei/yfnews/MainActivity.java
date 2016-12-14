package com.yunfei.yfnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ApiClient.retrofit().create(NewsService.class)
//                .getNewsByType(TOP)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<BaseResponse<List<NewItem>>>() {
//                    @Override
//                    public void call(BaseResponse<List<NewItem>> listBaseResponse) {
//                        L.i(listBaseResponse.getReason());
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        L.i(throwable.getMessage());
//                    }
//                })
//        ;

//        NewItemFragment newItemFragment = (NewItemFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);
//        if (newItemFragment == null) {
//            newItemFragment = NewItemFragment.getNewInstance();
//            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, newItemFragment).commit();
//        }
//        NewItemPresenter presenter = new NewItemPresenter(newItemFragment, ApiClient.retrofit().create(NewsService.class), "top");


    }


    public void test() {

    }
}
