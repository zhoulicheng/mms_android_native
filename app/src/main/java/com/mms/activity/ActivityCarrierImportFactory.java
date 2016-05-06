package com.mms.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseActivity;
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
 * Created by Tanikawa on 2016/5/4.
 */
@ContentView(R.layout.layout_activity_carrier_import_factory)
public class ActivityCarrierImportFactory extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_activity_carrier_import_factory_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_carrier_import_factory_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.et_activity_carrier_import_factory_address)
    private CancelableEditView etAddress;

    @InjectView(R.id.rl_activity_carrier_import_factory_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.rl_activity_carrier_import_factory_status)
    private RelativeLayout rlStatus;

    @InjectView(R.id.ll_activity_carrier_import_factory_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_factory_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_factory_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_factory_rent)
    private CheckBox cbRent;

    @InjectView(R.id.cb_activity_carrier_import_factory_sell)
    private CheckBox cbSell;

    @InjectView(R.id.cb_activity_carrier_import_factory_cooperation)
    private CheckBox cbCooperation;

    @InjectView(R.id.rl_activity_carrier_import_factory_structure)
    private RelativeLayout rlStructure;

    @InjectView(R.id.et_activity_carrier_import_factory_area)
    private CancelableEditView etArea;

    @InjectView(R.id.et_activity_carrier_import_factory_yard_area)
    private CancelableEditView etYardArea;

    @InjectView(R.id.et_activity_carrier_import_factory_office_area)
    private CancelableEditView etOfficeArea;

    @InjectView(R.id.et_activity_carrier_import_factory_bearing)
    private CancelableEditView etBearing;

    @InjectView(R.id.et_activity_carrier_import_factory_height)
    private CancelableEditView etHeight;

    @InjectView(R.id.et_activity_carrier_import_factory_span)
    private CancelableEditView etSpan;

    @InjectView(R.id.et_activity_carrier_import_factory_floors)
    private CancelableEditView etFloors;

    @InjectView(R.id.et_activity_carrier_import_factory_rprice)
    private CancelableEditView etRPrice;

    @InjectView(R.id.et_activity_carrier_import_factory_sprice)
    private CancelableEditView etSPrice;

    @InjectView(R.id.et_activity_carrier_import_factory_kva)
    private CancelableEditView etKVA;

    @InjectView(R.id.rg_activity_carrier_import_crane)
    private RadioGroup rgCrane;

    @InjectView(R.id.et_activity_carrier_import_factory_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_carrier_import_factory_save)
    private Button btnSave;

    private boolean hasTianche = true;
    private boolean hasBtnDelContact = false;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private List<String> levelList;
    private List<String> stateList;
    private List<String> structureList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener stateListener;
    private AdapterView.OnItemClickListener structureListener;

    private MessageDialog messageDialog;

    private View.OnTouchListener etLongTextOnTouchListener;

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
                        Utils.showToast(ActivityCarrierImportFactory.this, "1");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportFactory.this, "2");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportFactory.this, "3");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportFactory.this, "4");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportFactory.this, "5");
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
                        Utils.showToast(ActivityCarrierImportFactory.this, "待租");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportFactory.this, "待售");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportFactory.this, "已租");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportFactory.this, "已售");
                        break;

                }
            }
        };
        structureList = new ArrayList<>();
        structureList.add("彩钢");
        structureList.add("钢混");
        structureList.add("砖混");
        structureList.add("框架");
        structureList.add("其他");
        structureListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Utils.showToast(ActivityCarrierImportFactory.this, "彩钢");
                        break;
                    case 1:
                        Utils.showToast(ActivityCarrierImportFactory.this, "钢混");
                        break;
                    case 2:
                        Utils.showToast(ActivityCarrierImportFactory.this, "砖混");
                        break;
                    case 3:
                        Utils.showToast(ActivityCarrierImportFactory.this, "框架");
                        break;
                    case 4:
                        Utils.showToast(ActivityCarrierImportFactory.this, "其他");
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

        etLongTextOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        };
    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        rlStatus.setOnClickListener(this);
        rlStructure.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        cbCooperation.setOnCheckedChangeListener(this);
        cbRent.setOnCheckedChangeListener(this);
        cbSell.setOnCheckedChangeListener(this);
        etIntro.setOnTouchListener(etLongTextOnTouchListener);
        rgCrane.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hasTianche = !hasTianche;
            }
        });
        btnSave.setOnClickListener(this);

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
            case R.id.cb_activity_carrier_import_factory_rent:
                if (b) {
                    Utils.showToast(this, "您选择了出租");
                } else {
                    Utils.showToast(this, "您取消了出租");
                }
                break;
            case R.id.cb_activity_carrier_import_factory_sell:
                if (b) {
                    Utils.showToast(this, "您选择了出售");
                } else {
                    Utils.showToast(this, "您取消了出售");
                }
                break;
            case R.id.cb_activity_carrier_import_factory_cooperation:
                if (b) {
                    Utils.showToast(this, "您选择了合作");
                } else {
                    Utils.showToast(this, "您取消了合作");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_carrier_import_factory_back:
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
            case R.id.rl_activity_carrier_import_factory_level:
                //点击等级选择
                selectDialog = new SelectDialog(this, levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_factory_status:
                //点击状态选择
                selectDialog = new SelectDialog(this, stateList);
                selectDialog.setOnItemClickListener(stateListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_factory_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_carrier_import_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.rl_activity_carrier_import_factory_structure:
                //点击建筑结构选择
                selectDialog = new SelectDialog(this, structureList);
                selectDialog.setOnItemClickListener(structureListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_factory_save:
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
        showLoadingDialog();
        cancelLoadingDialog();
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

    private String getYardArea() {
        return etYardArea.getText();
    }

    private String getOfficeArea() {
        return etOfficeArea.getText();
    }

    private String getBearing() {
        return etBearing.getText();
    }

    private String getHeight() {
        return etHeight.getText();
    }

    private String getSpan() {
        return etSpan.getText();
    }

    private String getFloors() {
        return etFloors.getText();
    }

    private String getRPrice() {
        return etRPrice.getText();
    }

    private String getSPrice() {
        return etSPrice.getText();
    }

    private String getKVA() {
        return etKVA.getText();
    }

    private boolean getHasTianche(){
        return hasTianche;
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