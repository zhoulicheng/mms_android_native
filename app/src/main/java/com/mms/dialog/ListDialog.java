package com.mms.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Tanikawa on 2016/4/12.
 */
public class ListDialog extends Dialog {

    public ListDialog(Context context) {
        super(context);
    }

    public ListDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public ListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
