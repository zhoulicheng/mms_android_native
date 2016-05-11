package com.mms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.mms.R;
import com.mms.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/13.
 * 在线办公主界面
 */
@ContentView(R.layout.layout_activity_onlineoffice)
public class ActivityOnlineOffice extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.gv_activity_onlineoffice)
    private GridView gvOnlineOffice;

    @InjectView(R.id.btn_activity_onlineoffice_back)
    private Button btnBack;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    private int[] icon = { R.drawable.icon_rizhi, R.drawable.icon_yongche,
            R.drawable.icon_waichu, R.drawable.icon_qingjia, R.drawable.icon_jiaban,
            R.drawable.icon_chuchai, R.drawable.icon_gaizhang, R.drawable.icon_zizhishiyong};
    private String[] iconName = { "日志", "用车", "外出", "请假", "加班", "出差", "盖章",
            "资质使用"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOCL();

        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.gridview_item, from, to);
        gvOnlineOffice.setAdapter(sim_adapter);

    }

    private void setOCL(){
        btnBack.setOnClickListener(this);

    }

    public List<Map<String, Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_activity_onlineoffice_back:
                finish();
                break;

        }
    }
}
