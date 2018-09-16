package com.test.smartschool.Page;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.smartschool.Adapter.Adapter_viewPager;
import com.test.smartschool.BaseActivity;
import com.test.smartschool.R;
import com.test.smartschool.Utils.Log;
import com.test.smartschool.Utils.NetClient;
import com.test.smartschool.Utils.PhotoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/yaosizy/article/details/70231359
//某些页面禁止滑动Toolbar
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Adapter_viewPager viewPagerAdapter;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private Toolbar toolbar;


    public static Uri tempUri;
    //弹出框选择图片来源
    //public xxxx xxxx;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View headView = navigationView.inflateHeaderView(R.layout.navigation_head);
        imageView = headView.findViewById(R.id.navigation_header_icon);

        imageView.setImageResource(R.mipmap.back);


    }


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.view_pager);
        List<String> titleList = new ArrayList<>();
        titleList.add("一卡通");
        titleList.add("主页");
        titleList.add("学习助手");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SmartCardFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new Fragment2());

        viewPagerAdapter = new Adapter_viewPager(getSupportFragmentManager(),titleList,fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        //设置首先展示中间的fragment
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        navigationView = findViewById(R.id.navigationView);


        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(navigationView);
            }
        });


    }

    @Override
    protected void initData() {
//        NetClient.getInstance().startRequest("https://www.baidu.com/",callback);
    }


    //navigationView Item的点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_item1:
                Toast.makeText(this,"点击了某事件",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(this,"点击了某事件",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item3:
                Toast.makeText(this,"点击了某事件",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item4:
                Toast.makeText(this,"点击了某事件",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item5:
                Toast.makeText(this,"点击了某事件",Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

}
