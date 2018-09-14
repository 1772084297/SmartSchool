package com.test.smartschool.Page;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.test.smartschool.BaseActivity;
import com.test.smartschool.Charts.Charts;
import com.test.smartschool.R;
import com.test.smartschool.Utils.Log;
import com.test.smartschool.Utils.NetClient;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DataAnalysisActivity extends BaseActivity {

    //性别分析

    private NumberProgressBar bar_boy;
    private NumberProgressBar bar_girl;

    private NiceSpinner spinner_grade;
    private NiceSpinner spinner_college;

    private PieChart pieChart;

    List<String> gradeList;
    List<String> collegeList;

    List<String> gradeList2;
    List<String> collegeList2;
    List<String> majorList;

    //专业分析
    private NiceSpinner spinner_grade2;
    private NiceSpinner spinner_college2;
    private NiceSpinner spinner_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        //性别比例
        bar_boy = findViewById(R.id.dataanalysis_boyBar);

        bar_girl = findViewById(R.id.dataanalysis_girlBar);


        spinner_grade = findViewById(R.id.dataanalysis_spinner_grade);
        spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e(gradeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_college = findViewById(R.id.dataanalysis_spinner_college);
        spinner_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e(collegeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //专业人数
        spinner_grade2 = findViewById(R.id.dataanalysis_spinner_grade2);
        spinner_grade2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e(gradeList2.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_college2 = findViewById(R.id.dataanalysis_spinner_college2);
        spinner_college2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e(collegeList2.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_major = findViewById(R.id.dataanalysis_spinner_major);
        spinner_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e(majorList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        pieChart = findViewById(R.id.dataanalysis_pieChart);



    }

    @Override
    protected void initData() {
        bar_boy.setPrefix("男生");
        bar_boy.setProgress(34);

        bar_girl.setPrefix("女生");
        bar_girl.setProgress(56);

        gradeList = new ArrayList<>();
        gradeList.addAll(Arrays.asList(getResources().getStringArray(R.array.grade)));
        spinner_grade.attachDataSource(gradeList);

        collegeList = new ArrayList<>();
        collegeList.addAll(Arrays.asList(getResources().getStringArray(R.array.college)));
        spinner_college.attachDataSource(collegeList);


        collegeList2 = new ArrayList<>();
        collegeList2.addAll(Arrays.asList(getResources().getStringArray(R.array.college)));
        spinner_college2.attachDataSource(collegeList2);

        gradeList2 = new ArrayList<>();
        gradeList2.addAll(Arrays.asList(getResources().getStringArray(R.array.grade)));
        spinner_grade2.attachDataSource(gradeList2);

        majorList = new ArrayList<>();
        majorList.addAll(Arrays.asList(getResources().getStringArray(R.array.major)));
        spinner_major.attachDataSource(majorList);

        Map<String,Float> pieData = new Hashtable<>();
        pieData.put("2015级",15f);
        pieData.put("2016级",12f);
        pieData.put("2017级",18f);
        pieData.put("2018级",15f);
        Charts.setPieChart(pieChart,pieData,"",true);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_data_analysis;
    }


    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {

        }

        @Override
        public void onFailure(int code) {

        }
    };


}
