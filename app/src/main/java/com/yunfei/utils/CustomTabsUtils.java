package com.yunfei.utils;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

/**
 * Created by yunfei on 2016/12/14.
 * email mayunfei6@gmail.com
 * 打开本地chrome
 */

public class CustomTabsUtils {
    private CustomTabsUtils() {

    }

    public static void startCustomTabs(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
