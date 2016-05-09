package com.mms.widget.CustomSpinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mms.R;
import com.mms.widget.CustomSpinner.TextAdapter.OnItemClickListener;

public class ViewList extends LinearLayout implements ViewBaseAction {

	private Context mContext;
	private ListView mListView;
	private ImageView ivUpOne,ivUpTwo,ivUpThree,ivUpFour;
	private TextAdapter adapter;	
	private String[] items ;//= new String[] {"不限","出售","出租","求购","求租"};//显示字段
	private String[] itemsVaule ;//= new String[] { "1", "2", "3", "4", "5"};//隐藏id
	private String showText;
	private OnSelectListener mOnSelectListener;
	private int showWhichUp=1;
	
	public ViewList(Context context, String[] items, String[] itemsVaule, int showWhichUp) {
		super(context);
		this.items = items;
		this.itemsVaule = itemsVaule;
		this.showWhichUp = showWhichUp;
		init(context);
	}
	
	public ViewList(Context context, AttributeSet attrs,
					String[] items, String[] itemsVaule, int showWhichUp){
		super(context, attrs);
		this.items = items;
		this.itemsVaule = itemsVaule;
		this.showWhichUp = showWhichUp;
		init(context);
	}
	
	public void init(Context context){
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_list_popuwindow, this, true);
		mListView = (ListView) findViewById(R.id.list);
		ivUpOne = (ImageView) findViewById(R.id.ivUpOne);
		ivUpTwo = (ImageView) findViewById(R.id.ivUpTwo);
		ivUpThree = (ImageView) findViewById(R.id.ivUpThree);
		ivUpFour = (ImageView) findViewById(R.id.ivUpFour);
		
		ivUpOne.setImageResource(R.color.transparent);
		ivUpTwo.setImageResource(R.color.transparent);
		ivUpThree.setImageResource(R.color.transparent);
		ivUpFour.setImageResource(R.color.transparent);		
		if(showWhichUp==1){
			ivUpOne.setImageResource(R.drawable.icon_popuwindow_up);
		}else if(showWhichUp==2){
			ivUpTwo.setImageResource(R.drawable.icon_popuwindow_up);
		}else if(showWhichUp==3){
			ivUpThree.setImageResource(R.drawable.icon_popuwindow_up);
		}else if(showWhichUp==4){
			ivUpFour.setImageResource(R.drawable.icon_popuwindow_up);
		}
		if(items!=null&&itemsVaule!=null){
			adapter = new TextAdapter(mContext, items, R.color.white, R.color.white,0);
			adapter.setTextSize(14);
			adapter.setTextColor(mContext.getResources()
					.getColor(R.color.app_custom_popup_view_list_text));
			mListView.setAdapter(adapter);
			adapter.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(View view, int position) {
					if (mOnSelectListener != null) {
						showText = items[position];
						mOnSelectListener.getValue(itemsVaule[position], items[position]);
					}				
				}
			});
		}		
	}
	
	/**
	 * 默认选择项显示
	 * @param showText
	 */
	public void setDefaultShowText(String showText){
		this.showText = showText;
	}
	
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String distance, String showText);
	}

	@Override
	public void hide() {

	}

	@Override
	public void show() {

	}

}
