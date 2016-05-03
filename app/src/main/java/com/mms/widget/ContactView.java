package com.mms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.mms.R;

/**
 * Created by Tanikawa on 2016/5/3.
 */
public class ContactView extends LinearLayout {

    private CancelableEditView etName;
    private RadioGroup rgSex;
    private CancelableEditView etZhiwu;
    private CancelableEditView etPhone;
    private EditText etQuhao;
    private EditText etZuoji;
    private EditText etFenji;
    private CancelableEditView etMail;
    private CancelableEditView etRemark;

    public ContactView(Context context) {
        this(context, null);
    }

    public ContactView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
        setView();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ContactView);
        }
    }

    private void initView() {
//        setBackground();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.contact_view, this);
        etName = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_name);
        rgSex = (RadioGroup) view.findViewById(R.id.rg_activity_carrier_import_tudi_sex);
        etZhiwu = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_zhiwu);
        etPhone = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_phone);
        etQuhao = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_quhao);
        etZuoji = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_zuoji);
        etFenji = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_fenji);
        etMail = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_mail);
        etRemark = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_remark);

    }

    private void setView() {
        //例如可见性
    }

//    private void setVisible(View view, boolean visible) {
//        if (visible)
//            view.setVisibility(VISIBLE);
//        else view.setVisibility(GONE);
//    }

}
