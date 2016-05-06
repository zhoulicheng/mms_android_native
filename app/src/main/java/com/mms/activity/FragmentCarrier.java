package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mms.R;
import com.mms.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentCarrier extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.btn_fragment_carrier_import)
    private Button btnAdd;

    @InjectView(R.id.sp_fragment_carrier_status)
    private Spinner spStatus;

    @InjectView(R.id.sp_fragment_carrier_need)
    private Spinner spNeed;

    private List<String> spStatusData;
    private List<String> spNeedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_carrier, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setOCL();
    }

    private void init() {
        spStatusData = new ArrayList<>();
        spStatusData.add("不限");
        spStatusData.add("待租");
        spStatusData.add("待售");
        spStatusData.add("已租");
        spStatusData.add("已售");
        spNeedData = new ArrayList<>();
        spNeedData.add("不限");
        spNeedData.add("出租");
        spNeedData.add("出售");
        spNeedData.add("合作");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(getActivity(), R.layout.spinner_dropdown_item, spStatusData);
        adapterStatus.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter<String> adapterNeed = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,spNeedData);
        adapterNeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapterStatus);
        spNeed.setAdapter(adapterNeed);

    }

    private void setOCL() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_fragment_carrier_import:
                intent = new Intent(getActivity(), ActivityCarrierImport.class);
                startActivity(intent);
                break;
        }
    }
}
