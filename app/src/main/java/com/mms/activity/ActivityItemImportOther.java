package com.mms.activity;

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
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.dialog.MessageDialog;
import com.mms.dialog.SelectDialog;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;
import com.mms.widget.CancelableEditView;
import com.mms.widget.ClickableRowView;
import com.mms.widget.ContactView;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/10.
 */
@ContentView(R.layout.layout_activity_item_import_other)
public class ActivityItemImportOther extends BaseActivity implements View.OnClickListener{

    @InjectView(R.id.btn_activity_item_import_other_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_item_import_other_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.rl_activity_item_import_other_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.tv_activity_item_import_other_level)
    private TextView tvLevel;

    @InjectView(R.id.ll_activity_item_import_other_districts)
    private LinearLayout llDistricts;

    @InjectView(R.id.ll_activity_item_import_other_districts_add_and_del)
    private LinearLayout llDistrictsAddAndDel;

    @InjectView(R.id.btn_activity_item_import_other_districts_add_district)
    private Button btnAddDistrict;

    @InjectView(R.id.ll_activity_item_import_other_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_item_import_other_contacts_add_and_del)
    private LinearLayout llContactsAddAndDel;

    @InjectView(R.id.btn_activity_item_import_other_contacts_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.rl_activity_item_import_other_land_type)
    private RelativeLayout rlLandType;

    @InjectView(R.id.tv_activity_item_import_other_land_type)
    private TextView tvLandType;

    @InjectView(R.id.ll_activity_item_import_other_industrys)
    private LinearLayout llIndustrys;

    @InjectView(R.id.ll_activity_item_import_other_industrys_add_and_del)
    private LinearLayout llIndustrysAddAndDel;

    @InjectView(R.id.btn_activity_item_import_other_industrys_add_industry)
    private Button btnAddIndustry;

    @InjectView(R.id.rl_activity_item_import_other_item_from)
    private RelativeLayout rlItemFrom;

    @InjectView(R.id.tv_activity_item_import_other_item_from)
    private TextView tvItemFrom;

    @InjectView(R.id.et_activity_item_import_other_comname)
    private CancelableEditView etComname;

    @InjectView(R.id.rl_activity_item_import_other_comtype)
    private RelativeLayout rlComtype;

    @InjectView(R.id.tv_activity_item_import_other_comtype)
    private TextView tvComtype;

    @InjectView(R.id.rl_activity_item_import_other_comdistrict)
    private RelativeLayout rlComdistrict;

    @InjectView(R.id.tv_activity_item_import_other_comdistrict)
    private TextView tvComdistrict;

    @InjectView(R.id.et_activity_item_import_other_comadd)
    private CancelableEditView etComadd;

    @InjectView(R.id.rg_activity_item_import_other_isregister)
    private RadioGroup rgIsregister;

    @InjectView(R.id.et_activity_item_import_other_reg_money)
    private CancelableEditView etRegMoney;

    @InjectView(R.id.btn_activity_item_import_other_reg_money_type)
    private Button btnRegMoneyType;

    @InjectView(R.id.et_activity_item_import_other_invest_money)
    private CancelableEditView etInvestMoney;

    @InjectView(R.id.btn_activity_item_import_other_invest_money_type)
    private Button btnInvestMoneyType;

    @InjectView(R.id.et_activity_item_import_other_product_money)
    private CancelableEditView etProductMoney;

    @InjectView(R.id.btn_activity_item_import_other_product_money_type)
    private Button btnProductMoneyType;

    @InjectView(R.id.et_activity_item_import_other_tax_money)
    private CancelableEditView etTaxMoney;

    @InjectView(R.id.btn_activity_item_import_other_tax_money_type)
    private Button btnTaxMoneyType;

    @InjectView(R.id.et_activity_item_import_other_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_item_import_other_save)
    private Button btnSave;

    private boolean hasBtnDelDistrict = false;
    private boolean hasBtnDelContact = false;
    private boolean hasBtnDelIndustry = false;
    private boolean isRegister = true;

    private int level = -1;
    private int landType = -1;
    private int itemFrom = -1;
    private int comtype = -1;
    private List<String> needs;

    private List<String> levelList;
    private List<String> landTypeList;
    private List<String> itemFromList;
    private List<String> comtypeList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener landTypeListener;
    private AdapterView.OnItemClickListener itemFromListener;
    private AdapterView.OnItemClickListener comtypeListener;

    private List<ClickableRowView> districtViews;
    private List<ClickableRowView> industryViews;
    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private MessageDialog messageDialog;

