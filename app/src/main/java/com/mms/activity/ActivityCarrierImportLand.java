package com.mms.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mms.R;
import com.mms.base.BaseSwipeActivity;
import com.mms.dialog.MessageDialog;
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

@ContentView(R.layout.layout_activity_carrier_import_land)
public class ActivityCarrierImportLand extends BaseSwipeActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_activity_carrier_import_land_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_carrier_import_land_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.et_activity_carrier_import_land_address)
    private CancelableEditView etAddress;

    @InjectView(R.id.rl_activity_carrier_import_land_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.rl_activity_carrier_import_land_state)
    private RelativeLayout rlState;

    @InjectView(R.id.ll_activity_carrier_import_land_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_land_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_land_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_land_rent)
    private CheckBox cbRent;

    @InjectView(R.id.cb_activity_carrier_import_land_purchase)
    private CheckBox cbPurchase;

    @InjectView(R.id.cb_activity_carrier_import_land_cooperation)
    private CheckBox cbCooperation;

    @InjectView(R.id.cb_activity_carrier_import_land_tagout)
    private CheckBox cbTagout;

    @InjectView(R.id.cb_activity_carrier_import_land_transfer)
    private CheckBox cbTransfer;

    @InjectView(R.id.rl_activity_carrier_import_land_type)
    private RelativeLayout rlType;

    @InjectView(R.id.et_activity_carrier_import_land_area)
    private CancelableEditView etArea;

    //这词儿是容积率的意思
    @InjectView(R.id.et_activity_carrier_import_land_volume_rate)
    private CancelableEditView etVolumeRate;

    @InjectView(R.id.et_activity_carrier_import_land_tax_require)
    private CancelableEditView etTaxRequire;

    @InjectView(R.id.et_activity_carrier_import_land_price)
    private CancelableEditView etPrice;

    @InjectView(R.id.et_activity_carrier_import_land_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_carrier_import_land_save)
    private Button btnSave;

    private boolean hasBtnDelContact = false;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private List<String> levelList;
    private List<String> stateList;
    private List<String> typeList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener stateListener;
    private AdapterView.OnItemClickListener typeListener;

    private MessageDialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//做一些初始化的工作
        setOCL();
    }

    private void init() {
        messageDialog = new MessageDialog(this);
        if (Build.VERSION.SDK_INT >= 16) {
            btnSave.setBackground(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        } else {
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
                switch (i) {
                    case 0:
                        Utils.showToast(ActivityCarrierImportLand.this, "1");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportLand.this, "2");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportLand.this, "3");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportLand.this, "4");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportLand.this, "5");
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
                switch (i) {
                    case 0:
                        Utils.showToast(ActivityCarrierImportLand.this, "待租");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportLand.this, "待售");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportLand.this, "已租");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportLand.this, "已售");
                        break;

                }
            }
        };
        typeList = new ArrayList<>();
        typeList.add("工业用地");
        typeList.add("商业用地");
        typeList.add("综合用地");
        typeList.add("住宅用地");
        typeList.add("其他用地");
        typeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Utils.showToast(ActivityCarrierImportLand.this, "工业用地");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportLand.this, "商业用地");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportLand.this, "综合用地");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportLand.this, "住宅用地");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportLand.this, "其他用地");
                        break;

                }
            }
        };
        //动态添加自定义ContactView相关
        contactViews = new ArrayList<>();
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ContactView contact1 = new ContactView(this);
        llContacts.addView(contact1, LP_FW);
        contactViews.add(contact1);
    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_carrier_import_land_back:
                messageDialog.setMessage("你确定要退出编辑吗？退出后将丢失已经添加或修改的内容。");
                messageDialog.setTitle("后退");
                messageDialog.setPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                messageDialog.show();
                break;
            case R.id.rl_activity_carrier_import_land_level:
                //点击等级选择
                selectDialog = new SelectDialog(this, levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_land_state:
                //点击状态选择
                selectDialog = new SelectDialog(this, stateList);
                selectDialog.setOnItemClickListener(stateListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_land_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_carrier_import_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.rl_activity_carrier_import_land_type:
                //点击类型选择
                selectDialog = new SelectDialog(this, typeList);
                selectDialog.setOnItemClickListener(typeListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_land_save:
                //点击保存按钮

                messageDialog.setTitle("保存");
                messageDialog.setMessage("确认所填信息无误并保存吗？");
                messageDialog.setPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        save();
                    }
                });
                messageDialog.show();
                break;

        }
    }

    private void save() {
        //这里进行保存操作
        showLoadingDialog();
        cancelLoadingDialog();

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
            btnDelContact.setId(R.id.btn_activity_carrier_import_del_contact);
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
        switch (compoundButton.getId()) {
            case R.id.cb_activity_carrier_import_land_rent:
                if (b) {
                    Utils.showToast(this, "您选择了租赁");
                } else {
                    Utils.showToast(this, "您取消了租赁");
                }
                break;
            case R.id.cb_activity_carrier_import_land_purchase:
                if (b) {
                    Utils.showToast(this, "您选择了购置");
                } else {
                    Utils.showToast(this, "您取消了购置");
                }
                break;
            case R.id.cb_activity_carrier_import_land_cooperation:
                if (b) {
                    Utils.showToast(this, "您选择了合作");
                } else {
                    Utils.showToast(this, "您取消了合作");
                }
                break;
            case R.id.cb_activity_carrier_import_land_tagout:
                if (b) {
                    Utils.showToast(this, "您选择了挂牌");
                } else {
                    Utils.showToast(this, "您取消了挂牌");
                }
                break;
            case R.id.cb_activity_carrier_import_land_transfer:
                if (b) {
                    Utils.showToast(this, "您选择了转让");
                } else {
                    Utils.showToast(this, "您取消了转让");
                }
                break;
        }
    }

    private String getTheTitle() {
        return etTitle.getText();
    }

    private String getAddress() {
        return etAddress.getText();
    }

    private String getArea() {
        return etArea.getText();
    }

    private String getVolumeRate() {
        return etVolumeRate.getText();
    }

    private String getTaxRequire() {
        return etTaxRequire.getText();
    }

    private String getPrice() {
        return etPrice.getText();
    }

    private String getIntro() {
        return etIntro.getText().toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            messageDialog.setMessage("你确定要退出编辑吗？退出后将丢失已经添加或修改的内容。");
            messageDialog.setTitle("后退");
            messageDialog.setPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            messageDialog.show();
            return false;

        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


}
