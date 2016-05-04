package com.mms.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseSwipeActivity;
import com.mms.dialog.SelectDialog;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;
import com.mms.widget.CancelableEditView;
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

    @InjectView(R.id.rl_activity_carrier_import_tudi_state)
    private RelativeLayout rlState;

    @InjectView(R.id.ll_activity_carrier_import_tudi_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_tudi_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_tudi_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_tudi_rent)
    private CheckBox cbRent;

    @InjectView(R.id.cb_activity_carrier_import_tudi_purchase)
    private CheckBox cbPurchase;

    @InjectView(R.id.cb_activity_carrier_import_tudi_cooperation)
    private CheckBox cbCooperation;

    @InjectView(R.id.cb_activity_carrier_import_tudi_tagout)
    private CheckBox cbTagout;

    @InjectView(R.id.cb_activity_carrier_import_tudi_transfer)
    private CheckBox cbTransfer;

    @InjectView(R.id.rl_activity_carrier_import_tudi_type)
    private RelativeLayout rlType;

    @InjectView(R.id.et_activity_carrier_import_tudi_area)
    private CancelableEditView etArea;

    //这词儿是容积率的意思
    @InjectView(R.id.et_activity_carrier_import_tudi_volume_rate)
    private CancelableEditView etVolumeRate;

    @InjectView(R.id.et_activity_carrier_import_tudi_tax_require)
    private CancelableEditView etTaxRequire;

    @InjectView(R.id.et_activity_carrier_import_tudi_price)
    private CancelableEditView etPrice;

    @InjectView(R.id.et_activity_carrier_import_tudi_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_carrier_import_tudi_save)
    private Button btnSave;

    private boolean hasBtnDelContact = false;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    private List<String> levelList;
    private List<String> stateList;
    private List<String> typeList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener stateListener;
    private AdapterView.OnItemClickListener typeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//做一些初始化的工作
        setOCL();
    }

    private void init(){

//        btnSave.setPadding(10,8,10,8);
        if (Build.VERSION.SDK_INT>=16){
            btnSave.setBackground(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        }else {
            btnSave.setBackgroundDrawable(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        }

        levelList = new ArrayList<>();
        levelList.add("★");
        levelList.add("★★");
        levelList.add("★★★");
        levelList.add("★★★★");
        levelList.add("★★★★★");
        levelListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Utils.showToast(ActivityCarrierImportTudi.this,"1");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportTudi.this,"2");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportTudi.this,"3");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportTudi.this,"4");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportTudi.this,"5");
                        break;

                }
            }
        };
        stateList = new ArrayList<>();
        stateList.add("待租");
        stateList.add("待售");
        stateList.add("已租");
        stateList.add("已售");
        stateListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Utils.showToast(ActivityCarrierImportTudi.this,"待租");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportTudi.this,"待售");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportTudi.this,"已租");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportTudi.this,"已售");
                        break;

                }
            }
        };
        typeList=new ArrayList<>();
        typeList.add("工业用地");
        typeList.add("商业用地");
        typeList.add("综合用地");
        typeList.add("住宅用地");
        typeList.add("其他用地");
        typeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Utils.showToast(ActivityCarrierImportTudi.this,"工业用地");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportTudi.this,"商业用地");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportTudi.this,"综合用地");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportTudi.this,"住宅用地");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportTudi.this,"其他用地");
                        break;

                }
            }
        };

        contactViews = new ArrayList<>();
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ContactView contact1 = new ContactView(this);
        llContacts.addView(contact1, LP_FW);
        contactViews.add(contact1);
    }

    private void setOCL() {
        etTitle.addTextChangedListener(tw);
        etAddress.addTextChangedListener(tw);
        btnTitleDel.setOnClickListener(this);
        btnAddressDel.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        rlState.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        cbPurchase.setOnCheckedChangeListener(this);
        cbTagout.setOnCheckedChangeListener(this);
        cbCooperation.setOnCheckedChangeListener(this);
        cbTransfer.setOnCheckedChangeListener(this);
        cbRent.setOnCheckedChangeListener(this);
        rlType.setOnClickListener(this);
        btnSave.setOnClickListener(this);

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
        SelectDialog selectDialog;
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
                selectDialog = new SelectDialog(this,levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_tudi_state:
                //点击状态选择
                selectDialog = new SelectDialog(this,stateList);
                selectDialog.setOnItemClickListener(stateListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_tudi_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_carrier_import_tudi_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.rl_activity_carrier_import_tudi_type:
                //点击类型选择
                selectDialog = new SelectDialog(this,typeList);
                selectDialog.setOnItemClickListener(typeListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_tudi_save:
                //点击保存按钮
//                showLoadingDialog();
                if (contactViews.get(contactViews.size()-1).getIsMale()){
                    Utils.showToast(this,"先生");
                }else {
                    Utils.showToast(this,"女士");
                }
//                cancelLoadingDialog();
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
            case R.id.cb_activity_carrier_import_tudi_rent:
                if (b){
                    Utils.showToast(this,"您选择了租赁");
                }else {
                    Utils.showToast(this,"您取消了租赁");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_purchase:
                if (b){
                    Utils.showToast(this,"您选择了购置");
                }else {
                    Utils.showToast(this,"您取消了购置");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_cooperation:
                if (b){
                    Utils.showToast(this,"您选择了合作");
                }else {
                    Utils.showToast(this,"您取消了合作");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_tagout:
                if (b){
                    Utils.showToast(this,"您选择了挂牌");
                }else {
                    Utils.showToast(this,"您取消了挂牌");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_transfer:
                if (b){
                    Utils.showToast(this,"您选择了转让");
                }else {
                    Utils.showToast(this,"您取消了转让");
                }
                break;
        }
    }
}
