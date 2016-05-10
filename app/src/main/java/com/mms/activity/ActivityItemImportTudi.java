package com.mms.activity;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.dialog.MessageDialog;
import com.mms.dialog.SelectDialog;
import com.mms.widget.CancelableEditView;
import com.mms.widget.ContactView;
import com.mms.widget.DistrictView;
import com.mms.widget.kankanWheelView.CityModel;
import com.mms.widget.kankanWheelView.DistrictModel;
import com.mms.widget.kankanWheelView.OnWheelChangedListener;
import com.mms.widget.kankanWheelView.ProvinceModel;
import com.mms.widget.kankanWheelView.WheelView;
import com.mms.widget.kankanWheelView.XmlParserHandler;
import com.mms.widget.kankanWheelView.adapters.ArrayWheelAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/10.
 */
@ContentView(R.layout.layout_activity_item_import_tudi)
public class ActivityItemImportTudi extends BaseActivity implements View.OnClickListener{

    @InjectView(R.id.btn_activity_item_import_tudi_back)
    private Button btnBack;

    @InjectView(R.id.et_activity_item_import_tudi_title)
    private CancelableEditView etTitle;

    @InjectView(R.id.rl_activity_item_import_tudi_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.ll_activity_item_import_tudi_districts)
    private LinearLayout llDistricts;

    @InjectView(R.id.ll_activity_item_import_tudi_districts_add_and_del)
    private LinearLayout llDistrictsAddAndDel;

    @InjectView(R.id.btn_activity_item_import_tudi_districts_add_district)
    private Button btnAddDistrict;

    @InjectView(R.id.ll_activity_item_import_tudi_contacts)
    private LinearLayout llContacts;

    @InjectView(R.id.ll_activity_item_import_tudi_contacts_add_and_del)
    private LinearLayout llContactsAddAndDel;

    @InjectView(R.id.btn_activity_item_import_tudi_contacts_add_contact)
    private Button btnAddContact;

    private boolean hasBtnDelDistrict = false;
    private boolean hasBtnDelContact = false;

    private List<DistrictView> districtViews;
    private List<ContactView> contactViews;
    private LinearLayout.LayoutParams LP_FW;

    private MessageDialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//做一些初始化的工作
        setOCL();
    }

    private void init() {
        messageDialog = new MessageDialog(this);
        LP_FW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //动态添加自定义DistrictView
        districtViews = new ArrayList<>();
        DistrictView district1 = new DistrictView(this);
        llDistricts.addView(district1, LP_FW);
        districtViews.add(district1);

        //动态添加自定义ContactView相关
        contactViews = new ArrayList<>();
        ContactView contact1 = new ContactView(this);
        llContacts.addView(contact1, LP_FW);
        contactViews.add(contact1);

    }
    private void setOCL() {
        btnBack.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        btnAddContact.setOnClickListener(this);
        btnAddDistrict.setOnClickListener(this);
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
        DistrictView districtView = new DistrictView(this);
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

    @Override
    public void onClick(View view) {
        SelectDialog selectDialog;
        switch (view.getId()) {
            case R.id.btn_activity_item_import_tudi_back:
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
            case R.id.rl_activity_item_import_tudi_level:
//                //点击等级选择
//                selectDialog = new SelectDialog(this, levelList);
//                selectDialog.setOnItemClickListener(levelListener);
//                selectDialog.show();
                break;

            case R.id.btn_activity_item_import_tudi_districts_add_district:
                //增加一个联系人
                addDistrict();
                break;
            case R.id.btn_activity_import_del_district:
                //删除联系人
                delDistrict(view);
                break;
            case R.id.btn_activity_item_import_tudi_contacts_add_contact:
                //增加一个联系人
                addContact();
                break;
            case R.id.btn_activity_import_del_contact:
                //删除联系人
                delContact(view);
                break;
//            case R.id.btn_activity_item_import_tudi_save:
//                //点击保存按钮
//                if (isLegal()) {
//                    messageDialog.setTitle("保存");
//                    messageDialog.setMessage("确认所填信息无误并保存吗？");
//                    messageDialog.setPositiveListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            save();
//                        }
//                    });
//                    messageDialog.show();
//                }
//                break;

        }
    }
}
