package com.mms.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mms.R;
import com.mms.adapter.FragmentTabAdapter;
import com.mms.base.BaseFragment;
import com.mms.util.DrawableUtils;
import com.mms.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/16.
 */
public class ActivityWorkLogImport extends RoboFragmentActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_worklog_import_back)
    private Button btnBack;

    private RadioGroup rg;

    @InjectView(R.id.rb_activity_worklog_import_plan)
    private RadioButton rbPlan;

    @InjectView(R.id.rb_activity_worklog_import_summary)
    private RadioButton rbSummary;

    //沉浸式状态栏
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }

    private List<BaseFragment> fragments = null;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_onlineoffice_worklog_import);
        receiveIntent();
        initView();
        setOCL();
    }

    private void receiveIntent(){
        type = getIntent().getStringExtra("type");
    }

    private void initView() {
        // TODO Auto-generated method stub
        fragments = new ArrayList<>();
        fragments.add(new FragmentAddPlan());
        fragments.add(new FragmentAddSummary());
        rg = (RadioGroup) findViewById(R.id.rg_activity_worklog_import);
        if (Build.VERSION.SDK_INT >= 16) {
            rg.setBackground(DrawableUtils.getDrawable(5, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.white)));
        } else {
            rg.setBackgroundDrawable(DrawableUtils.getDrawable(5, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.white)));
        }

        if (type.equals("summary")){
            new FragmentTabAdapter(this, fragments, R.id.activity_worklog_tab_content, rg,1);
            rbSummary.setTextColor(getResources().getColor(R.color.colorPrimary));
            rbPlan.setTextColor(getResources().getColor(R.color.white));
            rbSummary.setBackgroundColor(getResources().getColor(R.color.white));
            rbPlan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            rbSummary.setChecked(true);
        }else {
            new FragmentTabAdapter(this, fragments, R.id.activity_worklog_tab_content, rg,0);
        }
    }


    private void setOCL() {
        btnBack.setOnClickListener(this);
        rbPlan.setOnClickListener(this);
        rbSummary.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        for (int i = 0; i < fragments.size(); i++) {
            fragments.get(i).onResume();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_activity_worklog_import_plan:
                rbPlan.setTextColor(getResources().getColor(R.color.colorPrimary));
                rbSummary.setTextColor(getResources().getColor(R.color.white));
                rbPlan.setBackgroundColor(getResources().getColor(R.color.white));
                rbSummary.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.rb_activity_worklog_import_summary:
                rbSummary.setTextColor(getResources().getColor(R.color.colorPrimary));
                rbPlan.setTextColor(getResources().getColor(R.color.white));
                rbSummary.setBackgroundColor(getResources().getColor(R.color.white));
                rbPlan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }
}
