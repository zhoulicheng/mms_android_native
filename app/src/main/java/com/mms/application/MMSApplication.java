package com.mms.application;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;

import com.mms.R;
import com.mms.dao.base.DaoManager;
import com.mms.imageLoader.ImageLoader;
import com.mms.util.AppSetting;

/**
 * Created by Tanikawa on 2016/5/11.
 */
public class MMSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //AppSetting
        AppSetting.init(getApplicationContext());
        //ImageLoader图片加载
        ImageLoader.initConfig(getApplicationContext(), new ColorDrawable(getResources().getColor(R.color.divideLineGray)));
        //GreenDao本地数据库
        DaoManager.initGreenDao(getApplicationContext());

    }
}
