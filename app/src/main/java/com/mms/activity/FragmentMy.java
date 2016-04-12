package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseFragment;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentMy extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.rl_fragment_my_person)
    private RelativeLayout rlPerson;

    @InjectView(R.id.rl_fragment_my_accountandpassword)
    private RelativeLayout rlAccountAndPassword;

    @InjectView(R.id.rl_fragment_my_editinfor)
    private RelativeLayout rlEditInfor;

    @InjectView(R.id.rl_fragment_my_privacy)
    private RelativeLayout rlPrivacy;

    @InjectView(R.id.rl_fragment_my_menbers)
    private RelativeLayout rlMenbers;

    @InjectView(R.id.rl_fragment_my_feedback)
    private RelativeLayout rlFeedback;

    @InjectView(R.id.rl_fragment_my_servicenumber)
    private RelativeLayout rlServiceNumber;

    @InjectView(R.id.rl_fragment_my_about)
    private RelativeLayout rlAbout;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment_my, container,
                false);

        initView(v);
        this.setOCL();
        return v;
    }

    private void initView(View v){


    }

    private void setOCL(){

    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.rl_fragment_my_person:
                break;
            case R.id.rl_fragment_my_accountandpassword:
                break;
            case R.id.rl_fragment_my_editinfor:
                break;
            case R.id.rl_fragment_my_privacy:
                break;
            case R.id.rl_fragment_my_menbers:
                break;
            case R.id.rl_fragment_my_feedback:
                break;
            case R.id.rl_fragment_my_servicenumber:
                break;
            case R.id.rl_fragment_my_about:
                break;

        }

    }
}
