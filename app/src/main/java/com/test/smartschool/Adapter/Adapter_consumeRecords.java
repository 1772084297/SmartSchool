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

import java.util.ArrayList;
import java.util.List;

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
        holder.textView.setText(record.getData());
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

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }

    }
}
