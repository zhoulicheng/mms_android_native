package com.mms.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mms.R;
import com.mms.base.BaseFragment;
import com.mms.util.Utils;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentItem extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_fragment_item_add)
    private Button btnAdd;

    @InjectView(R.id.tb_fragment_item_top)
    private ToggleButton tbTop;

    @InjectView(R.id.tv_fragment_item_top_title)
    private TextView tvTopTitle;

    @InjectView(R.id.tb_fragment_item_from)
    private ToggleButton tbFrom;

    @InjectView(R.id.tv_fragment_item_from_title)
    private TextView tvFrom;

    @InjectView(R.id.tb_fragment_item_state)
    private ToggleButton tbState;

    @InjectView(R.id.tv_fragment_item_state_title)
    private TextView tvState;

    @InjectView(R.id.tb_fragment_item_level)
    private ToggleButton tbLevel;

    @InjectView(R.id.tv_fragment_item_level_title)
    private TextView tvLevel;

    @InjectView(R.id.tb_fragment_item_progress)
    private ToggleButton tbProgress;

    @InjectView(R.id.tv_fragment_item_progress_title)
    private TextView tvProgress;

    private boolean isChecked = false;

    private PopupWindow myPopupWindow;

    @InjectView(R.id.ll_fragment_item_filters)
    private LinearLayout llFilters;

    private int width;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_item, container,
                false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        initMyPopupWindow();
        setOCL();
    }

    private void initMyPopupWindow() {

        View myView = getActivity().getLayoutInflater().inflate(R.layout.item_of_mypopview, null, false);
        myPopupWindow = new PopupWindow(myView, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        myPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        myPopupWindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (myPopupWindow != null && myPopupWindow.isShowing()) {
                    myPopupWindow.dismiss();
                    myPopupWindow = null;
                }

                return false;
            }
        });


    }

    private void setOCL() {
        btnAdd.setOnClickListener(this);

        tvTopTitle.setOnClickListener(this);
        tbTop.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_fragment_item_add:
                intent = new Intent(getActivity(),ActivityItemImport.class);
                startActivity(intent);
                break;
            case R.id.tv_fragment_item_top_title:
//                if (isChecked){
//                    onCheckedChanged(tbTop,false);
//                    isChecked = !isChecked;
//                }else {
//                    onCheckedChanged(tbTop,true);
//                    isChecked = !isChecked;
//                }
                tbTop.setChecked(!isChecked);

                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton btn, boolean b) {
        isChecked = !isChecked;
        if (b) {
            Utils.showToast(getActivity(), "弹出下拉");
            myPopupWindow.showAtLocation(llFilters, Gravity.CENTER_HORIZONTAL,0,0);
        } else {
            Utils.showToast(getActivity(), "收起下拉");
            if (myPopupWindow != null && myPopupWindow.isShowing()) {
                myPopupWindow.dismiss();
                return;
            }
        }
    }
}
