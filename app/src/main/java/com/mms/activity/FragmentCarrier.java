package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseFragment;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentCarrier extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.btn_fragment_carrier_add)
    private Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_carrier, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOCL();
    }

    private void setOCL(){
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_fragment_carrier_add:
                intent = new Intent(getActivity(),ActivityCarrierImport.class);
                startActivity(intent);
                break;
        }
    }
}
