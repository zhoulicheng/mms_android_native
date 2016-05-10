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
import com.mms.widget.ContactView;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/4.
 */
@ContentView(R.layout.layout_activity_carrier_import_depot)
public class ActivityCarrierImportDepot extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.btn_activity_carrier_import_depot_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_carrier_import_depot_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.et_activity_carrier_import_depot_address)
    private CancelableEditView etAddress;

    @InjectView(R.id.rl_activity_carrier_import_depot_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.tv_activity_carrier_import_depot_level)
    private TextView tvLevel;

    @InjectView(R.id.rl_activity_carrier_import_depot_status)
    private RelativeLayout rlStatus;

    @InjectView(R.id.tv_activity_carrier_import_depot_status)
    private TextView tvStatus;

    @InjectView(R.id.ll_activity_carrier_import_depot_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_carrier_import_depot_add_and_del)
    private LinearLayout llAddAndDel;

    @InjectView(R.id.btn_activity_carrier_import_depot_add_contact)
    private Button btnAddContact;

    @InjectView(R.id.cb_activity_carrier_import_depot_rent)
    private CheckBox cbRent;

    @InjectView(R.id.cb_activity_carrier_import_depot_sell)
    private CheckBox cbSell;

    @InjectView(R.id.cb_activity_carrier_import_depot_cooperation)
    private CheckBox cbCooperation;

    @InjectView(R.id.rl_activity_carrier_import_depot_structure)
    private RelativeLayout rlStructure;

    @InjectView(R.id.tv_activity_carrier_import_depot_structure)
    private TextView tvStructure;

    @InjectView(R.id.et_activity_carrier_import_depot_area)
    private CancelableEditView etArea;

    @InjectView(R.id.et_activity_carrier_import_depot_office_area)
    private CancelableEditView etOfficeArea;

    @InjectView(R.id.et_activity_carrier_import_depot_for_rent_area)
    private CancelableEditView etForRentArea;

    @InjectView(R.id.et_activity_carrier_import_depot_settled_company)
    private CancelableEditView etSettledCompany;

    @InjectView(R.id.et_activity_carrier_import_depot_road_width)
    private CancelableEditView etRoadWidth;

    @InjectView(R.id.et_activity_carrier_import_depot_floor)
    private CancelableEditView etFloor;

    @InjectView(R.id.et_activity_carrier_import_depot_door_num)
    private CancelableEditView etDoorNum;

    @InjectView(R.id.et_activity_carrier_import_depot_door_height)
    private CancelableEditView etDoorHeight;

    @InjectView(R.id.et_activity_carrier_import_depot_beam_height)
    private CancelableEditView etBeamHeight;

    @InjectView(R.id.et_activity_carrier_import_depot_lamp_height)
    private CancelableEditView etLampHeight;

    @InjectView(R.id.et_activity_carrier_import_depot_pillar_width)
    private CancelableEditView etPillarWidth;

    @InjectView(R.id.et_activity_carrier_import_depot_spray_area)
    private CancelableEditView etSprayArea;

    @InjectView(R.id.rl_activity_carrier_import_depot_fire_control_level)
    private RelativeLayout rlFireControlLevel;

    @InjectView(R.id.tv_activity_carrier_import_depot_fire_control_level)
    private TextView tvFireControlLevel;

    @InjectView(R.id.rl_activity_carrier_import_depot_type)
    private RelativeLayout rlDepotType;

    @InjectView(R.id.tv_activity_carrier_import_depot_type)
    private TextView tvType;

    @InjectView(R.id.rl_activity_carrier_import_depot_unload_type)
    private RelativeLayout rlUnloadType;

    @InjectView(R.id.tv_activity_carrier_import_depot_unload_type)
    private TextView tvUnloadType;

    @InjectView(R.id.et_activity_carrier_import_depot_bearing)
    private CancelableEditView etBearing;

    @InjectView(R.id.et_activity_carrier_import_depot_lighting)
    private CancelableEditView etLighting;

    @InjectView(R.id.rg_activity_carrier_import_depot_dorm)
    private RadioGroup rgDorm;

    @InjectView(R.id.rg_activity_carrier_import_depot_canteen)
    private RadioGroup rgCanteen;

    @InjectView(R.id.rg_activity_carrier_import_depot_power_bak)
    private RadioGroup rgPowerBak;

    @InjectView(R.id.rg_activity_carrier_import_depot_idc_distribution)
    private RadioGroup rgIdcDistribution;

    @InjectView(R.id.et_activity_carrier_import_depot_rprice)
    private CancelableEditView etRPrice;

    @InjectView(R.id.et_activity_carrier_import_depot_sprice)
    private CancelableEditView etSPrice;

    @InjectView(R.id.et_activity_carrier_import_depot_intro)
    private EditText etIntro;

    @InjectView(R.id.btn_activity_carrier_import_depot_save)
    private Button btnSave;

    private boolean hasDorm = false;
    private boolean hasCanteen = false;
    private boolean hasPowerBak = false;
    private boolean hasIdcDistribution = false;
    private boolean hasBtnDelContact = false;
    private int level = -1;
    private int status = -1;
    private int structure = -1;
    private int depotType = -1;
    //下边这俩先暂时不加判断，需要加的时候直接在isLegal()中加个判断就行
    private int fireControlLevel = -1;
    private int unloadType = -1;
    private List<String> needs;

    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private List<String> levelList;
    private List<String> statusList;
    private List<String> structureList;
    private List<String> fireControlLevelList;
    private List<String> depotTypeList;
    private List<String> unloadTypeList;
    private AdapterView.OnItemClickListener levelListener;
    private AdapterView.OnItemClickListener statusListener;
    private AdapterView.OnItemClickListener structureListener;
    private AdapterView.OnItemClickListener fireControlLevelListener;
    private AdapterView.OnItemClickListener depotTypeListener;
    private AdapterView.OnItemClickListener unloadTypeListener;

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
//                        Utils.showToast(ActivityCarrierImportDepot.this, "1");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "2");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "3");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "4");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "5");
//                        break;
//
//                }
                level = i + 1;
                tvLevel.setText(levelList.get(i));
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
//                        Utils.showToast(ActivityCarrierImportDepot.this, "待租");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "待售");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "已租");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "已售");
//                        break;
//
//                }
                status = i + 1;
                tvStatus.setText(statusList.get(i));
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
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "彩钢");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "钢混");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "砖混");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "框架");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "其他");
//                        break;
//                }
                structure = i + 1;
                tvStructure.setText(statusList.get(i));
            }
        };

        fireControlLevelList = new ArrayList<>();
        fireControlLevelList.add("甲");
        fireControlLevelList.add("乙");
        fireControlLevelList.add("丙一");
        fireControlLevelList.add("丙二");
        fireControlLevelList.add("丁");
        fireControlLevelList.add("戊");
        fireControlLevelListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "1");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "2");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "3");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "4");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "5");
