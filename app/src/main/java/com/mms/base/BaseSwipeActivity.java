package com.mms.base;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

/**
 * 基类，所有Activity必须继承该Activity，并在清单中声明，</br>
 * 例   &lt;activity  android:name="com.support.test.TwoActivity"  android:theme="@android:style/Theme.Translucent"/&gt;
 * <p/>
 * </br></br>
 * 不要在application节点和默认启动Activity下使用 android:theme="@android:style/Theme.Translucent"
 *
 * @author Young
 */


public abstract class BaseSwipeActivity extends BaseActivity {
    private boolean isTouchable = true;//是否可以右划返回，默认启动 的主Activity要设置为false
    private FrameLayout rootView;
    private static final String MAIN = "android.intent.action.MAIN";
    private int width;//屏幕宽度
    private final int time = 200;//动画时长

    public void setContentView(View root) {
        addContainer(root);

    }

    public void setContentView(int layoutRes) {
        addContainer(LayoutInflater.from(getApplicationContext()).inflate(layoutRes, null));

    }


    private void addContainer(View root) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();
        //把用户要显示的界面加入到可右划的布局中
        ContainerView frameLayout = new ContainerView(getApplicationContext());
        frameLayout.setBackgroundColor(Color.parseColor("#88000000"));
        frameLayout.addView(root);
        rootView = frameLayout;

        if (intent == null || MAIN.equals(intent.getAction())) {
            frameLayout.canTouch(false);
            isTouchable = false;
        }
        super.setContentView(rootView);
    }

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
        overridePendingTransition(0, 0);//取消用户设置的或系统默认的Activity切换动画
    }


    @Override
    public void finish() {
        if (isTouchable == false) {
            super.finish();
            overridePendingTransition(0, 0);//取消用户设置的或系统默认的Activity切换动画
            return;
        }
        // TODO Auto-generated method stub
        ObjectAnimator animator = ObjectAnimator.ofFloat(rootView.getChildAt(0), "translationX", 0, width);
        animator.setDuration(time * 2);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation);
                endToFinish();

            }
        });
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // TODO Auto-generated method stub
                Float cost = (Float) animation.getAnimatedValue();

                float alp = (float) (1 - cost / time / 2);
                rootView.setAlpha(alp);
                rootView.getChildAt(0).setAlpha(alp);
            }
        });
        animator.start();
        overridePendingTransition(0, 0);
    }

    private void endToFinish() {
        super.finish();
    }

    @Override//取消用户设置的或系统默认的Activity切换动画
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(0, 0);
    }


    /**
     * 可右划的容器
     *
     * @author Young
     */
    private final class ContainerView extends FrameLayout {

        /**
         * 按下时x坐标
         */
        private float downX;
        private float downY;
        private Context context;
        /**
         * 左侧8dp为右划感应区域
         */
        private final int scaledTouchSlop = 8;

        public ContainerView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            this.context = context;
            getScreenWidth();
        }

        public ContainerView(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.context = context;
            getScreenWidth();
        }

        public ContainerView(Context context) {
            super(context);
            this.context = context;
            getScreenWidth();
        }

        private void getScreenWidth() {
            // TODO Auto-generated method stub
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            width = wm.getDefaultDisplay().getWidth();
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            boolean intercepted = false;
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    intercepted = false;
                    downX = ev.getRawX();
                    downY = ev.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float rawX = ev.getRawX();
                    float rawY = ev.getRawY();
                    if (rawX - downX > Math.abs(rawY - downY) && Math.abs(rawX - downX) >= ViewConfiguration.get(context).getScaledTouchSlop() && downX < dip2px(scaledTouchSlop)) {
                        intercepted = true;
                    } else {
                        intercepted = false;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    intercepted = false;
                    break;
                default:
                    break;
            }
            return intercepted;
        }


//			if (ev.getAction()==MotionEvent.ACTION_DOWN) {
//				downX=ev.getRawX();
//				downY=ev.getRawX();
//				return super.onInterceptTouchEvent(ev);//交给子控件处理事件
//			}
//			if (ev.getAction()==MotionEvent.ACTION_MOVE) {
//				float x=ev.getRawX();
//				float y=ev.getRawX();
//				//ViewConfiguration.get(context).getScaledTouchSlop()   手机可以识别是最小滑动距离
//				if (x-downX>Math.abs(y-downY)&&Math.abs(x-downX)>=ViewConfiguration.get(context).getScaledTouchSlop()&&downX<dip2px(scaledTouchSlop ) ) {
//					return true;//交给自己的onTouchEvent(MotionEvent event)处理事件
//
//				}
//			}
//			return super.onInterceptTouchEvent(ev);


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // TODO Auto-generated method stub
            if (isTouchable == false) {
                return false;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (downX < dip2px(scaledTouchSlop) && getChildCount() == 1) {

                        getChildAt(0).setTranslationX(event.getRawX() - downX);
                        float alp = (float) ((event.getRawX() - downX) / width);
                        setAlpha(1 - alp);
//						getChildAt(0).setAlpha(1-alp/5);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (downX < dip2px(scaledTouchSlop) && getChildCount() == 1) {

                        float t = getChildAt(0).getTranslationX();

                        if (t > width / 2) {//

                            ObjectAnimator animator = ObjectAnimator.ofFloat(getChildAt(0), "translationX", t, width);
                            animator.setDuration(time);

                            ObjectAnimator.ofFloat(this, "translationX", t, width).setDuration(time).start();
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    // TODO Auto-generated method stub
                                    super.onAnimationEnd(animation);

                                    endToFinish();
                                }
                            });

                            animator.start();
                        } else {
                            ObjectAnimator animator = ObjectAnimator.ofFloat(getChildAt(0), "translationX", t, 0);
                            animator.setDuration(time);
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    // TODO Auto-generated method stub
                                    super.onAnimationEnd(animation);
                                    getChildAt(0).setAlpha(1);
                                    setAlpha(1);

                                }
                            });
                            animator.start();
                        }
                    }

                    break;
            }


            return true;
        }

        public int dip2px(float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }


        public void canTouch(boolean flag) {
            isTouchable = flag;
        }
    }
}

