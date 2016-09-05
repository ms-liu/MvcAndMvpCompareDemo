package com.demo.ms.mvcandmvpcomparedemo.uitls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 这是一个用于将数据缓存到本地的工具类
 *
 * @author Administrator
 */
public class SharedPreferencesUtils {

    public static final String SP_NAME = "config";

    private static SharedPreferences sp;

    /**
     * 缓存int型数据
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putInt(key, value).apply();
    }





    /**
     * 缓存字符串数据
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        sp.edit().putString(key, value).apply();
    }

    /**
     * 根据键获取字符串型的值
     *
     * @param context 上下文
     * @param key     键
     * @return value 值
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return  sp.getString(key, defValue);
    }


    /**
     * 根据键获取int型的值
     *
     * @param context 上下文
     * @param key     键
     * @return value 值
     */
    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getInt(key, defValue);
    }


    /**
     * 根据键获取布尔型的值
     *
     * @param context 上下文
     * @param key     键
     * @return value 值
     */
    public static Boolean getBoolean(Context context, String key, Boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getBoolean(key, defValue);
    }

}
