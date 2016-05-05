package com.mms.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.mms.imageLoader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 可监听滑动的ScrollView
 * 滚动时Image不加载
 * 可添加多个滚动监听器
 */
public class SafeScrollView extends ScrollView {

    /**
     * 用于解决ScrollView横向滑动失效的问题
     */
    private GestureDetector mGestureDetector;


    /**
     * 空闲
     */
    public static final int IDLE = 0;

    /**
     * 触摸滚动
     */
    public static final int TOUCH_SCROLL = 1;

    /**
     * 自由滑动
     */
    public static final int FLING = 2;

    private static final int HANDLE_DELAY = 50;

    private int currentY = Integer.MIN_VALUE;

    private int scrollState = IDLE;

    private List<OnScrollStateChangedListener> listeners = new ArrayList<>();

    private Handler mHandler = new Handler();

    public interface OnScrollStateChangedListener {
        void onScrollStateChanged(int scrollState);
    }

    public SafeScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SafeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SafeScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        //解决横向滑动事件被拦截的问题(本来想解决右划退出的不过并没有卵用)，此代码本意是用来解决ScrollView嵌套ViewPager滑动失效和无法正常滑动冲突问题
        mGestureDetector = new GestureDetector(context, new YScrollDetector());

        addOnScrollStateChangedListener(new OnScrollStateChangedListener() {
            @Override
            public void onScrollStateChanged(int scrollState) {
                switch (scrollState) {
                    case FLING:
                        if (ImageLoader.isInitial())
                            ImageLoader.pause();
                        break;
                    default:
                        if (ImageLoader.isInitial())
                            ImageLoader.resume();
                        break;
                }
            }
        });
    }

    private Runnable scrollRunnable = new Runnable() {

        @Override
        public void run() {
            if (getScrollY() == currentY) {
                if (scrollState != IDLE) {//改变时才调用
                    for (OnScrollStateChangedListener listener : listeners) {
                        listener.onScrollStateChanged(IDLE);
                    }
                }
                scrollState = IDLE;
                mHandler.removeCallbacks(this);
            } else {
                if (scrollState != FLING) {//改变时才调用
                    for (OnScrollStateChangedListener listener : listeners) {
                        listener.onScrollStateChanged(FLING);
                    }
                }
                scrollState = FLING;
                currentY = getScrollY();
                mHandler.postDelayed(this, HANDLE_DELAY);
            }
        }
    };

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (scrollState != TOUCH_SCROLL) {//改变时才调用
                    for (OnScrollStateChangedListener listener : listeners) {
                        listener.onScrollStateChanged(TOUCH_SCROLL);
                    }
                }
                this.scrollState = TOUCH_SCROLL;
                currentY = getScrollY();
                mHandler.removeCallbacks(scrollRunnable);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.post(scrollRunnable);
                break;
        }
        return super.onTouchEvent(ev);
//        return false;
    }

    /**
     * 可添加多个滚动监听器
     */
    public void addOnScrollStateChangedListener(OnScrollStateChangedListener listener) {
        this.listeners.add(listener);
    }

    /**
     * 用于解决ScrollView横向滑动失效的问题
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
//        return false;
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            //如果我们滚动更接近水平方向,返回false,让子视图来处理它
            return (Math.abs(distanceY) > Math.abs(distanceX));
        }
    }
}
