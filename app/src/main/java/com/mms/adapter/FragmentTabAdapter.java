package com.mms.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.mms.R;
import com.mms.base.BaseFragment;

import java.util.List;

public class FragmentTabAdapter implements OnCheckedChangeListener {
	private FragmentActivity fragmentActivity = null;
	private List<BaseFragment> fragments = null;
	private int fragmentContentId;
	private RadioGroup radioGroup = null;
	private int currentTab;

	public FragmentTabAdapter(FragmentActivity fragmentActivity,
							  List<BaseFragment> fragments, int fragmentContentId,
							  RadioGroup radioGroup) {
		this.fragmentActivity = fragmentActivity;
		this.fragments = fragments;
		this.fragmentContentId = fragmentContentId;
		this.radioGroup = radioGroup;
		currentTab = 0;
		setListener();
		defaultTab(currentTab);
	}


	private void setListener() {
		// TODO Auto-generated method stub
		radioGroup.setOnCheckedChangeListener(this);
	}

	private void defaultTab(int currentTab) {
		// TODO Auto-generated method stub
		FragmentTransaction transaction = fragmentActivity
				.getSupportFragmentManager().beginTransaction();
		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			transaction.add(fragmentContentId, fragment);
			transaction.hide(fragment);
		}
		transaction.commit();
		transaction.show(getCurrentFragment());// Ĭ����ʾ��һҳ
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < radioGroup.getChildCount(); i++) {
			if (radioGroup.getChildAt(i).getId() == checkedId) {
				Fragment fragment = fragments.get(i);
				FragmentTransaction ft = obtainFragmentTransaction(i);

				getCurrentFragment().onPause();
				if (fragment.isAdded()) {
					fragment.onResume();
				} else {
					ft.add(fragmentContentId, fragment);
				}
				showTab(i);
				ft.commit();
			}
		}

	}

	private Fragment getCurrentFragment() {
		// TODO Auto-generated method stub
		return fragments.get(currentTab);
	}

	private void showTab(int idx) {
		// TODO Auto-generated method stub
		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(idx);
			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commit();
		}
		currentTab = idx;
	}

	private FragmentTransaction obtainFragmentTransaction(int index) {
		// TODO Auto-generated method stub
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager()
				.beginTransaction();
		if (index > currentTab) {
			ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
		} else {
			ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
		}
		return ft;
	}

}
