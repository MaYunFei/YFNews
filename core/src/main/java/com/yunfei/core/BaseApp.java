package com.yunfei.core;

import android.app.Application;
import butterknife.ButterKnife;
import com.yunfei.core.utils.NetUtil;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public class BaseApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    ButterKnife.setDebug(BuildConfig.DEBUG);
    NetUtil.init(this);
  }
}
