package com.mms.widget.kankanWheelView;

import java.util.List;

public class ProvinceModel {
    private String name;
    private List<CityModel> cityList;
    private String zipcode;

    public ProvinceModel() {
        super();
    }

    public ProvinceModel(String name, List<CityModel> cityList, String zipcode) {
        super();
        this.name = name;
        this.cityList = cityList;
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + name + ", cityList=" + cityList + "]";
    }

}
