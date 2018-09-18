package com.test.smartschool.Page;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.test.smartschool.Adapter.Adapter_consumeRecords;
import com.test.smartschool.BaseActivity;
import com.test.smartschool.Gson.ConsumeRecord;
import com.test.smartschool.R;
import com.test.smartschool.Utils.NetClient;

import java.util.ArrayList;
import java.util.List;


//https://github.com/823546371/PullToRefresh 下拉刷新上拉加载控件
public class SmartCardDetialActivity extends BaseActivity {

    private PullToRefreshLayout pullToRefreshLayout;
    private RecyclerView recyclerView;
    private Adapter_consumeRecords adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_smartcard_detial;
    }

    @Override
    protected void initView() {
        pullToRefreshLayout = findViewById(R.id.smartcard_pullToRefreshLayout);
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                //开始刷新，请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                    }
                }, 1200);
                //换位置，在数据请求成功或失败后在finish
            }

            @Override
            public void loadMore() {
                //开始加载，请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreData();
                    }
                }, 1200);

            }
        });


        recyclerView = findViewById(R.id.smartcard_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter_consumeRecords(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<ConsumeRecord> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            records.add(new ConsumeRecord("this is data"));
        }
        adapter.addData(records);
    }

    private void refreshData() {
//        String url = "";
//        NetClient.getInstance().startRequest(url,callBack);
        Toast.makeText(this, "假装在刷新数据", Toast.LENGTH_SHORT).show();
        pullToRefreshLayout.finishRefresh();

    }

    private void loadMoreData() {
//        String url = "";
//        NetClient.getInstance().startRequest(url,callBack);
        Toast.makeText(this, "假装在加载数据", Toast.LENGTH_SHORT).show();
        pullToRefreshLayout.finishLoadMore();
    }

    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {
            //回调成功
        }

        @Override
        public void onFailure(int code) {
            //回调失败
        }
    };


}
