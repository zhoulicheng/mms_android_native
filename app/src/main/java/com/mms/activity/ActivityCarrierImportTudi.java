package com.mms.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.mms.R;
import com.mms.base.BaseSwipeActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/15.
 */

@ContentView(R.layout.layout_activity_carrier_import_tudi)
public class ActivityCarrierImportTudi extends BaseSwipeActivity implements View.OnClickListener {

    @InjectView(R.id.et_activity_carrier_import_tudi_title)
    private EditText etTitle;

    @InjectView(R.id.btn_activity_carrier_import_tudi_title_del)
    private Button btnTitleDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_address)
    private EditText etAddress;

    @InjectView(R.id.btn_activity_carrier_import_tudi_address_del)
    private Button btnAddressDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_name)
    private EditText etName;

    @InjectView(R.id.btn_activity_carrier_import_tudi_name_del)
    private Button btnNameDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_zhiwu)
    private EditText etZhiwu;

    @InjectView(R.id.btn_activity_carrier_import_tudi_zhiwu_del)
    private Button btnZhiwuDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_mobilephone)
    private EditText etMobilephone;

    @InjectView(R.id.btn_activity_carrier_import_tudi_mobilephone_del)
    private Button btnMobilephoneDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_telephone_quhao)
    private EditText etQuhao;

    @InjectView(R.id.et_activity_carrier_import_tudi_telephone_zuoji)
    private EditText etZuoji;

    @InjectView(R.id.et_activity_carrier_import_tudi_telephone_fenji)
    private EditText etFenji;

    @InjectView(R.id.btn_activity_carrier_import_tudi_telephone_del)
    private Button btnTelephoneDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_mail)
    private EditText etMail;

    @InjectView(R.id.btn_activity_carrier_import_tudi_mail_del)
    private Button btnMailDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_remark)
    private EditText etRemark;

    @InjectView(R.id.btn_activity_carrier_import_tudi_remark_del)
    private Button btnRemarkDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();
    }

    private void setOCL() {
        etTitle.addTextChangedListener(tw);
        etAddress.addTextChangedListener(tw);
        etZhiwu.addTextChangedListener(tw);
        etMobilephone.addTextChangedListener(tw);
        etName.addTextChangedListener(tw);
        etFenji.addTextChangedListener(tw);
        etQuhao.addTextChangedListener(tw);
        etZuoji.addTextChangedListener(tw);
        etMail.addTextChangedListener(tw);
        etRemark.addTextChangedListener(tw);
        btnTitleDel.setOnClickListener(this);
        btnAddressDel.setOnClickListener(this);
        btnMobilephoneDel.setOnClickListener(this);
        btnNameDel.setOnClickListener(this);
        btnTelephoneDel.setOnClickListener(this);
        btnZhiwuDel.setOnClickListener(this);
        btnMailDel.setOnClickListener(this);
        btnRemarkDel.setOnClickListener(this);
    }

    private TextWatcher tw = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (getCurrentFocus().getId()){
                case R.id.et_activity_carrier_import_tudi_title:
                    if (temp.length() > 0) {
                        btnTitleDel.setVisibility(View.VISIBLE);
                    } else {
                        btnTitleDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_address:
                    if (temp.length() > 0) {
                        btnAddressDel.setVisibility(View.VISIBLE);
                    } else {
                        btnAddressDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_name:
                    if (temp.length() > 0) {
                        btnNameDel.setVisibility(View.VISIBLE);
                    } else {
                        btnNameDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_zhiwu:
                    if (temp.length() > 0) {
                        btnZhiwuDel.setVisibility(View.VISIBLE);
                    } else {
                        btnZhiwuDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_mobilephone:
                    if (temp.length() > 0) {
                        btnMobilephoneDel.setVisibility(View.VISIBLE);
                    } else {
                        btnMobilephoneDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_telephone_quhao:
                    if (temp.length() > 0) {
                        btnTelephoneDel.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_telephone_zuoji:
                    if (temp.length() > 0) {
                        btnTelephoneDel.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_telephone_fenji:
                    if (temp.length() > 0) {
                        btnTelephoneDel.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_mail:
                    if (temp.length() > 0) {
                        btnMailDel.setVisibility(View.VISIBLE);
                    } else {
                        btnMailDel.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.et_activity_carrier_import_tudi_remark:
                    if (temp.length() > 0) {
                        btnRemarkDel.setVisibility(View.VISIBLE);
                    } else {
                        btnRemarkDel.setVisibility(View.INVISIBLE);
                    }
                    break;

            }


        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_carrier_import_tudi_title_del:
                etTitle.setText("");
                btnTitleDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_address_del:
                etAddress.setText("");
                btnAddressDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_name_del:
                etName.setText("");
                btnNameDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_zhiwu_del:
                etZhiwu.setText("");
                btnZhiwuDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_mobilephone_del:
                etMobilephone.setText("");
                btnMobilephoneDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_telephone_del:
                etQuhao.setText("");
                etZuoji.setText("");
                etFenji.setText("");
                btnTelephoneDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_mail_del:
                etMail.setText("");
                btnMailDel.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_activity_carrier_import_tudi_remark_del:
                etRemark.setText("");
                btnRemarkDel.setVisibility(View.INVISIBLE);
                break;


        }
    }

}
