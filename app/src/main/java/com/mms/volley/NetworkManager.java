package com.mms.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mms.volley.listener.RequestCallback;
import com.mms.volley.listener.RequestListCallback;

/**
 * Created by cwj on 16/1/12.
 * 网络管理器,队列单例(整个app一个队列)
 */
class NetworkManager {

    private static RequestQueue requestQueue;

    /**
     * 初始化网络请求队列,app共用一个
     *
     * @param context
     */
    public static void initNetwork(Context context) {
        if (context == null)
            return;
        if (requestQueue == null) {
            synchronized (NetworkManager.class) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                }
            }
        }
    }

    /**
     * 添加一个请求到队列
     *
     * @param request
     * @param <T>
     */
    public static <T> void addToQueue(RequestModel<T> request) {
        if (request == null || requestQueue == null)
            return;
        if (request.getRequestCallback() != null) {
            if (request.getRequestCallback() instanceof RequestCallback)//对象
                requestQueue.add(new RequestObject<>(request));
            else if (request.getRequestCallback() instanceof RequestListCallback)//数组
                requestQueue.add(new RequestListObject<>(request));
        } else {//普通请求
            Request<T> req = new RequestObject<>(request);
            requestQueue.add(req);
        }
    }

    /**
     * 根据tag取消请求
     */
    public static void cancelRequest(Object tag) {
        if (requestQueue == null || tag == null)
            return;
        requestQueue.cancelAll(tag);
    }
}
