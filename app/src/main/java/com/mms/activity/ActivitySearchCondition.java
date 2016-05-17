package com.mms.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.mms.R;
import com.mms.base.BaseActivity;
import com.mms.sqlite.RecordSQLiteOpenHelper;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/5/17.
 */
@ContentView(R.layout.layout_activity_search_condition)
public class ActivitySearchCondition extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_activity_search_condition_back)
    private Button btnBack;

    @InjectView(R.id.btn_activity_search_condition_ok)
    private Button btnOk;

    @InjectView(R.id.et_activity_search_condition_search)
    private EditText etSearch;

    @InjectView(R.id.ll_activity_search_condition_search_back)
    private LinearLayout llSearch;

    @InjectView(R.id.lv_activity_search_condition_history)
    private ListView lvHistory;

    @InjectView(R.id.btn_activity_search_condition_clear)
    private Button btnClear;

    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;
    private BaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setOCL();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 16) {
            llSearch.setBackground(DrawableUtils.getDrawable(10, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.qianGray)));
        } else {
            llSearch.setBackgroundDrawable(DrawableUtils.getDrawable(10, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.qianGray)));
        }
        queryData("");
    }

    private void setOCL() {
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        etSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(etSearch.getText().toString().trim());
                    if (!hasData) {
                        insertData(etSearch.getText().toString().trim());
                        queryData("");
                    }
                    // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
                    Utils.showToast(ActivitySearchCondition.this, "点击了搜索");

                }
                return false;
            }
        });

        // 搜索框的文本变化实时监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tempName = etSearch.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
                queryData(tempName);

            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_list_item_history);
                String name = textView.getText().toString();
                etSearch.setText(name);
                search(name);
            }
        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_activity_search_condition_back:
                finish();
                break;
            case R.id.btn_activity_search_condition_clear:
                deleteData();
                queryData("");
                break;
            case R.id.btn_activity_search_condition_ok:
                String str = etSearch.getText().toString().trim();
//                if (TextUtils.isEmpty(str)){
//                    Utils.showToast(this,"请输入搜索条件");
//                }else {
                boolean hasData = hasData(str);
                if (!hasData) {
                    insertData(str);
                }
                search(str);
//                }
                break;
        }
    }

    private void search(String str) {
        Intent intent = new Intent();
        intent.putExtra("condition", str);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, R.layout.list_item_history, cursor, new String[]{"name"},
                new int[]{R.id.tv_list_item_history}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }


}
