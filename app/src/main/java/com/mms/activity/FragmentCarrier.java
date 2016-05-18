package com.mms.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mms.R;
import com.mms.adapter.CarrierAdapter;
import com.mms.base.BaseFragment;
import com.mms.util.DrawableUtils;
import com.mms.util.Utils;
import com.mms.widget.rlrView.view.RLRView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class FragmentCarrier extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final int SEARCH_CONDITION = 1;

    @InjectView(R.id.btn_fragment_carrier_import)
    private Button btnImport;

    @InjectView(R.id.iv_fragment_carrier_top)
    private ImageView ivTop;

    @InjectView(R.id.iv_fragment_carrier_status)
    private ImageView ivStatsu;

    @InjectView(R.id.iv_fragment_carrier_need)
    private ImageView ivNeed;

    @InjectView(R.id.tv_fragment_carrier_top_title)
    private TextView tvTopTitle;

    @InjectView(R.id.tv_fragment_carrier_status_title)
    private TextView tvStatus;

    @InjectView(R.id.tv_fragment_carrier_need_title)
    private TextView tvNeed;

    @InjectView(R.id.ll_fragment_carrier_title)
    private LinearLayout titleLayout;

    @InjectView(R.id.rl_Fragment_carrier_status)
    private RelativeLayout rlStatus;

    @InjectView(R.id.rl_Fragment_carrier_need)
    private RelativeLayout rlNeed;

    @InjectView(R.id.tv_fragment_carrier_search)
    private TextView tvSearch;

    @InjectView(R.id.rlrview_fragment_carrier)
    private RLRView rlrView;

    @InjectView(R.id.v_fragment_carrier_cover)
    private View vCover;

    @InjectView(R.id.ll_fragment_carrier_search)
    private LinearLayout llSearch;

    private CarrierAdapter adapter;
    private List<Map<String, String>> carriers;

    private boolean isOpenPop = false;
    private boolean isOpenWhitePop = false;
    private PopupWindow window;
    private ListView list;
    public static final String KEY = "key";
    ArrayList<Map<String, Object>> items;
    Context mContext;

    private int status;
    private int need;

    private String condition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_carrier, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        carriers = new ArrayList<>();
        init();
        setOCL();
        setRLRView();

    }

    private void init() {
        addSomething();
        if (Build.VERSION.SDK_INT >= 16) {
            llSearch.setBackground(DrawableUtils.getDrawable(15, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.qianGray)));
        } else {
            llSearch.setBackgroundDrawable(DrawableUtils.getDrawable(15, getResources().getColor(R.color.white), 2, getResources().getColor(R.color.qianGray)));
        }

    }

    private void setOCL() {
        btnImport.setOnClickListener(this);
        titleLayout.setOnClickListener(this);
        rlNeed.setOnClickListener(this);
        rlStatus.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        titleLayout.setOnClickListener(this);
    }

    private void setRLRView() {
        adapter = new CarrierAdapter(getActivity());
        rlrView.setAdapter(adapter);
        rlrView.setOnItemClickListener(adapter);
        rlrView.setOnRefreshListener(new RLRView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
        rlrView.setOnLoadListener(new RLRView.OnLoadListener() {
            @Override
            public void onLoad() {
                loadData(false);
            }
        });
    }

    private void loadData(final boolean isRefresh) {
//        safeFindCallback = new SafeFindCallback<Flight>(this) {
//            @Override
//            public void findResult(List<Flight> objects, AVException e) {
//                if (e == null) {
        if (isRefresh)
            rlrView.resetData(carriers);
        else rlrView.addData(carriers);
//                } else {//有错
//                    rlrView.rlError();
//                }
        rlrView.stopRL();//结束刷新加载
//            }
//        };
//        getMainQuery().findInBackground(safeFindCallback);//查询
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_fragment_carrier_import:
                intent = new Intent(getActivity(), ActivityCarrierImport.class);
                startActivity(intent);
                break;
            case R.id.ll_fragment_carrier_title:
                changGrayPopState(view);
                break;
            case R.id.rl_Fragment_carrier_status:
                changWhitePopState(view);
                break;
            case R.id.rl_Fragment_carrier_need:
                changWhitePopState(view);
                break;
            case R.id.tv_fragment_carrier_search:
                intent = new Intent(getActivity(), ActivitySearchCondition.class);
                startActivityForResult(intent, SEARCH_CONDITION);
                break;
        }
    }

    public void changGrayPopState(View v) {

        isOpenPop = !isOpenPop;
        if (isOpenPop) {
            ivTop.setBackgroundResource(R.drawable.up_white);
            popGrayWindow(v);

        } else {
            ivTop.setBackgroundResource(R.drawable.down_white);
            if (window != null) {
                window.dismiss();
                window = null;
            }
        }
    }


    public void changWhitePopState(View v) {

        isOpenWhitePop = !isOpenWhitePop;
        switch (v.getId()) {
            case R.id.rl_Fragment_carrier_status:
                if (isOpenWhitePop) {
                    ivStatsu.setBackgroundResource(R.drawable.up_blue);
                    tvStatus.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivStatsu.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window = null;
                    }
                }
                break;
            case R.id.rl_Fragment_carrier_need:
                if (isOpenWhitePop) {
                    ivNeed.setBackgroundResource(R.drawable.up_blue);
                    tvNeed.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivNeed.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window = null;
                    }
                }

                break;
        }
    }

    private void popGrayWindow(View parent) {
        LayoutInflater lay = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = lay.inflate(R.layout.pop_window_gray, null);
        list = (ListView) v.findViewById(R.id.pop_window_gray_list);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), topCreateData(),
                R.layout.pop_window_gray_list_item, new String[]{KEY},
                new int[]{R.id.pop_window_gray_item});
        list.setAdapter(adapter);
        list.setDivider(getResources().getDrawable(R.drawable.white_back));
        list.setDividerHeight(1);
        list.setItemsCanFocus(false);
        list.setOnItemClickListener(this);
        int x = (int) getResources().getDimension(R.dimen.pop_gray_x);
        int y = (int) getResources().getDimension(R.dimen.pop_gray_y_carrier);
        window = new PopupWindow(v, x, y);
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.gray_back));
        window.setFocusable(true);
        window.setOutsideTouchable(false);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                isOpenPop = false;
                ivTop.setBackgroundResource(R.drawable.down_white);
                vCover.setVisibility(View.INVISIBLE);
            }
        });
        window.update();
        window.setAnimationStyle(R.style.AnimationFade);
        window.showAtLocation(parent, Gravity.CENTER_HORIZONTAL | Gravity.TOP,
                0, (int) getResources().getDimension(R.dimen.pop_layout_gray_y));
        vCover.setVisibility(View.VISIBLE);

    }

    private void popWhiteWindow(final View parent) {
//        if (window == null) {
        LayoutInflater lay = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = lay.inflate(R.layout.pop_window_white, null);
        list = (ListView) v.findViewById(R.id.pop_window_white_list);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), filterCreateData(parent),
                R.layout.pop_window_white_list_item, new String[]{KEY},
                new int[]{R.id.pop_window_white_item});

        list.setAdapter(adapter);
        list.setDivider(getResources().getDrawable(R.drawable.gray_back));
        list.setDividerHeight(1);
        list.setItemsCanFocus(false);
        list.setOnItemClickListener(this);
        int y = (int) getResources().getDimension(R.dimen.pop_white_y_carrier);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int x = dm.widthPixels;
        window = new PopupWindow(v, x, y);
