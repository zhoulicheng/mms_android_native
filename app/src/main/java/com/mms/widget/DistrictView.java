package com.mms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mms.R;

/**
 * Created by Tanikawa on 2016/5/3.
 */
public class DistrictView extends RelativeLayout {

    private TextView tvDistrict;

    public DistrictView(Context context) {
        this(context, null);
    }

    public DistrictView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DistrictView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
        setView();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.ContactView);
        }
    }

    private void initView() {
//        setBackground();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.district_view, this);
        tvDistrict = (TextView) view.findViewById(R.id.tv_activity_item_import_district);

    }

    private void setView() {
    }

    public String getDistrict() {
        return tvDistrict.getText().toString();
    }

}
