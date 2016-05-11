package com.mms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.mms.base.BaseFragment;
import com.mms.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import roboguice.inject.InjectView;

/**
 * Created by Tanikawa on 2016/4/14.
 */
public class FragmentItem extends BaseFragment implements View.OnClickListener ,AdapterView.OnItemClickListener {

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
    private LinearLayout title_layout;

    @InjectView(R.id.rl_Fragment_item_from)
    private RelativeLayout rlFrom;

    @InjectView(R.id.rl_Fragment_item_state)
    private RelativeLayout rlState;

    @InjectView(R.id.rl_Fragment_item_level)
    private RelativeLayout rlLevel;

    @InjectView(R.id.rl_Fragment_item_progress)
    private RelativeLayout rlProgress;

    @InjectView(R.id.v_fragment_item_cover)
    private View vCover;

    private boolean isOpenPop = false;
    private boolean isOpenWhitePop = false;
    private PopupWindow window;
    private ListView list;
    public static final String KEY = "key";
    ArrayList<Map<String, Object>> items;
    Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_item, container,
                false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        setOCL();

    }

    private void setOCL() {
        btnAdd.setOnClickListener(this);
        title_layout.setOnClickListener(this);
        rlFrom.setOnClickListener(this);
        rlLevel.setOnClickListener(this);
        rlProgress.setOnClickListener(this);
        rlState.setOnClickListener(this);
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
                window=null;
            }
        }
    }

    public void changWhitePopState(View v) {

        isOpenWhitePop = !isOpenWhitePop;
        switch (v.getId()) {
            case R.id.rl_Fragment_item_from:
                if (isOpenWhitePop) {
                    ivFrom.setBackgroundResource(R.drawable.up_gray);
                    tvFrom.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivFrom.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window=null;
                    }
                }
                break;
            case R.id.rl_Fragment_item_level:
                if (isOpenWhitePop) {
                    ivLevel.setBackgroundResource(R.drawable.up_gray);
                    tvLevel.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivLevel.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window=null;
                    }
                }

                break;
            case R.id.rl_Fragment_item_progress:
                if (isOpenWhitePop) {
                    ivProgress.setBackgroundResource(R.drawable.up_gray);
                    tvProgress.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivProgress.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window=null;
                    }
                }
                break;
            case R.id.rl_Fragment_item_state:
                if (isOpenWhitePop) {
                    ivState.setBackgroundResource(R.drawable.up_gray);
                    tvState.setTextColor(getResources().getColor(R.color.textBlue));
                    popWhiteWindow(v);

                } else {
                    ivState.setBackgroundResource(R.drawable.down_gray);
                    if (window != null) {
                        window.dismiss();
                        window=null;
                    }
                }
                break;
        }
    }

    private void popGrayWindow(View parent) {
//        if (window == null) {
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
            // window = new PopupWindow(v, 260, 300);
            int x = (int) getResources().getDimension(R.dimen.pop_gray_x);
            int y = (int) getResources().getDimension(R.dimen.pop_gray_y);
//            int y= tvItem.getLayoutParams().height*5;
            window = new PopupWindow(v, x, y);
//        }
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
//        if (Build.VERSION.SDK_INT>=19){
//            window.showAsDropDown(parent, 0,0, Gravity.CENTER_HORIZONTAL | Gravity.TOP);
//        }else {
//            window.showAsDropDown(parent, 0,0);
//        }

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
            // window = new PopupWindow(v, 260, 300);
//            int x = (int) getResources().getDimension(R.dimen.pop_x);
            int y = (int) getResources().getDimension(R.dimen.pop_white_y);
//            int y= tvItem.getLayoutParams().height*5;
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int x=dm.widthPixels;
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
        window.showAsDropDown(parent, 0,0);
        vCover.setVisibility(View.VISIBLE);
//        window.showAtLocation(parent, Gravity.CENTER_HORIZONTAL | Gravity.TOP,
//                0, (int) getResources().getDimension(R.dimen.pop_layout_white_y));

    }



    public ArrayList<Map<String, Object>> filterCreateData(View v) {

        Map<String, Object> map;
        if (items!=null){
            items.clear();
        }else {
            items= new ArrayList<>();
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
        if (items!=null){
            items.clear();
        }else {
            items= new ArrayList<>();
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
                intent = new Intent(getActivity(),ActivityItemImport.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String flag = ((Map<String, Object>) parent.getItemAtPosition(1)).get(KEY)+"";
        Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(i);

        switch (flag) {
            case "土地项目":
                if (i==0){
                    tvTopTitle.setText("项目");
                }else {
                    tvTopTitle.setText(map.get(KEY) + "");
                }
                if (window != null) {
                    window.dismiss();
                    window=null;
                }
                break;
            case "选哪儿推送":
                if (i==0){
                    tvFrom.setText("来源");
                }else {
                    tvFrom.setText(map.get(KEY) + "");

                }
                if (window != null) {
                    window.dismiss();
                    window=null;
                }
                break;
            case "重点接洽":
                if (i==0){
                    tvState.setText("状态");
                }else {
                    tvState.setText(map.get(KEY) + "");
                }
                if (window != null) {
                    window.dismiss();
                    window=null;
                }
                break;
            case "★":
                if (i==0){
                    tvLevel.setText("等级");
                }else {
                    tvLevel.setText(map.get(KEY) + "");

                }
                if (window != null) {
                    window.dismiss();
                    window=null;
                }
                break;
            case "▋":
                if (i==0){
                    tvProgress.setText("进度");
                }else {
                    tvProgress.setText(map.get(KEY) + "");

                }
                if (window != null) {
                    window.dismiss();
                    window=null;
                }
                break;
        }

    }

    private void resetTextColor(){
        tvFrom.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvState.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvLevel.setTextColor(getResources().getColor(R.color.filterTextGray));
        tvProgress.setTextColor(getResources().getColor(R.color.filterTextGray));

    }

}
