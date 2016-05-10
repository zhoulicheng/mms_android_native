package com.mms.widget.CustomSpinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mms.R;
import com.mms.widget.CustomSpinner.TextAdapter.OnItemClickListener;

public class ViewList extends LinearLayout implements ViewBaseAction {

    private Context mContext;
    private ListView mListView;
    private TextAdapter adapter;
    private String[] items;//显示字段
    private String[] itemsValue;//隐藏id
    private String showText;
    private OnSelectListener mOnSelectListener;

    public ViewList(Context context, String[] items, String[] itemsVaule) {
        super(context);
        this.items = items;
        this.itemsValue = itemsVaule;
        init(context);
    }

    public ViewList(Context context, AttributeSet attrs,
                    String[] items, String[] itemsVaule) {
        super(context, attrs);
        this.items = items;
        this. itemsValue = itemsVaule;
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_list_popuwindow, this, true);
        mListView = (ListView) findViewById(R.id.list);

        if (items != null && itemsValue != null) {
            adapter = new TextAdapter(mContext, items, R.color.white, R.color.white, 0);
            adapter.setTextSize(15);
            adapter.setTextColor(mContext.getResources()
                    .getColor(R.color.app_custom_popup_view_list_text));
            mListView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    if (mOnSelectListener != null) {
                        showText = items[position];
                        mOnSelectListener.getValue(itemsValue[position], items[position]);
                    }
                }
            });
        }
    }

    /**
     * 默认选择项显示
     *
     * @param showText
     */
    public void setDefaultShowText(String showText) {
        this.showText = showText;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void getValue(String distance, String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}
