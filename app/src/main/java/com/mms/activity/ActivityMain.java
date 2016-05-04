package com.mms.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mms.R;
import com.mms.adapter.FragmentTabAdapter;
import com.mms.base.BaseFragment;
import com.mms.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

/**
 * 主界面
 */
public class ActivityMain extends RoboFragmentActivity {

    //沉浸式状态栏
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }
    protected void setStatusBar() {
        StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }

    private List<BaseFragment> fragments = null;

    private RadioGroup radioGroup;

    @InjectView(R.id.radioButtonHome)
    private RadioButton rb1;
    private Drawable drawable1;

    @InjectView(R.id.radioButtonItem)
    private RadioButton rb2;
    private Drawable drawable2;

    @InjectView(R.id.radioButtonCarrier)
    private RadioButton rb3;
    private Drawable drawable3;

    @InjectView(R.id.radioButtonMy)
    private RadioButton rb4;
    private Drawable drawable4;

    private static Boolean isExit = false;
    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        fragments = new ArrayList<BaseFragment>();
        fragments.add(new FragmentMain());
        fragments.add(new FragmentItem());
        fragments.add(new FragmentCarrier());
        fragments.add(new FragmentMy());

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        drawable1 = getResources().getDrawable(R.drawable.homeselector);
        drawable1.setBounds(0,0,65,65);
        rb1.setCompoundDrawables(null,drawable1,null,null);

        drawable2 = getResources().getDrawable(R.drawable.itemselector);
        drawable2.setBounds(0,0,65,65);
        rb2.setCompoundDrawables(null,drawable2,null,null);

        drawable3 = getResources().getDrawable(R.drawable.carrierselector);
        drawable3.setBounds(0,0,65,65);
        rb3.setCompoundDrawables(null,drawable3,null,null);

        drawable4 = getResources().getDrawable(R.drawable.myselector);
        drawable4.setBounds(0,0,65,65);
        rb4.setCompoundDrawables(null,drawable4,null,null);


        new FragmentTabAdapter(this, fragments, R.id.tab_content, radioGroup);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exit();
            return false;

        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private void exit() {
        // TODO Auto-generated method stub

        if (!isExit) {
            isExit = true;

            Toast.makeText(getApplication(), "再按一次退出程序", Toast.LENGTH_SHORT)
                    .show();
            mHandler.sendEmptyMessageDelayed(0, 2000);

        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        for (int i = 0; i < fragments.size(); i++) {
            fragments.get(i).onResume();
        }

    }

}
