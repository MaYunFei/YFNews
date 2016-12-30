package com.yunfei.core.mvp.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

  private Unbinder mUbinder;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentLayoutId());
    //直接在父类中填写，不用再在子类中
    mUbinder = ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mUbinder != null) {
      mUbinder.unbind();
    }
  }

  @LayoutRes protected abstract int getContentLayoutId();
}
