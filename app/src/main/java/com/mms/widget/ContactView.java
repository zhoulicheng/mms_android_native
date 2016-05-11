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
    private CancelableEditView etPost;
    private CancelableEditView etPhone;
    private EditText etQuhao;
    private EditText etZuoji;
    private EditText etFenji;
    private CancelableEditView etMail;
    private CancelableEditView etRemark;

    private boolean isMale = true;

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
                    R.styleable.ClickableRowView);
        }
    }

    private void initView() {
//        setBackground();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.contact_view, this);
        etName = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_name);
        rgSex = (RadioGroup) view.findViewById(R.id.rg_activity_carrier_import_tudi_sex);
        etPost = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_post);
        etPhone = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_phone);
        etQuhao = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_quhao);
        etZuoji = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_zuoji);
        etFenji = (EditText) view.findViewById(R.id.et_activity_carrier_import_tudi_telephone_fenji);
        etMail = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_mail);
        etRemark = (CancelableEditView) view.findViewById(R.id.et_activity_carrier_import_tudi_remark);

    }

    private void setView() {
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                isMale = !isMale;
            }
        });
    }

    public String getName() {
        return etName.getText().trim();
    }

    public boolean getIsMale(){
        return isMale;
    }

    public String getPost(){
        return etPost.getText().trim();
    }

    public String getPhone(){
        return etPhone.getText().trim();
    }

    public String getQuhao(){
        return etQuhao.getText().toString().trim();
    }

    public String getZuoji(){
        return etZuoji.getText().toString().trim();
    }

    public String getFenji(){
        return etFenji.getText().toString().trim();
    }

    public String getMail(){
        return etMail.getText().trim();
    }

    public String getRemark(){
        return etRemark.getText().trim();
    }

}
