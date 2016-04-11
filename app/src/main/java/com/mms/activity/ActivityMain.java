package com.mms.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mms.R;
import com.mms.adapter.FragmentTabAdapter;
import com.mms.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;

public class ActivityMain extends RoboFragmentActivity {

    private List<BaseFragment> fragments = null;

    private RadioGroup radioGroup;

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
        fragments.add(new FragmentMain());
        fragments.add(new FragmentMain());
        fragments.add(new FragmentMain());

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

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