//        }
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_back));
        window.setFocusable(true);
        window.setOutsideTouchable(false);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                isOpenWhitePop = false;
                //加判断
                switch (parent.getId()) {
                    case R.id.rl_Fragment_carrier_status:
                        ivStatsu.setBackgroundResource(R.drawable.down_gray);
                        break;
                    case R.id.rl_Fragment_carrier_need:
                        ivNeed.setBackgroundResource(R.drawable.down_gray);
                        break;
                }
                vCover.setVisibility(View.INVISIBLE);
                resetTextColor();
            }

        });
        window.update();
        window.setAnimationStyle(R.style.AnimationFade);
        window.showAsDropDown(parent, 0, 2);
        vCover.setVisibility(View.VISIBLE);

    }

    public ArrayList<Map<String, Object>> filterCreateData(View v) {

        Map<String, Object> map;
        if (items != null) {
            items.clear();
        } else {
            items = new ArrayList<>();
        }
        switch (v.getId()) {
            case R.id.rl_Fragment_carrier_status:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "待租");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "待售");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "已租");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "已售");
                items.add(map);
                break;
            case R.id.rl_Fragment_carrier_need:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "出租");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "出售");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "合作");
                items.add(map);
                break;
        }
        return items;

    }

    public ArrayList<Map<String, Object>> topCreateData() {

        Map<String, Object> map;
        if (items != null) {
            items.clear();
        } else {
            items = new ArrayList<>();
        }

        map = new HashMap<>();
        map.put(KEY, "全部载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "土地载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "厂房载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "仓库载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "写字楼载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "商业载体");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "其他载体");
        items.add(map);

        return items;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String flag = ((Map<String, Object>) parent.getItemAtPosition(1)).get(KEY) + "";
        Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(i);

        switch (flag) {
            case "土地载体":
                switch (i) {
                    case 0:
                        tvTopTitle.setText("全部");
                        break;
                    case 1:
                        tvTopTitle.setText("土地");
                        break;
                    case 2:
                        tvTopTitle.setText("厂房");
                        break;
                    case 3:
                        tvTopTitle.setText("仓库");
                        break;
                    case 4:
                        tvTopTitle.setText("写字楼");
                        break;
                    case 5:
                        tvTopTitle.setText("商业");
                        break;
                    case 6:
                        tvTopTitle.setText("其他");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "待租":
                switch (i) {
                    case 0:
                        tvStatus.setText("状态");
                        break;
                    case 1:
                        tvStatus.setText("待租");
                        break;
                    case 2:
                        tvStatus.setText("待售");
                        break;
                    case 3:
                        tvStatus.setText("已租");
                        break;
                    case 4:
                        tvStatus.setText("已售");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "出租":
                switch (i) {
                    case 0:
                        tvNeed.setText("意向");
                        break;
                    case 1:
                        tvNeed.setText("出租");
                        break;
                    case 2:
                        tvNeed.setText("出售");
                        break;
                    case 3:
                        tvNeed.setText("合作");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
        }

    }

    private void resetTextColor() {
        tvNeed.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvStatus.setTextColor(getResources().getColor(R.color.filterTextGray));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SEARCH_CONDITION:
                if (resultCode == Activity.RESULT_OK) {
                    condition = data.getStringExtra("condition");
                    if (TextUtils.isEmpty(condition)) {
                        tvSearch.setText("搜索");
                    } else {
                        tvSearch.setText(condition);
                    }
                } else {
                    Utils.showToast(getActivity(), "您取消了搜索");
                }
                break;
        }
    }

    private void addSomething() {
        Map<String, String> map;
        map = new HashMap<>();
        map.put(KEY, "1");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "2");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "4");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "5");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "6");
        carriers.add(map);
        map.put(KEY, "1");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "2");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "4");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "5");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "6");
        carriers.add(map);
        map.put(KEY, "1");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "2");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "3");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "4");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "5");
        carriers.add(map);
        map = new HashMap<>();
        map.put(KEY, "6");
        carriers.add(map);
    }

}
