package com.yunfei.core.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yunfei on 2016/12/23.
 * email mayunfei6@gmail.com
 */

public class NetUtil {

  public static Application mApplicationContent = null;

  public static void init(Application application) {
    mApplicationContent = application;
  }

  /**
   * 是否有网络
   */
  public static boolean isNetWorkAvilable() {
    checkApplication();
    ConnectivityManager connectivityManager = (ConnectivityManager) mApplicationContent.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * 查看是否有网络
   */
  public static boolean isWifi() {

    ConnectivityManager connectivityManager = (ConnectivityManager) mApplicationContent.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
    if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
      return false;
    }
    // 判断是不是连接的是不是wifi
    NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if (null != wifiInfo) {
      NetworkInfo.State state = wifiInfo.getState();
      if (null != state) {
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
          return true;
        }
      }
    }
    return true;
  }

  public static void checkApplication() {
    if (mApplicationContent == null) throw new IllegalArgumentException("please call init() in Application created()");
  }
}
