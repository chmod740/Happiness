package com.imudges.hupeng.Happiness.service;

import android.content.Context;
import com.google.gson.Gson;
import com.imudges.hupeng.Happiness.Listener.GetListener;
import com.imudges.hupeng.Happiness.Listener.SimpleListener;
import com.imudges.hupeng.Happiness.Listener.TokenListener;
import com.imudges.hupeng.Happiness.bean.CheckTokenModel;
import com.imudges.hupeng.Happiness.bean.LoginModel;

import com.imudges.hupeng.Happiness.bean.User;
import com.imudges.hupeng.Happiness.util.ConfigUtil;
import com.imudges.hupeng.Happiness.util.HttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * 用户服务类
 */
public class UserService {
    /**
     * 登录
     * */
    public void login(String phone_num, String password, Context context, SimpleListener simpleListener){
        String url = "login.html";
        String param = "phone_num=" + phone_num + "&password=" + password;
        RequestParams params = new RequestParams();
        params.add("phone_num",phone_num);
        params.add("password",password);
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String content = new String(bytes);
                try {
                    LoginModel loginModel = new Gson().fromJson(content,LoginModel.class);

                    if(loginModel.getResult() == 1){
                        //登录成功，记录下用户名，以及令牌
                        ConfigUtil.writeStr(context,"phone_num",phone_num);
                        ConfigUtil.writeStr(context,"token",loginModel.getToken());
                        //进行成功回调
                        simpleListener.onSuccess();
                        return;
                    }else {
                        simpleListener.onFailure("登录名或者密码不正确");
                        return;
                    }
                }catch (Exception e){
                    simpleListener.onFailure("发生未知错误");
                    return;
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                simpleListener.onFailure(SimpleListener.NET_ERROR);
                return;
            }
        });
    }

    /**
     * 得到当前已经登录的用户的信息
     * */
    public User getCurrentUser(Context context){
        Integer id = ConfigUtil.readInt(context,"id");
        String phone_num = ConfigUtil.readStr(context, "phone_num");
        String user_photo = ConfigUtil.readStr(context,"user_photo");
        String token = ConfigUtil.readStr(context,"token");
        String username  = ConfigUtil.readStr(context,"username");
        Integer lover_id = ConfigUtil.readInt(context,"lover_id");
        Integer gender = ConfigUtil.readInt(context,"gender");
        if (id == null || phone_num == null || user_photo == null || token == null || username == null || lover_id == null){
            return null;
        }
        User user = new User();
        user.setId(id);
        user.setPhone_num(phone_num);
        user.setUser_photo(user_photo);
        user.setToken(token);
        user.setUsername(username);
        user.setLover_id(lover_id);
        user.setGender(gender);
        return user;
    }

    /**
     * 验证用户的Token
     * */
    public void checkToken(Context context, String phone_num, String token, TokenListener tokenListener){
        String url = "check_token.html";
        RequestParams params  = new RequestParams();
        params.add("phone_num", phone_num);
        params.add("token", token);
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    CheckTokenModel checkTokenModel = new Gson().fromJson(new String(bytes),CheckTokenModel.class);
                    if (checkTokenModel.getResult() == 1){
                        tokenListener.onSuccess();
                        return;
                    }else{
                        tokenListener.onFailure(TokenListener.TOKEN_ERROR);
                        return;
                    }
                }catch (Exception e){
                    tokenListener.onNetError(SimpleListener.UNKNOWN_ERROR);
                    return;
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                tokenListener.onNetError(SimpleListener.NET_ERROR);
                return;
            }
        });
    }


    /**
     * 得到用户的信息
     * */
    public void getUserInfo(Context context, String phone_num, String token, GetListener getListener, Class clazz){
        String url = "get_user_info.html";
        RequestParams params = new RequestParams();
        params.add("phone_num", phone_num);
        params.add("token", token);

        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    User user = new Gson().fromJson(new String(bytes),User.class);

                    //在本地存储里面写入用户的登录信息
                    ConfigUtil.writeInt(context, "id", user.getId());
                    ConfigUtil.writeStr(context, "phone_num", user.getUser_photo());
                    ConfigUtil.writeStr(context, "user_photo", user.getUser_photo());
                    ConfigUtil.writeStr(context, "token", user.getToken());
                    ConfigUtil.writeStr(context, "username", user.getUsername());
                    ConfigUtil.writeInt(context, "lover_id", user.getLover_id());
                    ConfigUtil.writeInt(context, "gender", user.getGender());

                    getListener.onSuccess(user);
                    return;
                }catch (Exception e){
                    getListener.onFailure(SimpleListener.UNKNOWN_ERROR);
                    return;
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                getListener.onFailure(SimpleListener.NET_ERROR);
                return;
            }
        });

    }
}
