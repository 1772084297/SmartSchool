package com.test.smartschool.Page;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.test.smartschool.Adapter.Adapter_consumeRecords;
import com.test.smartschool.Gson.ConsumeRecord;
import com.test.smartschool.R;
import com.test.smartschool.Utils.NetClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmartCardDetialFragment extends Fragment {

    private PullToRefreshLayout pullToRefreshLayout;
    private RecyclerView recyclerView;
    private Adapter_consumeRecords adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_card_detial, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        pullToRefreshLayout = view.findViewById(R.id.smartcard_pullToRefreshLayout);
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                //开始刷新，请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                    }
                },1200);
                //换位置，在数据请求成功或失败后在finish
                pullToRefreshLayout.finishRefresh();
            }

            @Override
            public void loadMore() {
                //开始加载，请求数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreData();
                    }
                },1200);
                pullToRefreshLayout.finishLoadMore();
            }
        });


        recyclerView = view.findViewById(R.id.smartcard_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter_consumeRecords(getContext());
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        List<ConsumeRecord> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            records.add(new ConsumeRecord("this is data"));
        }
        adapter.addData(records);
    }

    private void refreshData(){
//        String url = "";
//        NetClient.getInstance().startRequest(url,callBack);

    }

    private void loadMoreData(){
//        String url = "";
//        NetClient.getInstance().startRequest(url,callBack);
        Toast.makeText(getContext(),"假装在刷新数据",Toast.LENGTH_SHORT).show();
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
