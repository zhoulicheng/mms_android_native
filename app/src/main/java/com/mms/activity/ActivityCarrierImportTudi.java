package com.mms.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.dialog.MessageDialog;
import com.mms.dialog.SelectDialog;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;
import com.mms.widget.CancelableEditView;
import com.mms.widget.ContactView;
import com.mms.widget.uploadImagesLayout.OperateImagesLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/15.
 */

@ContentView(R.layout.layout_activity_carrier_import_tudi)
public class ActivityCarrierImportTudi extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_activity_carrier_import_tudi_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_carrier_import_tudi_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.et_activity_carrier_import_tudi_address)
    private CancelableEditView etAddress;

    @InjectView(R.id.rl_activity_carrier_import_tudi_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.tv_activity_carrier_import_tudi_level)
    private TextView tvLevel;

    @InjectView(R.id.rl_activity_carrier_import_tudi_district)
    private RelativeLayout rlDistrict;

    @InjectView(R.id.tv_activity_carrier_import_tudi_district)
    private TextView tvDistrict;

    @InjectView(R.id.rl_activity_carrier_import_tudi_status)
    private RelativeLayout rlStatus;

    @InjectView(R.id.tv_activity_carrier_import_tudi_status)
    private TextView tvStatus;

    @InjectView(R.id.ll_activity_carrier_import_tudi_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_tudi_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_tudi_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_tudi_zhaobiao)
    private CheckBox cbZhaoBiao;

    @InjectView(R.id.cb_activity_carrier_import_tudi_paimai)
    private CheckBox cbPaiMai;

    @InjectView(R.id.cb_activity_carrier_import_tudi_guapai)
    private CheckBox cbGuaPai;

    @InjectView(R.id.cb_activity_carrier_import_tudi_zhuanrang)
    private CheckBox cbZhuanRang;

    @InjectView(R.id.cb_activity_carrier_import_tudi_no)
    private CheckBox cbNo;

    @InjectView(R.id.rl_activity_carrier_import_tudi_type)
    private RelativeLayout rlType;

    @InjectView(R.id.tv_activity_carrier_import_tudi_type)
    private TextView tvTudiType;

    @InjectView(R.id.et_activity_carrier_import_tudi_area)
    private CancelableEditView etArea;

    //这词儿是容积率的意思
    @InjectView(R.id.et_activity_carrier_import_tudi_ratio)
    private CancelableEditView etRatio;

    @InjectView(R.id.et_activity_carrier_import_tudi_tax_need)
    private CancelableEditView etTaxNeed;

    @InjectView(R.id.et_activity_carrier_import_tudi_land_price)
    private CancelableEditView etLandPrice;

    @InjectView(R.id.oil_activity_carrier_import_tudi)
    private OperateImagesLayout imagesLayout;

    @InjectView(R.id.et_activity_carrier_import_tudi_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_carrier_import_tudi_save)
    private Button btnSave;

    private boolean hasBtnDelContact = false;
    private int level = -1;
    private int status = -1;
    private int tudiType = -1;
    private List<String> transforType;
    private String districtCode;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private List<String> levelList;
    private List<String> statusList;
    private List<String> typeList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener statusListener;
    private AdapterView.OnItemClickListener typeListener;

    private MessageDialog messageDialog;

    private View.OnTouchListener etLongTextOnTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//做一些初始化的工作
        setOCL();
    }

    private void init() {
        transforType = new ArrayList<>();
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
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "1");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "2");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "3");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "4");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "5");
//                        break;
//
//                }
                level = i + 1;
                tvLevel.setText(levelList.get(i));
                tvLevel.setTextColor(getResources().getColor(R.color.filterTextGray));
            }
        };
        statusList = new ArrayList<>();
        statusList.add("待租");
        statusList.add("待售");
        statusList.add("已租");
        statusList.add("已售");
        statusListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "待租");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "待售");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "已租");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "已售");
