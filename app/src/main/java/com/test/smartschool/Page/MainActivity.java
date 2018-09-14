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


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Adapter_viewPager viewPagerAdapter;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private Toolbar toolbar;

    public static final int CHOOSE_PICTURE = 0;
    public static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

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

        //文件夹用来存储照片
//        File file = new File(Environment.getExternalStorageDirectory().getPath()+"AndroidPersonal_icon");
        File file = new File("/sdcard/androidPersonal_icon");
        if (!file.exists()){
            file.mkdirs();
            Log.e("文件不存在，已创建");
        }

        Log.e(file.getAbsolutePath());

        if (file.exists()&&file.isDirectory()){
            Log.e("file exists and file is directory");
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //讲道理应该在这里弹出窗口
                //先测试一下拍照
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tempUri = Uri.fromFile(
                        new File("/sdcard/androidPersonal_icon","image.jpg")
                );
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                startActivityForResult(openCameraIntent,TAKE_PICTURE);
            }
        });

        Log.e(" log"+ file.list()+"");

//        if (file.exists()&&file.isDirectory()){
//            if (file.list().length>0){
//                //file.list为文件的名字 表示存在文件时候
//                Bitmap bitmap = BitmapFactory.decodeFile(file.toString()+"/image_icon.png");
//                imageView.setImageBitmap(bitmap);
//            }else{
//                //设置默认的照片
////                imageView.setBackgroundResource(R.drawable.ic_launcher_background);
////                imageView.setImageResource(R.drawable.personal);
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK){
            switch (requestCode){
                case TAKE_PICTURE:
                    //开始裁剪
                    startPhotoZoom(tempUri);
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(tempUri);
                    break;
                case CROP_SMALL_PICTURE:
                    if (data!=null){
                        setImageToView(data);
                    }
                    break;
            }
        }
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


    //裁剪图片方法
    public void startPhotoZoom(Uri uri){
        if (uri == null){
            Log.e("the url not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //保存裁剪后的图片数据
    protected void setImageToView(Intent data) {
        Log.e("setImageToView");
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = PhotoUtils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            imageView.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = PhotoUtils.savePhoto(bitmap,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/AndroidPersonal_icon", "image_icon");
        Log.d(imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了
            // ...
        }
    }

}