    private View.OnTouchListener etLongTextOnTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//做一些初始化的工作
        setOCL();
    }

    private void init() {
        needs = new ArrayList<>();
        messageDialog = new MessageDialog(this);
        if (Build.VERSION.SDK_INT >= 16) {
            btnSave.setBackground(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        } else {
            btnSave.setBackgroundDrawable(DrawableUtils.getStateDrawable(new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_PRESSED}, 20, getResources().getColor(R.color.textGray))
                    , new DrawableUtils.CornerStateDrawable(new int[]{DrawableUtils.STATE_UNPRESSED}, 20, getResources().getColor(R.color.colorPrimary))));
        }
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //初始化每个“请选择”的数据以及监听器
        initListAndListener();

        //动态添加自定义DistrictView
        districtViews = new ArrayList<>();
        ClickableRowView district1 = new ClickableRowView(this);
        district1.setName("意向区域");
        llDistricts.addView(district1, LP_FW);
        districtViews.add(district1);

        //动态添加自定义IndustryView
        industryViews = new ArrayList<>();
        ClickableRowView industry1 = new ClickableRowView(this);
        industry1.setName("产业分类");
        llIndustrys.addView(industry1, LP_FW);
        industryViews.add(industry1);

        //动态添加自定义ContactView相关
        contactViews = new ArrayList<>();
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

    private void initListAndListener() {
        levelList = new ArrayList<>();
        levelList.add("★");
        levelList.add("★★");
        levelList.add("★★★");
        levelList.add("★★★★");
        levelList.add("★★★★★");
        levelListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                level = i + 1;
                tvLevel.setText(levelList.get(i));
                tvLevel.setTextColor(getResources().getColor(R.color.filterTextGray));
            }
        };

        landTypeList = new ArrayList<>();
        landTypeList.add("工业用地");
        landTypeList.add("商业用地");
        landTypeList.add("综合用地");
        landTypeList.add("住宅用地");
        landTypeList.add("其他用地");
        landTypeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                landType = i + 1;
                tvLandType.setText(landTypeList.get(i));
                tvLandType.setTextColor(getResources().getColor(R.color.filterTextGray));
            }
        };

        itemFromList = new ArrayList<>();
        itemFromList.add("电话来访");
        itemFromList.add("线上咨询");
        itemFromList.add("客户介绍");
        itemFromList.add("主动收集");
        itemFromList.add("其他");
        itemFromListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemFrom = i + 1;
                tvItemFrom.setText(itemFromList.get(i));
                tvItemFrom.setTextColor(getResources().getColor(R.color.filterTextGray));
            }
        };

        comtypeList = new ArrayList<>();
        comtypeList.add("内资");
        comtypeList.add("外资");
        comtypeList.add("合资");
        comtypeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                comtype = i + 1;
                tvComtype.setText(comtypeList.get(i));
                tvComtype.setTextColor(getResources().getColor(R.color.filterTextGray));
            }
        };


    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        btnAddDistrict.setOnClickListener(this);
        btnAddIndustry.setOnClickListener(this);
        rlComdistrict.setOnClickListener(this);
        rlComtype.setOnClickListener(this);
        rlItemFrom.setOnClickListener(this);
        rlLandType.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        btnInvestMoneyType.setOnClickListener(this);
        btnProductMoneyType.setOnClickListener(this);
        btnTaxMoneyType.setOnClickListener(this);
        btnRegMoneyType.setOnClickListener(this);
        rgIsregister.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                isRegister = !isRegister;
                Utils.showToast(ActivityItemImportOther.this, "点了");
            }
        });
        etIntro.setOnTouchListener(etLongTextOnTouchListener);
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
            llContactsAddAndDel.addView(btnDelContact, LP_BTN);
            hasBtnDelContact = true;
        }
    }

    private void delContact(View view) {
        if (contactViews.size() > 1) {
            llContacts.removeView(contactViews.get(contactViews.size() - 1));
            contactViews.remove(contactViews.get(contactViews.size() - 1));
            if (contactViews.size() == 1) {
                llContactsAddAndDel.removeView(view);
                hasBtnDelContact = false;
            }
        }
    }

    private void addDistrict() {
        ClickableRowView districtView = new ClickableRowView(this);
        districtView.setName("意向区域");
        districtViews.add(districtView);
        llDistricts.addView(districtView, LP_FW);
        if (districtViews.size() > 1 && !hasBtnDelDistrict) {
            Button btnDelDistrict = new Button(this);
            LinearLayout.LayoutParams LP_BTN = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            LP_BTN.weight = 1;
            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                btnDelDistrict.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector));
            } else if (Build.VERSION.SDK_INT >= 21) {
                btnDelDistrict.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector, null));
            } else {
                btnDelDistrict.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitebuttonselector));
            }
            btnDelDistrict.setId(R.id.btn_activity_import_del_district);
            btnDelDistrict.setText("删除意向区域");
            btnDelDistrict.setTextSize(17);
            btnDelDistrict.setTextColor(getResources().getColor(R.color.myRed));
            btnDelDistrict.setOnClickListener(this);
            llDistrictsAddAndDel.addView(btnDelDistrict, LP_BTN);
            hasBtnDelDistrict = true;
        }
    }

    private void delDistrict(View view) {
        if (districtViews.size() > 1) {
            llDistricts.removeView(districtViews.get(districtViews.size() - 1));
            districtViews.remove(districtViews.get(districtViews.size() - 1));
            if (districtViews.size() == 1) {
                llDistrictsAddAndDel.removeView(view);
                hasBtnDelDistrict = false;
            }
        }
    }

    private void addIndustry() {
        ClickableRowView industryView = new ClickableRowView(this);
        industryView.setName("产业分类");
        industryViews.add(industryView);
        llIndustrys.addView(industryView, LP_FW);
        if (industryViews.size() > 1 && !hasBtnDelIndustry) {
            Button btnDelIndustry = new Button(this);
            LinearLayout.LayoutParams LP_BTN = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            LP_BTN.weight = 1;
            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                btnDelIndustry.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector));
            } else if (Build.VERSION.SDK_INT >= 21) {
                btnDelIndustry.setBackground(getResources().getDrawable(R.drawable.whitebuttonselector, null));
            } else {
                btnDelIndustry.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitebuttonselector));
            }
            btnDelIndustry.setId(R.id.btn_activity_import_del_industry);
            btnDelIndustry.setText("删除产业分类");
            btnDelIndustry.setTextSize(17);
            btnDelIndustry.setTextColor(getResources().getColor(R.color.myRed));
            btnDelIndustry.setOnClickListener(this);
            llIndustrysAddAndDel.addView(btnDelIndustry, LP_BTN);
            hasBtnDelIndustry = true;
        }
    }

    private void delIndustry(View view) {
        if (industryViews.size() > 1) {
            llIndustrys.removeView(industryViews.get(industryViews.size() - 1));
            industryViews.remove(industryViews.get(industryViews.size() - 1));
            if (industryViews.size() == 1) {
                llIndustrysAddAndDel.removeView(view);
                hasBtnDelIndustry = false;
            }
        }
    }

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_item_import_other_back:
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
            case R.id.rl_activity_item_import_other_level:
                //点击等级选择
                selectDialog = new SelectDialog(this, levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_item_import_other_land_type:
                //点击土地类型选择
                selectDialog = new SelectDialog(this, landTypeList);
                selectDialog.setOnItemClickListener(landTypeListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_item_import_other_item_from:
                //点击信息来源选择
                selectDialog = new SelectDialog(this, itemFromList);
                selectDialog.setOnItemClickListener(itemFromListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_item_import_other_comtype:
                //点击企业类型选择
                selectDialog = new SelectDialog(this, comtypeList);
                selectDialog.setOnItemClickListener(comtypeListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_item_import_other_invest_money_type:
                if (TextUtils.equals(btnInvestMoneyType.getText().toString(), "万元")) {
                    btnInvestMoneyType.setText("万美元");
                } else {
                    btnInvestMoneyType.setText("万元");
                }
                break;
            case R.id.btn_activity_item_import_other_product_money_type:
                if (TextUtils.equals(btnProductMoneyType.getText().toString(), "万元")) {
                    btnProductMoneyType.setText("万美元");
                } else {
                    btnProductMoneyType.setText("万元");
                }
                break;
            case R.id.btn_activity_item_import_other_reg_money_type:
                if (TextUtils.equals(btnRegMoneyType.getText().toString(), "万元")) {
                    btnRegMoneyType.setText("万美元");
                } else {
                    btnRegMoneyType.setText("万元");
                }
                break;
            case R.id.btn_activity_item_import_other_tax_money_type:
                if (TextUtils.equals(btnTaxMoneyType.getText().toString(), "万元")) {
                    btnTaxMoneyType.setText("万美元");
                } else {
                    btnTaxMoneyType.setText("万元");
                }
                break;
            case R.id.btn_activity_item_import_other_districts_add_district:
                //增加一个意向区域
                addDistrict();
                break;
            case R.id.btn_activity_import_del_district:
                //删除意向区域
                delDistrict(view);
                break;
            case R.id.btn_activity_item_import_other_industrys_add_industry:
                //增加一个产业分类
                addIndustry();
                break;
            case R.id.btn_activity_import_del_industry:
                //删除产业分类
                delIndustry(view);
                break;
            case R.id.btn_activity_item_import_other_contacts_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_import_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.btn_activity_item_import_other_save:
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

        }
    }

    private void save() {
        showLoadingDialog();
        cancelLoadingDialog();
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
        if (needs.size() == 0) {
            Utils.showToast(this, "请选择意向");
            return false;
        }
        return true;
    }

    private String getTheTitle() {
        return etTitle.getText();
    }

    private int getLevel() {
        return level;
    }

    private String getNeed() {
        String s = "";
        for (String str : needs) {
            s = s + str + ",";
        }
        return s.substring(0, s.length() - 1);

    }

    private int getLandType() {
        return landType;
    }

    private int getItemFrom() {
        return itemFrom;
    }

    private String getComname(){
        return etComname.getText();
    }

    private int getComtype() {
        return comtype;
    }

    private String getComAdd() {
        return etComadd.getText();
    }

    private int getIsRegister() {
        if (isRegister) {
            return 1;
        }
        return 0;
    }

    private double getRegMoney() {
        return Utils.getStringToDouble(etRegMoney.getText());
    }

    private double getInvestMoney() {
        return Utils.getStringToDouble(etInvestMoney.getText());
    }

    private double getProductMoney() {
        return Utils.getStringToDouble(etProductMoney.getText());
    }

    private double getTaxMoney() {
        return Utils.getStringToDouble(etTaxMoney.getText());
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