//                        break;
//
//                }
                status = i + 1;
                tvStatus.setText(statusList.get(i));
                tvStatus.setTextColor(getResources().getColor(R.color.filterTextGray));
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
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "工业用地");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "商业用地");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "综合用地");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "住宅用地");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportTudi.this, "其他用地");
//                        break;
//
//                }
                tudiType = i + 1;
                tvTudiType.setText(typeList.get(i));
                tvTudiType.setTextColor(getResources().getColor(R.color.filterTextGray));
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
        btnAddContact.setOnClickListener(this);
        cbPaiMai.setOnCheckedChangeListener(this);
        cbZhuanRang.setOnCheckedChangeListener(this);
        cbGuaPai.setOnCheckedChangeListener(this);
        cbNo.setOnCheckedChangeListener(this);
        cbZhaoBiao.setOnCheckedChangeListener(this);
        rlType.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        etIntro.setOnTouchListener(etLongTextOnTouchListener);
        rlDistrict.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_carrier_import_tudi_back:
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
            case R.id.rl_activity_carrier_import_tudi_level:
                //点击等级选择
                selectDialog = new SelectDialog(this, levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_tudi_status:
                //点击状态选择
                selectDialog = new SelectDialog(this, statusList);
                selectDialog.setOnItemClickListener(statusListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_tudi_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_import_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.rl_activity_carrier_import_tudi_type:
                //点击类型选择
                selectDialog = new SelectDialog(this, typeList);
                selectDialog.setOnItemClickListener(typeListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_tudi_save:
                //点击保存按钮
                if (isLegal()) {
                    messageDialog.setTitle("保存");
                    messageDialog.setMessage("确认所填信息无误并保存吗？");
                    messageDialog.setPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            save();
                        }
                    });
                    messageDialog.show();
                }
                break;
            case R.id.rl_activity_carrier_import_tudi_district:
                //选择地区
                Intent intent = new Intent(this, ActivitySelectDistrict.class);
                startActivityForResult(intent, ActivitySelectDistrict.SELECTDISTRICT);
                break;

        }
    }

    private boolean isLegal() {
        if (TextUtils.isEmpty(getTheTitle())) {
            Utils.showToast(this, "请输入标题");
            return false;
        }
        if (level == -1) {
            Utils.showToast(this, "请选择等级");
            return false;
        }
        if (status == -1) {
            Utils.showToast(this, "请选择载体状态");
            return false;
        }
        if (transforType.size() == 0) {
            Utils.showToast(this, "请选择意向");
            return false;
        }
        if (tudiType == -1) {
            Utils.showToast(this, "请选择建筑结构");
        }
        if (TextUtils.isEmpty(districtCode)) {
            Utils.showToast(this, "请选择地区");
        }
        return true;
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
            btnDelContact.setId(R.id.btn_activity_import_del_contact);
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
            case R.id.cb_activity_carrier_import_tudi_zhaobiao:
                if (b) {
                    Utils.showToast(this, "您选择了招标");
                    transforType.add("1");
                } else {
                    Utils.showToast(this, "您取消了招标");
                    transforType.remove("1");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_paimai:
                if (b) {
                    Utils.showToast(this, "您选择了拍卖");
                    transforType.add("2");
                } else {
                    Utils.showToast(this, "您取消了拍卖");
                    transforType.remove("2");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_guapai:
                if (b) {
                    Utils.showToast(this, "您选择了挂牌");
                    transforType.add("3");
                } else {
                    Utils.showToast(this, "您取消了挂牌");
                    transforType.remove("3");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_zhuanrang:
                if (b) {
                    Utils.showToast(this, "您选择了转让");
                    transforType.add("4");
                } else {
                    Utils.showToast(this, "您取消了转让");
                    transforType.remove("4");
                }
                break;
            case R.id.cb_activity_carrier_import_tudi_no:
                if (b) {
                    Utils.showToast(this, "您选择了不限");
                    transforType.add("10");
                } else {
                    Utils.showToast(this, "您取消了不限");
                    transforType.remove("10");
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

    private int getLevel() {
        return level;
    }

    private int getStatus() {
        return status;
    }

    private double getArea() {
        return Utils.getStringToDouble(etArea.getText());
    }

    private String getRatio() {
        return etRatio.getText();
    }

    private double getTaxNeed() {
        return Utils.getStringToDouble(etTaxNeed.getText());
    }

    private double getLandPrice() {
        return Utils.getStringToDouble(etLandPrice.getText());
    }

    private String getIntro() {
        return etIntro.getText().toString();
    }

    private String getTransforType() {
        String s = "";
        for (String str : transforType) {
            s = s + str + ",";
        }
        return s.substring(0, s.length() - 1);

    }

    private String getDistrictCode() {
        if (!TextUtils.isEmpty(districtCode)) {
            return districtCode;
        }
        return "";
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ActivitySelectDistrict.SELECTDISTRICT:
                if (resultCode == RESULT_OK) {
                    tvDistrict.setText(data.getStringExtra(ActivitySelectDistrict.SELECTDISTRICT_RESULT_STRING));
                    tvDistrict.setTextColor(getResources().getColor(R.color.filterTextGray));
                    districtCode = data.getStringExtra(ActivitySelectDistrict.SELECTDISTRICT_RESULT_CODE);
                    Utils.showToast(this, districtCode);
                } else {
                    tvDistrict.setText("请选择");
                    tvDistrict.setTextColor(getResources().getColor(R.color.hintGray));
                }
                break;
            case OperateImagesLayout.CAMERA_KEY:
                if (resultCode == RESULT_OK) {
                    File file = imagesLayout.getCurrentTmpFile();
                    if (file != null) {
                        Uri uri = Uri.fromFile(file);
                        imagesLayout.addImage(uri.toString());
                    }
                }
                break;
            case OperateImagesLayout.PHOTO_KEY:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imagesLayout.addImage(uri.toString());
                }
                break;
        }

    }

}
