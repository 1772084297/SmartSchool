package com.test.smartschool.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.test.smartschool.Adapter.Adapter_consumeRecords;
import com.test.smartschool.BaseActivity;
import com.test.smartschool.Charts.Charts;
import com.test.smartschool.Gson.ConsumeRecord;
import com.test.smartschool.R;
import com.test.smartschool.Utils.NetClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartCardActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_smartcard;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    // TODO: 2018/9/6 charts颜色有点难看 注意一下颜色的调整




    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result){

        }

        @Override
        public void onFailure(int code) {

        }
    };



}
