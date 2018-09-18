package com.test.smartschool.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.smartschool.Gson.ConsumeRecord;
import com.test.smartschool.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_consumeRecords extends RecyclerView.Adapter<Adapter_consumeRecords.ViewHolder> {

    private Context context;
    private List<ConsumeRecord> dataList;

    public Adapter_consumeRecords(Context context){
        dataList = new ArrayList<>();
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_consume_records,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsumeRecord record = dataList.get(position);
        if (position%2==0){
            holder.iv_icon.setImageResource(R.mipmap.icon_canteen);
        }else{
            holder.iv_icon.setImageResource(R.mipmap.icon_shopping);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<ConsumeRecord> dataList){
        this.dataList.addAll(dataList);
    }

    public void setData(List<ConsumeRecord> dataList){
        this.dataList = dataList;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_amount;
        TextView tv_place;
        TextView tv_time;
        CircleImageView iv_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_icon = itemView.findViewById(R.id.item_icon);
            tv_amount = itemView.findViewById(R.id.item_amount);
            tv_place = itemView.findViewById(R.id.item_place);
            tv_time = itemView.findViewById(R.id.item_time);

        }

    }
}
