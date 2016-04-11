package com.mms.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by cwj on 16/1/14. UI鐩稿叧宸ュ叿
 */
public class UIUtils {

	/**
	 * 灏哾p鍗曚綅杞负px鍗曚綅
	 */
	public static int dp2px(Context context, float dpValue) {

		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpValue, context.getResources().getDisplayMetrics());

	}

	/**
	 * 灏唖p鍗曚綅杞负px鍗曚綅
	 */
	public static int sp2px(Context context, float spValue) {

		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spValue, context.getResources().getDisplayMetrics());

	}

	/**
	 * 鑾峰彇灞忓箷瀹藉害
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidthPX(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 鑾峰彇灞忓箷楂樺害
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeightPX(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 鑾峰彇灞忓箷瀹介珮鍍忕礌鍊�
	 * 
	 * @param context
	 *            涓婁笅鏂�
	 * @return 杩斿洖瀹介珮鏁扮粍
	 */
	public static int[] getScreenWidthHeightPX(Context context) {
		return new int[] { getScreenWidthPX(context),
				getScreenHeightPX(context) };
	}
}
