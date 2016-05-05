package com.mms.activity;

import android.os.Bundle;
import android.view.View;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.base.BaseSwipeActivity;

import roboguice.inject.ContentView;

/**
 * Created by Tanikawa on 2016/4/13.
 * 反馈意见界面
 */

@ContentView(R.layout.layout_activity_feedback)
public class ActivityFeedBack extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onClick(View view) {

    }
}
