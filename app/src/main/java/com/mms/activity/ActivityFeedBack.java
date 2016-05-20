package com.mms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.dialog.MessageDialog;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;
import com.mms.widget.uploadImagesLayout.OperateImagesLayout;

import java.io.File;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/13.
 * 反馈意见界面
 */

@ContentView(R.layout.layout_activity_feedback)
public class ActivityFeedBack extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_feedback_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_feed_back_intro)
    private EditText etIntro;

    @InjectView(R.id.oil_activity_feed_back)
    private OperateImagesLayout imagesLayout;

    @InjectView(R.id.et_activity_feed_back_contact)
    private EditText etContact;

    @InjectView(R.id.btn_activity_feed_back_sub)
    private Button btnSub;

    private MessageDialog messageDialog;

    private View.OnTouchListener etLongTextOnTouchListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setOCL();

    }

    private void init() {
        messageDialog = new MessageDialog(this);
        if (Build.VERSION.SDK_INT >= 16) {
            btnSub.setBackground(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        } else {
            btnSub.setBackgroundDrawable(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        }
        etLongTextOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        };
    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        etIntro.setOnTouchListener(etLongTextOnTouchListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_feedback_back:
                messageDialog.setMessage("你确定要退出编辑吗？退出后将丢失已经添加或修改的内容。");
                messageDialog.setTitle("后退");
                messageDialog.setPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                messageDialog.show();
                break;
            case R.id.btn_activity_feed_back_sub:
                if (isLegal()) {
                    messageDialog.setTitle("提交");
                    messageDialog.setMessage("确认所填信息无误并提交吗？");
                    messageDialog.setPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sub();
                        }
                    });
                    messageDialog.show();
                }
                break;
        }
    }

    private boolean isLegal() {
        if (TextUtils.isEmpty(etIntro.getText().toString().trim())) {
            Utils.showToast(this, "请输入问题和意见");
            return false;
        }
        return true;
    }

    private void sub() {
        showLoadingDialog();
        cancelLoadingDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            messageDialog.setMessage("你确定要退出编辑吗？退出后将丢失已经添加或修改的内容。");
            messageDialog.setTitle("后退");
            messageDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            messageDialog.show();
            return false;

        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OperateImagesLayout.CAMERA_KEY:
                if (resultCode == RESULT_OK) {
                    File file = imagesLayout.getCurrentTmpFile();
                    if (file != null) {
                        Uri uri = Uri.fromFile(file);
                        imagesLayout.addImage(uri.toString());
                    }
                }
                break;
            case OperateImagesLayout.PHOTO_KEY:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imagesLayout.addImage(uri.toString());
                }
                break;
        }

    }

}
