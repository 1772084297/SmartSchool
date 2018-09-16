package com.test.smartschool.Page;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.test.smartschool.Banner.GlideImageLoader;
import com.test.smartschool.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button testButton2;
    private Button testButton3;
    private Button testButton4;
    private Banner banner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        final List<String> images = new ArrayList<>();
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/109777.htm 标题 【山师学者】赵承福：研汇双序 益惠教改
        images.add("http://www.qlshx.sdnu.edu.cn/__local/E/40/71/8E680D476116476234AFEE386A3_47334B2E_C6FCF.jpg");
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/110116.htm 标题 学校举行2018级新生开学典礼暨军训动员大会
        images.add("http://www.qlshx.sdnu.edu.cn/__local/A/96/40/F60EB8E89A121641F94CC1ABE54_555961DE_CC60A.jpg");
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/110178.htm 标题【庆祝改革开放40周年】学校举行庆祝改革开放40周年暨2018年迎新生文艺演出
        images.add("http://www.qlshx.sdnu.edu.cn/__local/0/13/95/F7EB5DB60E0AC69BBD9B484036B_EF0001F3_D0FE3.jpg");
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/109777.htm 标题 【山师学者】赵承福：研汇双序 益惠教改
        images.add("http://www.qlshx.sdnu.edu.cn/__local/E/40/71/8E680D476116476234AFEE386A3_47334B2E_C6FCF.jpg");
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/110116.htm 标题 学校举行2018级新生开学典礼暨军训动员大会
        images.add("http://www.qlshx.sdnu.edu.cn/__local/A/96/40/F60EB8E89A121641F94CC1ABE54_555961DE_CC60A.jpg");
        //点击事件 http://www.qlshx.sdnu.edu.cn/info/10445/110178.htm 标题【庆祝改革开放40周年】学校举行庆祝改革开放40周年暨2018年迎新生文艺演出
        images.add("http://www.qlshx.sdnu.edu.cn/__local/0/13/95/F7EB5DB60E0AC69BBD9B484036B_EF0001F3_D0FE3.jpg");

        final List<String> titles = new ArrayList<>();
        titles.add("【山师学者】赵承福：研汇双序 益惠教改");
        titles.add("学校举行2018级新生开学典礼暨军训动员大会");
        titles.add("【庆祝改革开放40周年】学校举行庆祝改革开放40周年暨2018年迎新生文艺演出");
        titles.add("【山师学者】赵承福：研汇双序 益惠教改");
        titles.add("学校举行2018级新生开学典礼暨军训动员大会");
        titles.add("【庆祝改革开放40周年】学校举行庆祝改革开放40周年暨2018年迎新生文艺演出");
        initData();

        banner = view.findViewById(R.id.banner);
        //在线程中加载速度更快 好有道理我竟然无力反驳
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(images);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.setBannerTitles(titles);
                //设置轮播时间
                banner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getContext(),"select "+titles.get(position),Toast.LENGTH_SHORT).show();
                    }
                });
                banner.start();
            }
        });


        //跳转到数据分析
        testButton2 = view.findViewById(R.id.testbutton2);
        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DataAnalysisActivity.class);
                startActivity(intent);
            }
        });

        testButton3 = view.findViewById(R.id.testbutton3);
        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, StudyAnalysisActivity.class);
//                startActivity(intent);

//                NetClient.getInstance().startRequest("1");
            }
        });

        testButton4 = view.findViewById(R.id.testbutton4);
        testButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ClassActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initData(){


    }


    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

}
