package com.imudges.hupeng.Happiness.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HUPENG on 2016/8/20.
 * 进行配置文件读取的java类
 */
public class ConfigUtil {
    private static final String configFileName = "config";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    public static String readStr(Context context,String key){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }

    public static Integer readInt(Context context,String key){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        Integer value =  sharedPreferences.getInt(key,Integer.MIN_VALUE);
        if (value == Integer.MIN_VALUE) {
            return null;
        }else {
            return value;
        }
    }

    public static void writeStr(Context context, String key,String value){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        if (editor == null)
            editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void writeInt(Context context, String key,Integer value){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        if (editor == null)
            editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static void clear(Context context){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        if (editor == null)
            editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
