package com.mms.widget.CustomSpinner;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;

import com.mms.R;
import com.mms.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义下拉菜单控件
 *
 * @author linmz
 */
public class ExpandTabView extends LinearLayout implements OnDismissListener {

    private final int SMALL = 0;
    private Context mContext;
    private int displayWidth;
    private int displayHeight;
    private CustomToggleButton selectedButton;
    private int selectPosition;
    private List<CustomToggleButton> mToggleButton = new ArrayList<CustomToggleButton>();
    private ArrayList<String> mTextArray = new ArrayList<String>();
    private ArrayList<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
    private PopupWindow popupWindow;
    private View vBg;//pupowindow底部遮罩层

    public ExpandTabView(Context context) {
        super(context);
        init(context);
    }

    public ExpandTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        setOrientation(LinearLayout.HORIZONTAL);

    }

    /**
     * 根据选择的位置设置tabitem显示的值
     *
     * @param valueText
     * @param position
     */
    public void setTitle(String valueText, int position) {
        if (position < mToggleButton.size()) {
            mToggleButton.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置获取tabitem显示的值
     *
     * @param position
     * @return
     */
    public String getTitle(int position) {
        if (position < mToggleButton.size() && mToggleButton.get(position).getText() != null) {
            return mToggleButton.get(position).getText().toString();
        }
        return "";
    }

    /**
     * 设置tabitem的个数和初始值
     *
     * @param textArray
     * @param viewArray
     * @param v_bg      设置在pupowindow底部的遮罩层
     */
    public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray, final View v_bg) {
        vBg = v_bg;
        if (mContext == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTextArray = textArray;
        for (int i = 0; i < viewArray.size(); i++) {
            RelativeLayout r = new RelativeLayout(mContext);
            int maxHeight = (int) (displayHeight * 0.7);
            RelativeLayout.LayoutParams rl = new RelativeLayout
                    .LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, maxHeight);
//            rl.leftMargin = 10;
//            rl.rightMargin = 10;
            r.addView(viewArray.get(i), rl);
            mViewArray.add(r);
            r.setTag(SMALL);
            CustomToggleButton tButton = (CustomToggleButton) inflater
                    .inflate(R.layout.toggle_button, this, false);
            addView(tButton);

            mToggleButton.add(tButton);
            tButton.setTag(i);
            tButton.setText(mTextArray.get(i));
            r.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    onPressBack();

                }
            });

            r.setBackgroundColor(mContext.getResources().getColor(R.color.app_custom_popup_bottom_bg));
            tButton.setOnToggleListener(new CustomToggleButton.OnToggleListener() {

                @Override
                public void onClick(View v) {
                    if (vBg != null) {
                        vBg.setVisibility(View.VISIBLE);
                    }
                    CustomToggleButton tButton = (CustomToggleButton) v;
                    if (selectedButton != null && selectedButton != tButton) {
                        selectedButton.setChecked(false);
                        mToggleButton.set(selectPosition, selectedButton);
                    }
                    selectedButton = tButton;
                    selectPosition = (Integer) selectedButton.getTag();
                    startAnimation();
                    if (mOnButtonClickListener != null && tButton.isChecked()) {
                        mOnButtonClickListener.onClick(selectPosition);
                    }

                }
            });
        }

    }

    private void startAnimation() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
//			popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.color.transparent));
        }
        UIHelper.setPopupWindowTouchModal(popupWindow, false);
        if (selectedButton.isChecked()) {
            if (!popupWindow.isShowing()) {
                showPopup(selectPosition);
            } else {
                popupWindow.setOnDismissListener(this);
                popupWindow.dismiss();
                hideView();
            }
            setBottomBg();
        } else {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                hideView();
            }
            setBottomBg();
        }
    }

    private void showPopup(int position) {
        if (vBg != null) {
            vBg.setVisibility(View.VISIBLE);
        }
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.show();
        }
        if (popupWindow.getContentView() != mViewArray.get(position)) {
            popupWindow.setContentView(mViewArray.get(position));
        }
        popupWindow.showAsDropDown(this, 0, 0);
    }

    /**
     * 如果菜单成展开状态，则让菜单收回去
     */
    public boolean onPressBack() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            hideView();
            if (selectedButton != null) {
                selectedButton.setChecked(false);
            }
            setBottomBg();
            return true;
        } else {
            return false;
        }
    }

    private void hideView() {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.hide();
        }
    }

    /**
     * 隐藏或显示遮罩层
     */
    private void setBottomBg() {
        int count = 0;
        for (int i = 0; i < mToggleButton.size(); i++) {
            if (!mToggleButton.get(i).isChecked()) {
                ++count;
            }
        }
        if (count == mToggleButton.size()) {
            if (vBg != null) {
                vBg.setVisibility(View.GONE);
            }
        } else {
            if (vBg != null) {
                vBg.setVisibility(View.VISIBLE);
            }
        }
    }

    private OnButtonClickListener mOnButtonClickListener;

    /**
     * 设置tabitem的点击监听事件
     */
    public void setOnButtonClickListener(OnButtonClickListener l) {
        mOnButtonClickListener = l;
    }

    /**
     * 自定义tabitem点击回调接口
     */
    public interface OnButtonClickListener {
        public void onClick(int selectPosition);
    }

    @Override
    public void onDismiss() {
        showPopup(selectPosition);
        popupWindow.setOnDismissListener(null);
        Utils.showToast(mContext,"窗体消失了");

    }
}
