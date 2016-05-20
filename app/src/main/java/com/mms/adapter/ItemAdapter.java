package com.mms.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mms.R;
import com.mms.activity.ActivityCarrierInfo;
import com.mms.activity.ActivityItemInfo;
import com.mms.widget.rlrView.adapter.RecyclerViewAdapter;
import com.mms.widget.rlrView.view.LoadMoreRecyclerView;

import java.util.Date;
import java.util.Map;

/**
 * Created by zlc on 2016/4/4.
 */
public class ItemAdapter extends RecyclerViewAdapter<Map<String, String>> implements LoadMoreRecyclerView.OnItemClickListener {

    public ItemAdapter(Context context) {
        super(context);
    }

    @Override
    public void onHolderBind(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            Map<String, String> map = getDataItem(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
//            viewHolder.tvTitle.setText("" + flight.getLowPrice());
//            viewHolder.tvInfo.setText("" + flight.getLowPrice());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(layoutInflater.inflate(R.layout.rlr_list_item_item, parent, false));
    }


    @Override
    public void onItemClick(int position) {

        //跳转到详情页
        Intent intent = new Intent(context, ActivityItemInfo.class);
        intent.putExtra("type", "tudi");
        context.startActivity(intent);

    }

    public String getDateToTime(Date date) {
        //根据Date对象拿到形如09:20的时间点
        String s = (date.getHours() < 10 ? "0" + date.getHours() : "" + date.getHours()) + ":"
                + (date.getMinutes() < 10 ? "0" + date.getMinutes() : "" + date.getMinutes());
        return s;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvInfo;
        TextView tvTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.rlr_list_item_item_title);
            tvInfo = (TextView) itemView.findViewById(R.id.rlr_list_item_item_info);
            tvTime = (TextView) itemView.findViewById(R.id.rlr_list_item_item_time);
        }
    }

}
