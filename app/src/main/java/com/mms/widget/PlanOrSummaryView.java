package com.mms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mms.R;

/**
 * Created by Tanikawa on 2016/5/16.
 */
public class PlanOrSummaryView extends LinearLayout {

    private int type;
    private TextView tvBeginTime;
    private TextView tvEndTime;
    private EditText editText;
    private TextView tvPlanOrSummary;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        tvEndTime.setText(endTime);
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        tvBeginTime.setText(beginTime);
    }

    private String beginTime;
    private String endTime;

    public PlanOrSummaryView(Context context) {
        this(context, null);
    }

    public PlanOrSummaryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlanOrSummaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
        setView();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.PlanOrSummaryView);
            type = typedArray.getInt(R.styleable.PlanOrSummaryView_type, 1);
            typedArray.recycle();
        }
    }

    private void initView() {
//        setBackground();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.plan_or_summary_item_view, this);
        tvBeginTime = (TextView) view.findViewById(R.id.tv_plan_or_summary_item_begin_time);
        tvEndTime = (TextView) view.findViewById(R.id.tv_plan_or_summary_item_end_time);
        editText = (EditText) view.findViewById(R.id.et_plan_or_summary_item);
        tvPlanOrSummary = (TextView) view.findViewById(R.id.tv_plan_or_summary);
    }

    private void setView() {
        if (type == 1) {
            tvPlanOrSummary.setText("工作计划");
        } else {
            tvPlanOrSummary.setText("工作总结");
        }
    }

    public String getIntro() {
        return editText.getText().toString();
    }


}
