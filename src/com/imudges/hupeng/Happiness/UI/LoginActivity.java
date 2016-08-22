package com.imudges.hupeng.Happiness.UI;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.imudges.hupeng.Happiness.Listener.SimpleListener;
import com.imudges.hupeng.Happiness.bean.LoginModel;
import com.imudges.hupeng.Happiness.service.UserService;
import com.imudges.hupeng.Happiness.util.ToastUtil;

/**
 * Created by HUPENG on 2016/5/18.
 */
public class LoginActivity extends Activity {

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private TextView tv_foogetPasword;
//    private TextView tv_qqLogin;
    private TextView tv_register;
    private UserService userService;

    /**
     * 初始化变量以及设置监听
     * */
    private void initial(){
        // 在当前的activity中注册广播
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("ExitApp");
//        this.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                finish();
//            }
//        }, filter);

        userService = new UserService();


        et_username = (EditText) findViewById(R.id.userNameText);
        et_password = (EditText) findViewById(R.id.passwordText);
        bt_login = (Button) findViewById(R.id.LoginButton);
        tv_foogetPasword = (TextView)findViewById(R.id.forgetPassword);
//        tv_qqLogin = (TextView)findViewById(R.id.qq_login);
        tv_register = (TextView)findViewById(R.id.registLink);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (username.equals("") || password.equals("")){
                    ToastUtil.toast(LoginActivity.this, "用户名或者密码为空");
                }else {
                    bt_login.setEnabled(false);
                    bt_login.setBackgroundResource(R.drawable.button_unenable);
                    bt_login.setText("登录中...");
                    userService.login(username, password, LoginActivity.this, new SimpleListener() {
                        @Override
                        public void onSuccess() {
                            ToastUtil.toast(LoginActivity.this,"登录成功!");
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            LoginActivity.this.startActivity(intent);
                            LoginActivity.this.finish();
                        }

                        @Override
                        public void onFailure(String msg) {
                            ToastUtil.toast(LoginActivity.this,"登录失败，原因：" + msg);
                            bt_login.setBackgroundResource(R.drawable.button_blue);
                            bt_login.setText("登录");
                            bt_login.setEnabled(true);

                        }
                    });
                }

            }
        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initial();
    }


}
