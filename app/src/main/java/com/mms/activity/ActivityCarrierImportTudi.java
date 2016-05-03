package com.mms.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseSwipeActivity;
import com.mms.util.Utils;
import com.mms.widget.ContactView;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/15.
 */

@ContentView(R.layout.layout_activity_carrier_import_tudi)
public class ActivityCarrierImportTudi extends BaseSwipeActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.et_activity_carrier_import_tudi_title)
    private EditText etTitle;

    @InjectView(R.id.btn_activity_carrier_import_tudi_title_del)
    private Button btnTitleDel;

    @InjectView(R.id.et_activity_carrier_import_tudi_address)
    private EditText etAddress;

    @InjectView(R.id.btn_activity_carrier_import_tudi_address_del)
    private Button btnAddressDel;

    @InjectView(R.id.rl_activity_carrier_import_tudi_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.ll_activity_carrier_import_tudi_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_tudi_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_tudi_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_tudi_zulin)
    private CheckBox cbZulin;

    @InjectView(R.id.cb_activity_carrier_import_tudi_gouzhi)
    private CheckBox cbGouzhi;

    @InjectView(R.id.cb_activity_carrier_import_tudi_hezuo)
    private CheckBox cbHezuo;

    @InjectView(R.id.cb_activity_carrier_import_tudi_guapai)
    private CheckBox cbGuapai;

    @InjectView(R.id.cb_activity_carrier_import_tudi_zhuanrang)
    private CheckBox cbZhuanrang;

    private boolean hasBtnDelContact = false;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViews = new ArrayList<>();
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ContactView contact1 = new ContactView(this);
        llContacts.addView(contact1, LP_FW);
        contactViews.add(contact1);

        setOCL();
    }

    private void setOCL() {
        etTitle.addTextChangedListener(tw);
        etAddress.addTextChangedListener(tw);
        btnTitleDel.setOnClickListener(this);
        btnAddressDel.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        cbGouzhi.setOnCheckedChangeListener(this);
        cbGuapai.setOnCheckedChangeListener(this);
        cbHezuo.setOnCheckedChangeListener(this);
        cbZhuanrang.setOnCheckedChangeListener(this);
        cbZulin.setOnCheckedChangeListener(this);

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
            if (null != getCurrentFocus()) {
                switch (getCurrentFocus().getId()) {
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
                }

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
            case R.id.rl_activity_carrier_import_tudi_level:
                //点击等级选择
                break;
            case R.id.btn_activity_carrier_import_tudi_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_carrier_import_tudi_del_contact:
                //删除联系人
                delContact(view);
                break;

        }
    }

    private void addContact() {
        ContactView contactView = new ContactView(this);
        contactViews.add(contactView);
        llContacts.addView(contactView, LP_FW);
        if (contactViews.size() > 1 && !hasBtnDelContact) {
            Button btnDelContact = new Button(this);
            LinearLayout.LayoutParams LP_BTN = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            LP_BTN.weight = 1;
            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                btnDelContact.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector));
            } else if (Build.VERSION.SDK_INT >= 21) {
                btnDelContact.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector, null));
            } else {
                btnDelContact.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitebuttonselector));
            }
            btnDelContact.setId(R.id.btn_activity_carrier_import_tudi_del_contact);
            btnDelContact.setText("删除联系人");
            btnDelContact.setTextSize(17);
            btnDelContact.setTextColor(getResources().getColor(R.color.myRed));
            btnDelContact.setOnClickListener(this);
            llAddAndDel.addView(btnDelContact, LP_BTN);
            hasBtnDelContact = true;
        }
    }

    private void delContact(View view) {
        if (contactViews.size() > 1) {
            llContacts.removeView(contactViews.get(contactViews.size() - 1));
            contactViews.remove(contactViews.get(contactViews.size() - 1));
            if (contactViews.size() == 1) {
                llAddAndDel.removeView(view);
                hasBtnDelContact = false;
            }
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.cb_activity_carrier_import_tudi_zulin:
                if (b){
                    Utils.showToast(this,"您选择了租赁");
                }else {
                    Utils.showToast(this,"您取消了租赁");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_gouzhi:
                break;
            case R.id.cb_activity_carrier_import_tudi_hezuo:
                break;
            case R.id.cb_activity_carrier_import_tudi_guapai:
                break;
            case R.id.cb_activity_carrier_import_tudi_zhuanrang:
                break;
        }
    }
}
