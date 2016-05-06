package com.mms.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.mms.volley.Network;

import roboguice.fragment.RoboFragment;

/**
 * Created by Tanikawa on 2016/4/11.
 */
public abstract class BaseFragment extends RoboFragment {

    protected final Object NETWORK_TAG = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity fa = getActivity();
        if (fa instanceof BaseFragmentActivity)
            ((BaseFragmentActivity) fa).setOnBackPress(this);
    }

    /**
     * 显示fragment的通用方法,自动调用一次onResume
     */
    public void showFragment() {
        if (this.isHidden()) {
            getManager().beginTransaction().show(this).commitAllowingStateLoss();
            onResume();
        }
    }

    /**
     * 隐藏fragment的通用方法,自动调用一次onPause
     */
    public void hideFragment() {
        if (this.isVisible()) {
            getManager().beginTransaction().hide(this).commitAllowingStateLoss();
            onPause();
        }
    }

    /**
     * 拿到FragmentManager
     */
    public FragmentManager getManager() {
        if (!this.isAdded())//还没添加
            return null;
        if (getParentFragment() != null)
            return getParentFragment().getChildFragmentManager();
        return getActivity().getSupportFragmentManager();
    }

    /**
     * 回退事件
     *
     * @return false为未消费, true为消费
     */
    public boolean onBackPress() {
        return false;
    }

    @Override
    public void onDestroy() {
        Network.cancelRequest(NETWORK_TAG);
        super.onDestroy();
    }

}
