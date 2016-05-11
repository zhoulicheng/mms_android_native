package com.mms.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cwj on 16/3/7.
 * 应用设置(SharedPreference)
 */
@SuppressLint("CommitPrefEdits")
public class AppSetting {

    private static final String SETTING_SP_NAME = "setting";

    private static final String START_COUNT_KEY = "startCount";
    private static final String CITY_KEY = "city";

    private static SharedPreferences sharedPreferences;

    /**
     * 必须初始化
     *
     * @param context
     */
    public static void init(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(SETTING_SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 进入应用次数
     */
    public static int getStartCount() {
        return sharedPreferences.getInt(START_COUNT_KEY, 0);
    }

    /**
     * 进入一次应用
     */
    public static void addStartCount() {
        int newCount = getStartCount() + 1;
        sharedPreferences.edit().putInt(START_COUNT_KEY, newCount).commit();
    }

}
