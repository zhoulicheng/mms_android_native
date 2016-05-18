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
 * Created by Tanikawa on 2016/4/14.
 */
public class FragmentItem extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final int SEARCH_CONDITION = 1;

    @InjectView(R.id.btn_fragment_item_add)
    private Button btnAdd;

    @InjectView(R.id.iv_fragment_item_top)
    private ImageView ivTop;

    @InjectView(R.id.iv_fragment_item_from)
    private ImageView ivFrom;

    @InjectView(R.id.iv_fragment_item_state)
    private ImageView ivState;

    @InjectView(R.id.iv_fragment_item_level)
    private ImageView ivLevel;

    @InjectView(R.id.iv_fragment_item_progress)
    private ImageView ivProgress;

    @InjectView(R.id.tv_fragment_item_top_title)
    private TextView tvTopTitle;

    @InjectView(R.id.tv_fragment_item_from_title)
    private TextView tvFrom;

    @InjectView(R.id.tv_fragment_item_state_title)
    private TextView tvState;

    @InjectView(R.id.tv_fragment_item_level_title)
    private TextView tvLevel;

    @InjectView(R.id.tv_fragment_item_progress_title)
    private TextView tvProgress;

    @InjectView(R.id.ll_fragment_item_title)
    private LinearLayout titleLayout;

    @InjectView(R.id.rl_Fragment_item_from)
    private RelativeLayout rlFrom;

    @InjectView(R.id.rl_Fragment_item_state)
    private RelativeLayout rlState;

    @InjectView(R.id.rl_Fragment_item_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.rl_Fragment_item_progress)
    private RelativeLayout rlProgress;

    @InjectView(R.id.tv_fragment_item_search)
    private TextView tvSearch;

    @InjectView(R.id.rlrview_fragment_item)
    private RLRView rlrView;

    @InjectView(R.id.v_fragment_item_cover)
    private View vCover;

    @InjectView(R.id.ll_fragment_item_search)
    private LinearLayout llSearch;

    private CarrierAdapter adapter;
    private List<Map<String,String>> carriers;

    private boolean isOpenPop = false;
    private boolean isOpenWhitePop = false;
    private PopupWindow window;
    private ListView list;
    public static final String KEY = "key";
    ArrayList<Map<String, Object>> items;
    Context mContext;

    private int isFrom;
    private int status;
    private int level;
    private int progress;

    private String condition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_item, container,
                false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        carriers = new ArrayList<>();
        //加假数据
        addSomething();
        initView();
        setOCL();
        setRLRView();
    }

    private void initView(){
        if (Build.VERSION.SDK_INT>=16){
            llSearch.setBackground(DrawableUtils.getDrawable(15,getResources().getColor(R.color.white),2,getResources().getColor(R.color.qianGray)));
        }else {
            llSearch.setBackgroundDrawable(DrawableUtils.getDrawable(15,getResources().getColor(R.color.white),2,getResources().getColor(R.color.qianGray)));
        }

    }

    private void setOCL() {
        btnAdd.setOnClickListener(this);
        titleLayout.setOnClickListener(this);
        rlFrom.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        rlProgress.setOnClickListener(this);
        rlState.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
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
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ll_fragment_item_title:
                changGrayPopState(view);
                break;
            case R.id.rl_Fragment_item_from:
                changWhitePopState(view);
                break;
            case R.id.rl_Fragment_item_level:
                changWhitePopState(view);
                break;
            case R.id.rl_Fragment_item_progress:
                changWhitePopState(view);
                break;
            case R.id.rl_Fragment_item_state:
                changWhitePopState(view);
                break;
            case R.id.btn_fragment_item_add:
                intent = new Intent(getActivity(), ActivityItemImport.class);
                startActivity(intent);
                break;
            case R.id.tv_fragment_item_search:
                intent = new Intent(getActivity(),ActivitySearchCondition.class);
                startActivityForResult(intent,SEARCH_CONDITION);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SEARCH_CONDITION:
                if (resultCode== Activity.RESULT_OK){
                    condition = data.getStringExtra("condition");
                    if (TextUtils.isEmpty(condition)){
                        tvSearch.setText("搜索");
                    }else {
                        tvSearch.setText(condition);
                    }
                }else {
                    Utils.showToast(getActivity(),"您取消了搜索");
                }
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
            case R.id.rl_Fragment_item_from:
                if (isOpenWhitePop) {
                    ivFrom.setBackgroundResource(R.drawable.up_blue);
                    tvFrom.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivFrom.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window = null;
                    }
                }
                break;
            case R.id.rl_Fragment_item_level:
                if (isOpenWhitePop) {
                    ivLevel.setBackgroundResource(R.drawable.up_blue);
                    tvLevel.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivLevel.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window = null;
                    }
                }

                break;
            case R.id.rl_Fragment_item_progress:
                if (isOpenWhitePop) {
                    ivProgress.setBackgroundResource(R.drawable.up_blue);
                    tvProgress.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivProgress.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window = null;
                    }
                }
                break;
            case R.id.rl_Fragment_item_state:
                if (isOpenWhitePop) {
                    ivState.setBackgroundResource(R.drawable.up_blue);
                    tvState.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivState.setBackgroundResource(R.drawable.down_gray);
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
        int y = (int) getResources().getDimension(R.dimen.pop_gray_y_item);
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
        int y = (int) getResources().getDimension(R.dimen.pop_white_y_item);
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
                    case R.id.rl_Fragment_item_from:
                        ivFrom.setBackgroundResource(R.drawable.down_gray);
                        break;
                    case R.id.rl_Fragment_item_level:
                        ivLevel.setBackgroundResource(R.drawable.down_gray);
                        break;
                    case R.id.rl_Fragment_item_progress:
                        ivProgress.setBackgroundResource(R.drawable.down_gray);
                        break;
                    case R.id.rl_Fragment_item_state:
                        ivState.setBackgroundResource(R.drawable.down_gray);
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
            case R.id.rl_Fragment_item_from:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "选哪儿推送");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "自己录入");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "好友推送");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "继承项目");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "系统推送");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "联动招商");
                items.add(map);
                break;
            case R.id.rl_Fragment_item_state:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "重点接洽");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "接洽中");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "暂缓跟进");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "成功项目");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "回收站");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "未查看");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "未跟进");
                items.add(map);
                break;
            case R.id.rl_Fragment_item_level:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "★");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "★★");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "★★★");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "★★★★");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "★★★★★");
                items.add(map);
                break;
            case R.id.rl_Fragment_item_progress:
                map = new HashMap<>();
                map.put(KEY, "不限");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "▋");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "▋▋");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "▋▋▋");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "▋▋▋▋");
                items.add(map);
                map = new HashMap<>();
                map.put(KEY, "▋▋▋▋▋");
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
        map.put(KEY, "全部项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "土地项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "厂房项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "仓库项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "写字楼项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "注册项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "商业项目");
        items.add(map);
        map = new HashMap<>();
        map.put(KEY, "其他项目");
        items.add(map);

        return items;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String flag = ((Map<String, Object>) parent.getItemAtPosition(1)).get(KEY) + "";
        Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(i);

        switch (flag) {
            case "土地项目":
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
                        tvTopTitle.setText("注册");
                        break;
                    case 6:
                        tvTopTitle.setText("商业");
                        break;
                    case 7:
                        tvTopTitle.setText("其他");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "选哪儿推送":
                switch (i) {
                    case 0:
                        tvFrom.setText("来源");
                        break;
                    case 1:
                        tvFrom.setText("选哪儿");
                        break;
                    case 2:
                        tvFrom.setText("录入");
                        break;
                    case 3:
                        tvFrom.setText("好友");
                        break;
                    case 4:
                        tvFrom.setText("继承");
                        break;
                    case 5:
                        tvFrom.setText("系统");
                        break;
                    case 6:
                        tvFrom.setText("联动");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "重点接洽":
                switch (i) {
                    case 0:
                        tvState.setText("状态");
                        break;
                    case 1:
                        tvState.setText("重点");
                        break;
                    case 2:
                        tvState.setText("接洽");
                        break;
                    case 3:
                        tvState.setText("暂缓");
                        break;
                    case 4:
                        tvState.setText("成功");
                        break;
                    case 5:
                        tvState.setText("回收");
                        break;
                    case 6:
                        tvState.setText("未查看");
                        break;
                    case 7:
                        tvState.setText("未跟进");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "★":
                switch (i) {
                    case 0:
                        tvLevel.setText("等级");
                        break;
                    case 1:
                        tvLevel.setText("一星");
                        break;
                    case 2:
                        tvLevel.setText("二星");
                        break;
                    case 3:
                        tvLevel.setText("三星");
                        break;
                    case 4:
                        tvLevel.setText("四星");
                        break;
                    case 5:
                        tvLevel.setText("五星");
                        break;
                }
                if (window != null) {
                    window.dismiss();
                    window = null;
                }
                break;
            case "▋":
                switch (i) {
                    case 0:
                        tvProgress.setText("进度");
                        break;
                    case 1:
                        tvProgress.setText("进度一");
                        break;
                    case 2:
                        tvProgress.setText("进度二");
                        break;
                    case 3:
                        tvProgress.setText("进度三");
                        break;
                    case 4:
                        tvProgress.setText("进度四");
                        break;
                    case 5:
                        tvProgress.setText("进度五");
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
        tvFrom.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvState.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvLevel.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvProgress.setTextColor(getResources().getColor(R.color.filterTextGray));

    }

    private void addSomething(){
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
