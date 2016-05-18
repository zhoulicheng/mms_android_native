package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.widget.TitlePopup.ActionItem;
import com.mms.widget.TitlePopup.TitlePopup;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/16.
 */
@ContentView(R.layout.layout_activity_onlineoffice_worklog)
public class ActivityOnlineOfficeWorkLog extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_worklog_back)
    private Button btnBack;

    @InjectView(R.id.btn_activity_worklog_add)
    private Button btnAdd;

    private Intent intent;
    //定义标题栏弹窗
    private TitlePopup titlePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();
    }

    private void setOCL(){
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.addAction(new ActionItem(this, "添加计划", R.drawable.add_item));
        titlePopup.addAction(new ActionItem(this, "添加总结", R.drawable.add_carrier));
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position){
                    case 0:
                        intent.putExtra("type","plan");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("type","summary");
                        startActivity(intent);
                        break;
                }
            }
        });
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_worklog_back:
                finish();
                break;
            case R.id.btn_activity_worklog_add:
                intent = new Intent(this,ActivityWorkLogImport.class);
                titlePopup.show(view);
                break;
        }
    }
}
