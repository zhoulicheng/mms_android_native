package com.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */

@ContentView(R.layout.layout_activity_setprivacy)
public class ActivitySetPrivacy extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_setprivacy_back)
    private Button btnBack;

    @InjectView(R.id.rl_activity_setprivacy_base)
    private RelativeLayout rlBase;

    @InjectView(R.id.rl_activity_setprivacy_mobilephone)
    private RelativeLayout rlMobilephone;

    @InjectView(R.id.rl_activity_setprivacy_telephone)
    private RelativeLayout rlTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();
    }

    private void setOCL(){
        btnBack.setOnClickListener(this);
        rlBase.setOnClickListener(this);
        rlMobilephone.setOnClickListener(this);
        rlTelephone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_setprivacy_back:
                finish();
                break;
            case R.id.rl_activity_setprivacy_base:
                break;
            case R.id.rl_activity_setprivacy_mobilephone:
                break;
            case R.id.rl_activity_setprivacy_telephone:
                break;
        }
    }
}
