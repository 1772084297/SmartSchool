package com.test.smartschool.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.smartschool.Gson.Classmate;
import com.test.smartschool.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_classmate extends RecyclerView.Adapter<Adapter_classmate.ViewHolder> {

    private Context context;

    private List<Classmate> datalist;

    public Adapter_classmate(Context context){
        this.context = context;
        datalist = new ArrayList<>();
    }

    public void addData(List<Classmate> datalist){
        this.datalist.addAll(datalist);
    }

    public void setDatalist(List<Classmate> datalist){
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classmate,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Classmate classmate = datalist.get(position);
        viewHolder.textView.setText(classmate.getName());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
