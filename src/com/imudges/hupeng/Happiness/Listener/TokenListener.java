package com.imudges.hupeng.Happiness.Listener;

/**
 * Created by apollo on 8/22/16.
 */
public interface TokenListener extends SimpleListener{
    public static final String TOKEN_ERROR = "Token验证失败";

    public void onNetError(String msg);
}
