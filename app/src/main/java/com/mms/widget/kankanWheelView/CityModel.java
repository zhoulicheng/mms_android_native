package com.mms.widget.kankanWheelView;

import java.util.List;

public class CityModel {
	private String name;
	private List<DistrictModel> districtList;
	private String zipcode;
	
	public CityModel() {
		super();
	}

	public CityModel(String name, List<DistrictModel> districtList, String zipcode) {
		super();
		this.name = name;
		this.districtList = districtList;
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

	public List<DistrictModel> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictModel> districtList) {
		this.districtList = districtList;
	}

	@Override
	public String toString() {
		return "CityModel [name=" + name + ", districtList=" + districtList
				+ "]";
	}
	
}