//                        break;
//                    case 5:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "6");
//                        break;
//
//                }
                fireControlLevel = i + 1;
                tvFireControlLevel.setText(fireControlLevelList.get(i));
            }
        };

        depotTypeList = new ArrayList<>();
        depotTypeList.add("高台");
        depotTypeList.add("平库");
        depotTypeList.add("立体库");
        depotTypeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "高台");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "平库");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "立体库");
//                        break;
//
//                }
                depotType = i + 1;
                tvType.setText(depotTypeList.get(i));
            }
        };

        unloadTypeList = new ArrayList<>();
        unloadTypeList.add("双面卸货平台");
        unloadTypeList.add("单面卸货平台");
        unloadTypeList.add("内置平台");
        unloadTypeList.add("外置平台");
        unloadTypeList.add("内外混合平台");
        unloadTypeList.add("无");
        unloadTypeListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "1");
//                        break;
//                    case 1:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "2");
//                        break;
//                    case 2:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "3");
//                        break;
//                    case 3:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "4");
//                        break;
//                    case 4:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "5");
//                        break;
//                    case 5:
//                        Utils.showToast(ActivityCarrierImportDepot.this, "6");
//                        break;
//                }
                unloadType = i + 1;
                tvUnloadType.setText(unloadTypeList.get(i));

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
        rlDepotType.setOnClickListener(this);
        rlFireControlLevel.setOnClickListener(this);
        rlUnloadType.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        cbCooperation.setOnCheckedChangeListener(this);
        cbRent.setOnCheckedChangeListener(this);
        cbSell.setOnCheckedChangeListener(this);
        etIntro.setOnTouchListener(etLongTextOnTouchListener);
        rgDorm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hasDorm = !hasDorm;
            }
        });
        rgCanteen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hasCanteen = !hasCanteen;
            }
        });

        rgPowerBak.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hasPowerBak = !hasPowerBak;
            }
        });

        rgIdcDistribution.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                hasIdcDistribution = !hasIdcDistribution;
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
            case R.id.cb_activity_carrier_import_depot_rent:
                if (b) {
                    Utils.showToast(this, "您选择了出租");
                    needs.add("1");
                } else {
                    Utils.showToast(this, "您取消了出租");
                    needs.remove("1");
                }
                break;
            case R.id.cb_activity_carrier_import_depot_sell:
                if (b) {
                    Utils.showToast(this, "您选择了出售");
                    needs.add("2");
                } else {
                    Utils.showToast(this, "您取消了出售");
                    needs.remove("2");
                }
                break;
            case R.id.cb_activity_carrier_import_depot_cooperation:
                if (b) {
                    Utils.showToast(this, "您选择了合作");
                    needs.add("3");
                } else {
                    Utils.showToast(this, "您取消了合作");
                    needs.remove("3");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_carrier_import_depot_back:
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
            case R.id.rl_activity_carrier_import_depot_level:
                //点击等级选择
                selectDialog = new SelectDialog(this, levelList);
                selectDialog.setOnItemClickListener(levelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_depot_status:
                //点击状态选择
                selectDialog = new SelectDialog(this, statusList);
                selectDialog.setOnItemClickListener(statusListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_depot_fire_control_level:
                //点击消防等级选择
                selectDialog = new SelectDialog(this, fireControlLevelList);
                selectDialog.setOnItemClickListener(fireControlLevelListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_depot_type:
                //点击仓库类型选择
                selectDialog = new SelectDialog(this, depotTypeList);
                selectDialog.setOnItemClickListener(depotTypeListener);
                selectDialog.show();
                break;
            case R.id.rl_activity_carrier_import_depot_unload_type:
                //点击卸货平台选择
                selectDialog = new SelectDialog(this, unloadTypeList);
                selectDialog.setOnItemClickListener(unloadTypeListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_depot_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_import_del_contact:
                //删除联系人
                delContact(view);
                break;
            case R.id.rl_activity_carrier_import_depot_structure:
                //点击建筑结构选择
                selectDialog = new SelectDialog(this, structureList);
                selectDialog.setOnItemClickListener(structureListener);
                selectDialog.show();
                break;
            case R.id.btn_activity_carrier_import_depot_save:
                //点击保存按钮
                if (isLegal()){
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
        if (needs.size() == 0) {
            Utils.showToast(this, "请选择意向");
            return false;
        }
        if (structure == -1) {
            Utils.showToast(this, "请选择建筑结构");
            return false;
        }
        if (depotType==-1){
            Utils.showToast(this, "请选择仓库类型");
            return false;
        }
        return true;
    }

    private String getNeed() {
        String s = "";
        for (String str : needs) {
            s = s + str + ",";
        }
        return s.substring(0, s.length() - 1);

    }

    private void save() {
        showLoadingDialog();
        cancelLoadingDialog();
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

    public String getTheTitle() {
        return etTitle.getText();
    }

    public String getAddress() {
        return etAddress.getText();
    }

    private int getLevel(){
        return level;
    }

    private int getStatus(){
        return status;
    }

    public String getArea() {
        return etArea.getText();
    }

    public String getOfficeArea() {
        return etOfficeArea.getText();
    }

    public String getForRentArea() {
        return etForRentArea.getText();
    }

    public String getSettledCompany() {
        return etSettledCompany.getText();
    }

    public String getRoadWidth() {
        return etRoadWidth.getText();
    }

    public String getFloor() {
        return etFloor.getText();
    }

    public String getDoorNum() {
        return etDoorNum.getText();
    }

    public String getDoorHeight() {
        return etDoorHeight.getText();
    }

    public String getBeamHeight() {
        return etBeamHeight.getText();
    }

    public String getLampHeight() {
        return etLampHeight.getText();
    }

    public String getPillarWidth() {
        return etPillarWidth.getText();
    }

    public String getSparyArea() {
        return etSprayArea.getText();
    }

    public String getBearing() {
        return etBearing.getText();
    }

    public String getLighting() {
        return etLighting.getText();
    }

    public String getRPrice() {
        return etRPrice.getText();
    }

    public String getSPrice() {
        return etSPrice.getText();
    }

}
