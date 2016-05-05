package com.mms.activity;

import android.os.Bundle;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.base.BaseSwipeActivity;

import roboguice.inject.ContentView;

/**
 * Created by Tanikawa on 2016/4/15.
 * 载体详细信息显示界面
 */

@ContentView(R.layout.layout_activity_carrier_info)
public class ActivityCarrierInfo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
