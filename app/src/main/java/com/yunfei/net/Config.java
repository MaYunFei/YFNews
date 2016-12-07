package com.yunfei.net;

/**
 * Created by yunfei on 2016/12/6.
 * email mayunfei6@gmail.com
 */

public class Config {
    private Config() {

    }
    static final String APP_KEY = "fa03027fa13142d25d6e727bca82288e";
    static final String BASE_RUL = "http://v.juhe.cn/";

    /**
     * top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    public static class REQUEST_TYPE{
        public static String TOP = "top";
        public static String SHEHUI = "shehui";
        public static String GUONEI = "guonei";
        public static String GUOJI = "guoji";
        public static String YULE = "yule";
        public static String TIYU = "tiyu";
        public static String JUNSHI = "junshi";
        public static String KEJI = "keji";
        public static String CAIJING = "caijing";
        public static String SHISHANG = "shishang";
    }

}
