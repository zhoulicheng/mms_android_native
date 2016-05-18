package com.mms.widget.uploadImagesLayout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mms.activity.BigBitmapActivity;
import com.mms.imageLoader.ImageLoader;
import com.mms.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传图片列表布局
 * 共两行,每行4张
 */
public class UploadImagesLayout extends LinearLayout {

    private static final int NUM_OF_ROW = 4;

    private LinearLayout firstLayout;
    private LinearLayout secondLayout;

    private List<ImageView> imageViews = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();

    public UploadImagesLayout(Context context) {
        this(context, null);
    }

    public UploadImagesLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UploadImagesLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        this.setVisibility(GONE);
        addLines();
        addImages();
        setListener();
    }

    private void setListener() {
        for (int i = 0; i < imageViews.size(); i++) {
            final int finalI = i;
            imageViews.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI < urls.size()) {//有图片
                        Intent intent = new Intent(getContext(), BigBitmapActivity.class);
                        intent.putStringArrayListExtra(BigBitmapActivity.INTENT_URL_KEY, urls);
                        intent.putExtra(BigBitmapActivity.INTENT_FIRST_PAGE_KEY, finalI);
                        getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public void clearData() {
        this.urls.clear();
        notifyDataChanged();
    }

    public void resetData(List<String> urls) {
        this.urls.clear();
        addData(urls);
    }

    public void addData(List<String> urls) {
        this.urls.addAll(urls);
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        //layout
        if (urls.size() <= 0) {//无数据
            this.setVisibility(GONE);
            firstLayout.setVisibility(GONE);
            secondLayout.setVisibility(GONE);
        } else if (urls.size() <= NUM_OF_ROW) {//只有1行
            this.setVisibility(VISIBLE);
            firstLayout.setVisibility(VISIBLE);
            secondLayout.setVisibility(GONE);
        } else {
            this.setVisibility(VISIBLE);
            firstLayout.setVisibility(VISIBLE);
            secondLayout.setVisibility(VISIBLE);
        }
        //imageView
        for (int i = 0; i < imageViews.size(); i++) {
            if (i < urls.size()) {//show
                imageViews.get(i).setVisibility(VISIBLE);
                ImageLoader.displayImage(imageViews.get(i), urls.get(i));
            } else {//hide
                imageViews.get(i).setVisibility(INVISIBLE);
                imageViews.get(i).setImageDrawable(null);
            }
        }
    }

    private void addImages() {
        for (int i = 0; i < NUM_OF_ROW; i++) {
            firstLayout.addView(getRelativeLayout());
        }
        for (int i = 0; i < NUM_OF_ROW; i++) {
            secondLayout.addView(getRelativeLayout());
        }
    }

    private RelativeLayout getRelativeLayout() {
        //container
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        relativeLayout.setLayoutParams(params);
        //image
        ImageView imageView = getImageView();
        imageViews.add(imageView);
        relativeLayout.addView(imageView);
        return relativeLayout;
    }

    private ImageView getImageView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(UIUtils.dp2px(getContext(), 80), UIUtils.dp2px(getContext(), 80));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    private void addLines() {
        firstLayout = new LinearLayout(getContext());
        LayoutParams firstParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(firstLayout, firstParams);
        secondLayout = new LinearLayout(getContext());
        LayoutParams secondParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        secondParams.topMargin = UIUtils.dp2px(getContext(), 4);
        addView(secondLayout, secondParams);
    }

}
