package com.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mms.R;
import com.mms.base.BaseSwipeActivity;
import com.mms.dialog.SelectDialog;
import com.mms.util.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 * 隐私设置界面
 */

@ContentView(R.layout.layout_activity_setprivacy)
public class ActivitySetPrivacy extends BaseSwipeActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_setprivacy_back)
    private Button btnBack;

    @InjectView(R.id.rl_activity_setprivacy_base)
    private RelativeLayout rlBase;

    @InjectView(R.id.tv_activity_setprivacy_base)
    private TextView tvBase;

    @InjectView(R.id.rl_activity_setprivacy_mobilephone)
    private RelativeLayout rlMobilephone;

    @InjectView(R.id.tv_activity_setprivacy_mobilephone)
    private TextView tvMobilephone;

    @InjectView(R.id.rl_activity_setprivacy_telephone)
    private RelativeLayout rlTelephone;

    @InjectView(R.id.tv_activity_setprivacy_telephone)
    private TextView tvTelephone;

    private List<String> items;
    private SelectDialog selectDialog;

    private AdapterView.OnItemClickListener baseListener;
    private AdapterView.OnItemClickListener mobilePhoneListener;
    private AdapterView.OnItemClickListener telephoneListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setOCL();
    }

    private void init() {
        items = new ArrayList<>();
        items.add("自己");
        items.add("好友");
        items.add("所有人");

        baseListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Utils.showToast(getApplicationContext(), "基本——自己");

                        break;
                    case 1:
                        Utils.showToast(getApplicationContext(), "基本——好友");
                        break;
                    case 2:
                        Utils.showToast(getApplicationContext(), "基本——所有人");
                        break;
                }
                tvBase.setText(items.get(i));
            }
        };

        mobilePhoneListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Utils.showToast(getApplicationContext(), "手机号——自己");

                        break;
                    case 1:
                        Utils.showToast(getApplicationContext(), "手机号——好友");
                        break;
                    case 2:
                        Utils.showToast(getApplicationContext(), "手机号——所有人");
                        break;
                }
                tvMobilephone.setText(items.get(i));
            }
        };

        telephoneListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Utils.showToast(getApplicationContext(), "座机——自己");

                        break;
                    case 1:
                        Utils.showToast(getApplicationContext(), "座机——好友");
                        break;
                    case 2:
                        Utils.showToast(getApplicationContext(), "座机——所有人");
                        break;
                }
                tvTelephone.setText(items.get(i));
            }
        };

        selectDialog = new SelectDialog(this, items);

    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        rlBase.setOnClickListener(this);
        rlMobilephone.setOnClickListener(this);
        rlTelephone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_setprivacy_back:
                finish();
                break;
            case R.id.rl_activity_setprivacy_base:
                selectDialog.setOnItemClickListener(baseListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_setprivacy_mobilephone:
                selectDialog.setOnItemClickListener(mobilePhoneListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_setprivacy_telephone:
                selectDialog.setOnItemClickListener(telephoneListener);
                selectDialog.show();
                break;
        }
    }
}
