package com.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.base.BaseSwipeActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 * 服务条款与免责协议界面
 */
@ContentView(R.layout.layout_activity_agreement)
public class ActivityAgreement extends BaseActivity implements View.OnClickListener{

    @InjectView(R.id.btn_activity_agreement_back)
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_agreement_back:
                finish();
                break;
        }
    }
}
