package com.test.smartschool.Page;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.smartschool.Adapter.Adapter_classmate;
import com.test.smartschool.BaseActivity;
import com.test.smartschool.Gson.Classmate;
import com.test.smartschool.R;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private Adapter_classmate adapter;
    private List<Classmate> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_class;
    }

    @Override
    protected void initView() {

        adapter = new Adapter_classmate(this);
        recyclerView = findViewById(R.id.class_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new Classmate("某同学"));
        }
        adapter.addData(dataList);
    }
}
