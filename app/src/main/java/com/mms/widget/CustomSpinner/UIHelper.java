package com.mms.widget.CustomSpinner;

import android.widget.PopupWindow;

import java.lang.reflect.Method;

public class UIHelper {
	/**
	 * @author lmz
	 * @date 2015-2-8
     * Set whether this window is touch modal or if outside touches will be sent
     * to
     * other windows behind it.
     * ����ⲿ����,��������ʧ,���ǵ���¼����������activity����
     */
    public static void setPopupWindowTouchModal(PopupWindow popupWindow,
            boolean touchModal) {
        if (null == popupWindow) {
            return;
        }
        Method method;
        try {

            method = PopupWindow.class.getDeclaredMethod("setTouchModal",
                    boolean.class);
            method.setAccessible(true);
            method.invoke(popupWindow, touchModal);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
