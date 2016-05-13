/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mms.widget.kankanWheelView.adapters;

import android.content.Context;

import com.mms.dao.generate.City;
import com.mms.widget.kankanWheelView.CityModel;
import com.mms.widget.kankanWheelView.DistrictModel;
import com.mms.widget.kankanWheelView.ProvinceModel;

import java.util.List;

/**
 * The simple Array wheel adapter
 * @param <T> the element type
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    
    // items
//    private T items[];
    private List<T> items;

    /**
     * Constructor
     * @param context the current context
     * @param items the items
     */
    public ArrayWheelAdapter(Context context, List<T> items) {
        super(context);
        
        //setEmptyItemResource(TEXT_VIEW_ITEM_RESOURCE);
        this.items = items;
    }
    
    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < items.size()) {
            T item = items.get(index);
            if (item instanceof ProvinceModel) {
                return ((ProvinceModel) item).getName();
            }
            if (item instanceof CityModel){
                return ((CityModel) item).getName();
            }
            if (item instanceof DistrictModel){
                return ((DistrictModel) item).getName();
            }
            return "";
        }
        return null;
    }

    public String getItemCode(int index){
        if (index >= 0 && index < items.size()) {
            T item = items.get(index);
            if (item instanceof ProvinceModel) {
                return ((ProvinceModel) item).getZipcode();
            }
            if (item instanceof CityModel){
                return ((CityModel) item).getZipcode();
            }
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }
}
