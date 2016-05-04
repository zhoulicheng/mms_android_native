package com.mms.base;

import android.os.Bundle;

import com.mms.R;
import com.mms.dialog.LoadingDialog;
import com.mms.util.StatusBarUtils;

import roboguice.activity.RoboActivity;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class BaseActivity extends RoboActivity {

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
    }

    //沉浸式状态栏
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }
    protected void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }

    public void showLoadingDialog(String msg) {
        loadingDialog.show(msg);
    }

    public void showLoadingDialog() {
        loadingDialog.show();
    }

    public void cancelLoadingDialog() {
        loadingDialog.cancel();
    }


}
