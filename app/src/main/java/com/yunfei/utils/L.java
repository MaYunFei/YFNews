package com.yunfei.utils;

import android.util.Log;

/**
 * Created by yunfei on 2016/12/6.
 * email mayunfei6@gmail.com
 */

public class L {
    private static final String TAG = "yunfei";

    private L() {
    }

    public static void i(String TAG, String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }
}
