package com.mms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseFragment;
import com.mms.util.DrawableUtils;

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

    @InjectView(R.id.rl_fragment_my_setprivacy)
    private RelativeLayout rlPrivacy;

    @InjectView(R.id.rl_fragment_my_menbers)
    private RelativeLayout rlMenbers;

    @InjectView(R.id.rl_fragment_my_feedback)
    private RelativeLayout rlFeedback;

    @InjectView(R.id.rl_fragment_my_servicenumber)
    private RelativeLayout rlServiceNumber;

    @InjectView(R.id.rl_fragment_my_about)
    private RelativeLayout rlAbout;

    @InjectView(R.id.btn_fragment_my_logout)
    private Button btnLogout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_my, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setOCL();

    }

    private void initView(){
        if (Build.VERSION.SDK_INT>=16) {
            btnLogout.setBackground(DrawableUtils.getDrawable(15, getResources().getColor(R.color.myRed)));
        }else {
            btnLogout.setBackgroundDrawable(DrawableUtils.getDrawable(15, getResources().getColor(R.color.myRed)));
        }
    }

    private void setOCL() {
        rlAbout.setOnClickListener(this);
        rlAccountAndPassword.setOnClickListener(this);
        rlEditInfor.setOnClickListener(this);
        rlFeedback.setOnClickListener(this);
        rlMenbers.setOnClickListener(this);
        rlPerson.setOnClickListener(this);
        rlPrivacy.setOnClickListener(this);
        rlServiceNumber.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_fragment_my_person:
                break;
            case R.id.rl_fragment_my_accountandpassword:
                break;
            case R.id.rl_fragment_my_editinfor:
                break;
            case R.id.rl_fragment_my_setprivacy:
                intent = new Intent(getActivity(), ActivitySetPrivacy.class);
                startActivity(intent);
                break;
            case R.id.rl_fragment_my_menbers:
                break;
            case R.id.rl_fragment_my_feedback:
                intent = new Intent(getActivity(), ActivityFeedBack.class);
                startActivity(intent);
                break;
            case R.id.rl_fragment_my_servicenumber:
                //呼出拨打电话界面，应该加上Dialog
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02258518831"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rl_fragment_my_about:
                intent = new Intent(getActivity(), ActivityAbout.class);
                startActivity(intent);
                break;

        }

    }
}
