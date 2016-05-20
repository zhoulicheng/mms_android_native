package com.mms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mms.R;
import com.mms.base.BaseActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/15.
 * 载体详细信息显示界面
 */

@ContentView(R.layout.layout_activity_carrier_info)
public class ActivityCarrierInfo extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_carrier_info_back)
    private Button btnBack;

    @InjectView(R.id.ll_activity_carrier_info_tudi)
    private LinearLayout llTudi;

    @InjectView(R.id.ll_activity_carrier_info_factory)
    private LinearLayout llFactory;

    @InjectView(R.id.ll_activity_carrier_info_depot)
    private LinearLayout llDepot;

    @InjectView(R.id.ll_activity_carrier_info_soho)
    private LinearLayout llSoho;

    @InjectView(R.id.ll_activity_carrier_info_business)
    private LinearLayout llBusiness;

    @InjectView(R.id.btn_activity_carrier_info_call)
    private Button btnCall;

    @InjectView(R.id.btn_activity_carrier_info_cloud)
    private Button btnCloud;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiveIntent();
        setView();
        setOCL();
    }

    private void setOCL(){
        btnBack.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnCloud.setOnClickListener(this);
    }

    private void receiveIntent() {
        type = getIntent().getStringExtra("type");
    }

    private void setView() {
        switch (type) {
            //在这里根据type的值设定隐藏某些控件，并选择性的对某些控件赋值
            case "tudi":
                llTudi.setVisibility(View.VISIBLE);
                break;
            case "factory":
                llFactory.setVisibility(View.VISIBLE);
                break;
            case "depot":
                llDepot.setVisibility(View.VISIBLE);
                break;
            case "business":
                llBusiness.setVisibility(View.VISIBLE);
                break;
            case "soho":
                llSoho.setVisibility(View.VISIBLE);
                break;
            case "other":
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_activity_carrier_info_back:
                break;
            case R.id.btn_activity_carrier_info_call:
                break;
            case R.id.btn_activity_carrier_info_cloud:
                intent = new Intent(this, ActivityCarrierCloud.class);
                startActivity(intent);
                break;
        }
    }
}
