package com.test.smartschool.Page;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.test.smartschool.Adapter.Adapter_consumeRecords;
import com.test.smartschool.Charts.Charts;
import com.test.smartschool.Gson.ConsumeRecord;
import com.test.smartschool.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SmartCardFragment extends Fragment {

    private TextView tv_name;
    private TextView tv_stuId;
    private TextView tv_cardId;
    private TextView tv_balance;
    private TextView tv_traBalance;
    private TextView tv_state;
    private PieChart pieChart;

    private RecyclerView recyclerView;
    private Adapter_consumeRecords adapter;

    private Button testButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smartcard, container, false);
        initView(view);
        requestData();
        return view;
    }

    private void initView(View view){
        tv_name = view.findViewById(R.id.smartcard_name);
        tv_stuId = view.findViewById(R.id.smartcard_stuId);
        tv_cardId = view.findViewById(R.id.smartcard_cardId);
        tv_balance = view.findViewById(R.id.smartcard_balance);
        tv_traBalance =view.findViewById(R.id.smartcard_traBalance);
        tv_state = view.findViewById(R.id.smartcard_state);
        pieChart = view.findViewById(R.id.smartcard_pieChart);

        recyclerView  = view.findViewById(R.id.smartcard_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter_consumeRecords(getContext());
        recyclerView.setAdapter(adapter);

        testButton = view.findViewById(R.id.button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SmartCardDetialActivity.class);
                startActivity(intent);
            }
        });

        initPieChart();
    }

    private void initPieChart() {
        Map<String, Float> mapData = new HashMap<>();
        mapData.put("大娃", 3f);
        mapData.put("二娃", 1f);
        mapData.put("三娃", 2f);
        mapData.put("四娃", 4f);
        mapData.put("五娃", 6f);
        mapData.put("六娃", 3f);
        mapData.put("七娃", 5f);


        Charts.setPieChart(pieChart, mapData, "", true);
    }

    private void requestData() {
//        String url = "";
//        NetClient.getInstance().startRequest("",callBack);
        //假设网络请求成功
        List<ConsumeRecord> newList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ConsumeRecord record = new ConsumeRecord("this is data");
            newList.add(record);
        }
        adapter.addData(newList);
        adapter.notifyDataSetChanged();
    }


}
