package com.mms.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.R;
import com.mms.base.BaseFragment;

/**
 * Created by Tanikawa on 2016/4/11.
 */
public class FragmentMain extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment_main, container,
                false);

        initView(v);
        this.setOCL();
        return v;
    }

    private void initView(View v){


    }

    private void setOCL(){

    }

}