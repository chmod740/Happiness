package com.imudges.hupeng.Happiness.Listener;

/**
 * 普通的监听器
 */
public interface SimpleListener {
    public static final String NET_ERROR = "网络请求失败,请检查网络配置";
    public static final String UNKNOWN_ERROR = "发生未知错误";

    public void onSuccess();
    public void onFailure(String msg);
}
