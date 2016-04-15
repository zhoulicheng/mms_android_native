package com.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.base.BaseSwipeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/13.
 * 选择导入载体类型的界面
 */

@ContentView(R.layout.layout_activity_carrier_import)
public class ActivityCarrierImport extends BaseSwipeActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.gv_activity_carrierimport)
    private GridView gvCarrierImport;

    @InjectView(R.id.btn_activity_carrierimport_back)
    private Button btnBack;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    private int[] icon = {R.drawable.icon_import_tudi, R.drawable.icon_import_changfang,
            R.drawable.icon_import_cangku, R.drawable.icon_import_xiezilou,
            R.drawable.icon_import_shangye, R.drawable.icon_import_qita};
    private String[] iconName = {"土地", "厂房", "仓库", "写字楼", "商业", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();

        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.gridview_item, from, to);
        gvCarrierImport.setAdapter(sim_adapter);

    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        gvCarrierImport.setOnItemClickListener(this);

    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_carrierimport_back:
                finish();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null;
        switch (i){
            case 0:
                intent = new Intent(this,ActivityCarrierImportTudi.class);
                startActivity(intent);
                break;

        }
    }
}
