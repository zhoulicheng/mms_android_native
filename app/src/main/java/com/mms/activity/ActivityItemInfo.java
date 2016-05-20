package com.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/19.
 */
@ContentView(R.layout.layout_activity_item_info)
public class ActivityItemInfo extends BaseActivity {

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiveIntent();
        setView();
    }

    private void receiveIntent() {
        type = getIntent().getStringExtra("type");
    }

    private void setView() {
        switch (type) {
            //在这里根据type的值设定隐藏某些控件，并选择性的对某些控件赋值
            case "tudi":
                break;
            case "factory":
                break;
            case "depot":
                break;
            case "business":
                break;
            case "soho":
                break;
            case "other":
                break;
        }
    }

}
