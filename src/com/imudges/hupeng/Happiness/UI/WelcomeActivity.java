package com.imudges.hupeng.Happiness.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;



public class WelcomeActivity extends Activity {

    /**
     * 等待时间（毫秒）
     * */
    private static final long WAIT_TIME = 2000;

    /**
     * 跳转到主界面状态码
     * */
    private static final int GOHOME = 1000;

    /**
     * 跳转到登录界面状态码
     * */
    private static final int LOGIN = 1001;

    private void initial(){
//        /**
//         * 初始化Bmob
//         * */
//        Bmob.initialize(this, "72fdf214ae635095e2d815c0071cb6cb");
//
//        User oldUser = BmobUser.getCurrentUser(WelcomeActivity.this,User.class);
//
//        /**
//         * 判断用户是否已经成功登录过
//         * 成功登录后->MainActivity
//         * 没有成功登录->统一登录界面
//         * */
//        if (oldUser == null){
//            handler.sendEmptyMessageDelayed(LOGIN,WAIT_TIME);
//        }else {
//            handler.sendEmptyMessageDelayed(GOHOME,WAIT_TIME);
//        }


        handler.sendEmptyMessageDelayed(LOGIN,WAIT_TIME);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        initial();

    }



    /**
     * 接受不同的消息请求，做出处理
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOHOME:
                    goHome();
                    break;
                case LOGIN:
                    login();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void goHome(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }

    private void login(){
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }

}
