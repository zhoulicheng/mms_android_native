package com.mms.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.util.Utils;
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
@ContentView(R.layout.layout_activity_select_district)
public class ActivitySelectDistrict extends BaseActivity implements OnWheelChangedListener, View.OnClickListener {

    @InjectView(R.id.id_province)
    private WheelView mViewProvince;

    @InjectView(R.id.id_city)
    private WheelView mViewCity;

    @InjectView(R.id.id_district)
    private WheelView mViewDistrict;

    @InjectView(R.id.btn_activity_select_district_cancel)
    private Button btnCancel;

    @InjectView(R.id.btn_activity_select_district_ok)
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();
        setUpData();
    }

    private void setOCL() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<>(this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(5);
        mViewCity.setVisibleItems(5);
        mViewDistrict.setVisibleItems(5);
        updateCities();
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrict = mDistrictDatasMap.get(mCurrentCity).get(newValue);
            mCurrentDistrictCode = mCurrentDistrict.getZipcode();
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();

        mCurrentProvice = mProvinceDatas.get(pCurrent);
        mCurrentProviceCode = mCurrentProvice.getZipcode();
        List<CityModel> cities = mCitisDatasMap.get(mCurrentProvice);
        if (cities == null) {
            cities = new ArrayList<>();
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCity = mCitisDatasMap.get(mCurrentProvice).get(pCurrent);
        mCurrentCityCode = mCurrentCity.getZipcode();
        List<DistrictModel> areas = mDistrictDatasMap.get(mCurrentCity);

        if (areas == null) {
            areas = new ArrayList<>();
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 所有省
     */
    protected List<ProvinceModel> mProvinceDatas;
    /**
     * key 省 value 市
     */
    protected Map<ProvinceModel, List<CityModel>> mCitisDatasMap = new HashMap<>();
    /**
     * key 市 values 区
     */
    protected Map<CityModel, List<DistrictModel>> mDistrictDatasMap = new HashMap<>();

    /**
     * key 区 values 邮编
     */
    protected Map<DistrictModel, String> mZipcodeDatasMap = new HashMap<>();

    /**
     * 当前省
     */
    protected ProvinceModel mCurrentProvice;
    protected String mCurrentProviceCode;
    /**
     * 当前市
     */
    protected CityModel mCurrentCity;
    protected String mCurrentCityCode;

    /**
     * 当前区
     */
    protected DistrictModel mCurrentDistrict;
    protected String mCurrentDistrictCode;

    /**
     * 解析省市区的XML数据
     */

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProvice = provinceList.get(0);
                mCurrentProviceCode = mCurrentProvice.getZipcode();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCity = cityList.get(0);
                    mCurrentCityCode = mCurrentCity.getZipcode();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrict = districtList.get(0);
                    mCurrentDistrictCode = mCurrentDistrict.getZipcode();
                }
            }
            //*/
            mProvinceDatas = new ArrayList<>();
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas.add(provinceList.get(i));
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    for (int k = 0; k < districtList.size(); k++) {
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k), districtList.get(k).getZipcode());
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityList.get(j), districtList);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i), cityList);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_select_district_cancel:
                finish();
                break;
            case R.id.btn_activity_select_district_ok:
                Utils.showToast(this, mCurrentProviceCode + "_" + mCurrentCityCode + "_" + mCurrentDistrictCode);
                break;
        }
    }
}
