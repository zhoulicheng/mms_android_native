package com.mms.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {

	public static void showToast(Context context, String content, int duration) {
		if (context == null
				|| (context instanceof Activity && ((Activity) context)
						.isFinishing()))
			return;
		Toast.makeText(context, content, duration).show();
	}

	public static void showToast(Context context, String content) {
		showToast(context, content, Toast.LENGTH_SHORT);
	}

	public static boolean compareDate(int bYear, int bMonth, int bDay,
			int eYear, int eMonth, int eDay) {

		if (bYear > eYear) {
			return false;
		} else if (bYear == eYear) {
			if (bMonth > eMonth) {
				return false;
			} else if (bMonth == eMonth) {
				if (bDay > eDay) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Snack提示
	 */
	public static void showSnack(View view, String content, int duration) {
		if (view == null)
			return;
		Snackbar.make(view, content, duration).show();
	}

	public static void showSnack(View view, String content) {
		showSnack(view, content, Snackbar.LENGTH_SHORT);
	}

	/**
	 * 判断是否连入wifi
	 */
	public static boolean isWifiConnected(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != cm) {
				NetworkInfo networkInfo = cm.getActiveNetworkInfo();
				if ((null != networkInfo) && (ConnectivityManager.TYPE_WIFI == networkInfo.getType())) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * raw文件读取内容
	 */
	public static String readRawFile(Context context, int id) {
		StringBuilder sb = new StringBuilder("");
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = context.getResources().openRawResource(
					id);
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			String tmp;
			while ((tmp = bufferedReader.readLine()) != null) {
				sb.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 获取数据库中的name
	 */
	public static String getRealCityName(String name) {
		if (name.charAt(name.length() - 1) == '市')//XXX市的省略市
			name = name.substring(0, name.length() - 1);
		return name;
	}

	/**
	 * 检验手机号是否正确
	 */
	public static boolean isCorrectPhone(String phone) {
		return !TextUtils.isEmpty(phone) && phone.matches("^1(3|4|5|7|8)\\d{9}");
	}

	/**
	 * 将list集合组成string
	 */
	public static String getListStr(List<String> list, String space) {
		if (list == null || list.size() <= 0)
			return "";
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i != list.size() - 1)//末尾不加space
				sb.append(space);
		}
		return sb.toString();
	}

}
