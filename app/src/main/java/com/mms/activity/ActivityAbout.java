package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.base.BaseSwipeActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
@ContentView(R.layout.layout_activity_about)
public class ActivityAbout extends BaseSwipeActivity implements View.OnClickListener {

    @InjectView(R.id.tv_activity_about_agreement)
    private TextView tvAgreement;

    @InjectView(R.id.btn_activity_about_back)
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvAgreement.setOnClickListener(this);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_activity_about_agreement:
                intent = new Intent(this, ActivityAgreement.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_about_back:
                finish();
                break;
        }
    }
}
