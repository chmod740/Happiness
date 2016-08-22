package com.imudges.hupeng.Happiness.util;

import android.content.Context;
import android.content.Intent;

/**
 * 页面跳转通用类
 * Created by HUPENG on 2016/8/21.
 */
public class IntentUtil {
    public static void jump(Context context, Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }
}
