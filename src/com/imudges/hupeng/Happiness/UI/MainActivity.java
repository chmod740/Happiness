package com.imudges.hupeng.Happiness.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imudges.hupeng.Happiness.Listener.GetListener;
import com.imudges.hupeng.Happiness.Listener.TokenListener;
import com.imudges.hupeng.Happiness.service.UserService;
import com.imudges.hupeng.Happiness.util.ConfigUtil;
import com.imudges.hupeng.Happiness.util.IntentUtil;
import com.imudges.hupeng.Happiness.util.ToastUtil;

public class MainActivity extends Activity implements OnClickListener,OnPageChangeListener{

    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_address;
    private LinearLayout ll_friend;
    private LinearLayout ll_setting;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_address;
    private ImageView iv_friend;
    private ImageView iv_setting;

    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_address;
    private TextView tv_friend;
    private TextView tv_setting;

    // 中间内容区域
    private ViewPager viewPager;

    // ViewPager适配器ContentAdapter
    private ContentAdapter adapter;

    private List<View> views;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();

    }

    /**
     * 验证用户的身份
     * */
    private void init(){
        if (userService == null){
            String token = ConfigUtil.readStr(MainActivity.this,"token");
            String phone_num = ConfigUtil.readStr(MainActivity.this, "phone_num");


            userService = new UserService();
            userService.checkToken(MainActivity.this, phone_num, token, new TokenListener() {
                @Override
                public void onNetError(String msg) {
                    ToastUtil.toast(MainActivity.this, "连接服务器失败，请检查网络配置");
                }

                @Override
                public void onSuccess() {
                    userService.getUserInfo(MainActivity.this, phone_num, token, new GetListener() {
                        @Override
                        public void onSuccess(Object obj) {

                        }

                        @Override
                        public void onFailure(String msg) {

                        }
                    });
                }

                @Override
                public void onFailure(String msg) {
                    ToastUtil.toast(MainActivity.this, "令牌验证失败，请重新登录");
                    IntentUtil.jump(MainActivity.this, LoginActivity.class);
                    MainActivity.this.finish();
                }
            });
        }


    }


    private void initEvent() {
        // 设置按钮监听
        ll_home.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_setting.setOnClickListener(this);

        //设置ViewPager滑动监听
        viewPager.setOnPageChangeListener(this);
    }

    private void initView() {

        // 底部菜单4个Linearlayout
        this.ll_home = (LinearLayout) findViewById(R.id.ll_home);
        this.ll_address = (LinearLayout) findViewById(R.id.ll_address);
        this.ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
        this.ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        // 底部菜单4个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_address = (ImageView) findViewById(R.id.iv_address);
        this.iv_friend = (ImageView) findViewById(R.id.iv_friend);
        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);

        // 底部菜单4个菜单标题
        this.tv_home = (TextView) findViewById(R.id.tv_home);
        this.tv_address = (TextView) findViewById(R.id.tv_address);
        this.tv_friend = (TextView) findViewById(R.id.tv_friend);
        this.tv_setting = (TextView) findViewById(R.id.tv_setting);

        // 中间内容区域ViewPager
        this.viewPager = (ViewPager) findViewById(R.id.vp_content);

        // 适配器
        View page_01 = View.inflate(MainActivity.this, R.layout.page_0, null);
        View page_02 = View.inflate(MainActivity.this, R.layout.page_1, null);
        View page_03 = View.inflate(MainActivity.this, R.layout.page_2, null);
        View page_04 = View.inflate(MainActivity.this, R.layout.page_3, null);

        views = new ArrayList<View>();
        views.add(page_01);
        views.add(page_02);
        views.add(page_03);
        views.add(page_04);

        this.adapter = new ContentAdapter(views);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_home:
                //iv_home.setImageResource(R.drawable.tab_weixin_pressed);
                tv_home.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_address:
                //iv_address.setImageResource(R.drawable.tab_address_pressed);
                tv_address.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_friend:
                //iv_friend.setImageResource(R.drawable.tab_find_frd_pressed);
                tv_friend.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_setting:
                //iv_setting.setImageResource(R.drawable.tab_find_frd_pressed);
                tv_setting.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(3);
                break;

            default:
                break;
        }

    }

    private void restartBotton() {
        // ImageView置为灰色
//        iv_home.setImageResource(R.drawable.tab_weixin_normal);
//        iv_address.setImageResource(R.drawable.tab_address_normal);
//        iv_friend.setImageResource(R.drawable.tab_find_frd_normal);
//        iv_setting.setImageResource(R.drawable.tab_settings_normal);
        // TextView置为白色
        tv_home.setTextColor(0xffffffff);
        tv_address.setTextColor(0xffffffff);
        tv_friend.setTextColor(0xffffffff);
        tv_setting.setTextColor(0xffffffff);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        restartBotton();
        //当前view被选择的时候,改变底部菜单图片，文字颜色
        switch (arg0) {
            case 0:
//                iv_home.setImageResource(R.drawable.tab_weixin_pressed);
                tv_home.setTextColor(0xff1B940A);
                break;
            case 1:
//                iv_address.setImageResource(R.drawable.tab_address_pressed);
                tv_address.setTextColor(0xff1B940A);
                break;
            case 2:
//                iv_friend.setImageResource(R.drawable.tab_find_frd_pressed);
                tv_friend.setTextColor(0xff1B940A);
                break;
            case 3:
//                iv_setting.setImageResource(R.drawable.tab_find_frd_pressed);
                tv_setting.setTextColor(0xff1B940A);
                break;

            default:
                break;
        }

    }


    /**
     * 创建菜单项
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,Menu.FIRST + 1, 1, "退出登录");
        menu.add(Menu.NONE,Menu.FIRST + 2, 2, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case Menu.FIRST + 1:
                //清除登录信息
                ConfigUtil.clear(MainActivity.this);
                //跳转回登录页面
                IntentUtil.jump(MainActivity.this, LoginActivity.class);
                MainActivity.this.finish();
                break;
            case Menu.FIRST + 2:
                ToastUtil.toast(MainActivity.this, "2");
                MainActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
