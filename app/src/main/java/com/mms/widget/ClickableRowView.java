package com.mms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mms.R;

/**
 * Created by Tanikawa on 2016/5/3.
 */
public class ClickableRowView extends RelativeLayout {

    private TextView tvName;
    private String name;
    private TextView tvText;

    public ClickableRowView(Context context) {
        this(context, null);
    }

    public ClickableRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickableRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
        setView();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ClickableRowView);
            name = typedArray.getString(R.styleable.ClickableRowView_name);
            typedArray.recycle();
        }
    }

    private void initView() {
//        setBackground();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.clickable_row_view, this);
        tvName = (TextView) view.findViewById(R.id.tv_clickable_row_name);
        tvText = (TextView) view.findViewById(R.id.tv_clickable_row_text);
    }

    private void setView() {
        tvName.setText(name);
    }

    public void setName(String name) {
        this.name = name;
        setView();
    }

    public String getName() {
        return this.name;
    }

}
