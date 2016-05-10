package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseFragment;
import com.mms.util.Utils;
import com.mms.widget.CustomSpinner.ExpandTabView;
import com.mms.widget.CustomSpinner.ViewList;

import java.util.ArrayList;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentCarrier extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.btn_fragment_carrier_import)
    private Button btnImport;

    @InjectView(R.id.expandtabTab)
    private ExpandTabView expandTabView;

    @InjectView(R.id.v_bg)
    private View v_bg;

    private ViewList viewStatus;
    private ViewList viewNeed;
    private ArrayList<View> mViewArray = new ArrayList<>();
    private String[] statusItems = {"不限", "待租", "待售", "已租", "已售"};
    private String[] statusItemsVaule = {"0", "1", "2", "3", "4"};
    private String[] needItems = {"不限", "出租", "出售", "合作"};
    private String[] needItemsVaule = {"0", "1", "2", "3"};

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

        viewStatus = new ViewList(getActivity(), statusItems, statusItemsVaule);
        viewNeed = new ViewList(getActivity(), needItems, needItemsVaule);
        mViewArray.add(viewStatus);
        mViewArray.add(viewNeed);
        ArrayList<String> mTextArray = new ArrayList<>();
        mTextArray.add("状态");
        mTextArray.add("意向");
        expandTabView.setValue(mTextArray, mViewArray, v_bg);
    }

    private void setOCL() {
        viewStatus.setOnSelectListener(new ViewList.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                v_bg.setVisibility(View.GONE);
                refreshToggleButtonText(viewStatus, showText);
                Utils.showToast(getActivity(),distance);
            }
        });
        viewNeed.setOnSelectListener(new ViewList.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                v_bg.setVisibility(View.GONE);
                refreshToggleButtonText(viewNeed, showText);
                //这里的distance刚好对应数据库里的值
                Utils.showToast(getActivity(),distance);
            }
        });
        btnImport.setOnClickListener(this);

    }

    /**
     * 更新自定义ToggleButton上的文字
     *
     * @param view
     * @param showText
     */
    private void refreshToggleButtonText(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.btn_fragment_carrier_import:
                intent = new Intent(getActivity(), ActivityCarrierImport.class);
                startActivity(intent);
                break;
        }
    }

}
