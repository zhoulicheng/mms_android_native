package com.mms.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.R;
import com.mms.base.BaseFragment;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentCarrier extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_carrier, container,
                false);
    }
}
