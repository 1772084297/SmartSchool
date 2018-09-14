package com.test.smartschool.Charts;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.test.smartschool.R;
import com.test.smartschool.Utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//WilliamChat另一个比较好看的图表界面

//图表控件 参考http://www.android-doc.com/androiddocs/2017/0802/1157.html
//https://blog.csdn.net/dapangzao/article/details/74949541  详细介绍
//https://blog.csdn.net/wjk343977868/article/details/53316981 各种图的效果
//https://blog.csdn.net/wjk343977868/article/details/76610173 x轴设置为中文的改法

//调用时只需设置Charts.setLineChart(...)即可，注意传入数据的正确性


public class Charts {

    //设置折线图的各种参数以及数据
    public static void setLineChart(LineChart lineChart, List<String> xAxisValue, List<Float> yAxisValues, String title){

        lineChart.getDescription().setEnabled(false);


        //设置比例放缩折线图
        lineChart.setPinchZoom(true);

        //设置自定义的markView
        MPChartMarkerView markerView = new MPChartMarkerView(lineChart.getContext(), R.layout.custom_marker_view);
        lineChart.setMarker(markerView);

        //边框
        lineChart.setDrawBorders(false);



        //x坐标轴设置
        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setValueFormatter(xAxisFormatter);

        //y轴设置
        lineChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        //取消右侧Y轴
        lineChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);

        setLineChartData(lineChart,yAxisValues,title);

        lineChart.setExtraOffsets(10, 30, 20, 10);
        lineChart.animateX(1500);//数据显示动画，从左往右依次显示

    }

    private static void setLineChartData(LineChart lineChart, List<Float> yAxisValues, String title){
        List<Entry> entries = new ArrayList<>();
        for (int i=0;i<yAxisValues.size();i++){
            entries.add(new Entry(i,yAxisValues.get(i)));
        }

        LineDataSet set;
        if (lineChart.getData()!=null&&lineChart.getData().getDataSetCount()>0){
            set = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        }else{
            set = new LineDataSet(entries,title);
            set.setColor(ContextCompat.getColor(lineChart.getContext(), R.color.red));

            List<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set);

            LineData data = new LineData(dataSets);
            data.setValueTextSize(10f);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return StringUtils.double2String(value,2);
                }
            });

            lineChart.setScaleXEnabled(false);
            lineChart.setScaleYEnabled(false);

            lineChart.setData(data);
        }
    }


    //设置柱状图的各种参数及数据
    public static void setBarChart(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue, String title, float xAxixTextSize) {
        //设置描述
        barChart.getDescription().setEnabled(false);
        //按比例缩放
        barChart.setPinchZoom(true);

        //设置markerView
        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(), R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        //x坐标轴设置
        //获取X轴
        XAxis xAxis = barChart.getXAxis();
        //x轴标签显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //不绘制网格 网格太难看了
        xAxis.setDrawAxisLine(false);
        //设置最小间隔，防止当放大时出现重复标签
        xAxis.setGranularity(1f);
        //x轴坐标设置  设置自定义的x轴值格式化器
        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
        xAxis.setValueFormatter(xAxisFormatter);
        //标签字体大小
        xAxis.setTextSize(5f);
        //标签显示的个数
        xAxis.setLabelCount(xAxisValue.size());

        //y坐标轴设置
        //获取左侧坐标轴并不显示右侧坐标轴
        YAxis yAxis = barChart.getAxisLeft();
        barChart.getAxisRight().setEnabled(false);
        //设置y轴标签显示在外侧
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置Y轴的最小值
        yAxis.setAxisMinimum(0f);
        //禁止Y轴绘制标签
        yAxis.setDrawLabels(false);


        //图例设置
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //绘制在外侧  如果绘制在内侧的话会遮住文字
        legend.setDrawInside(false);
        //图例中的文字方向
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //图例窗体的形状
        legend.setForm(Legend.LegendForm.SQUARE);
        //图例窗体的大小
        legend.setFormSize(14f);
        //图例文字大小
        legend.setTextSize(14f);

        //设置柱状图数据
        setBarChartData(barChart, yAxisValue, title);

        //padding
        barChart.setExtraOffsets(0, 30, 0, 10);
        //使两侧的柱体完全显示
        barChart.setFitBars(true);
        //显示动画，从左往右依此显示
        barChart.animateX(1500);
        //除去缩放功能
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);

    }

    private static void setBarChartData(BarChart barChart, List<Float> yAxisValue, String title) {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < yAxisValue.size(); i++) {
            entries.add(new BarEntry(i, yAxisValue.get(i)));
        }

        BarDataSet set;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(entries, title);
            set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.red));

            List<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new DefaultValueFormatter(0));

            barChart.setData(data);
        }
    }


    //设置三组柱状图的各种参数及数据
    public static void setThreeBarChart(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2,
                                  List<Float> yAxisValue3, String title1, String title2, String title3) {
        //设置描述
        barChart.getDescription().setEnabled(false);
        //不按比例缩放柱状图
        barChart.setPinchZoom(false);
        barChart.setExtraBottomOffset(10);
        barChart.setExtraTopOffset(30);


        //设置点击柱状图后产生的标签
        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(), R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));


        //y坐标轴设置
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        barChart.getAxisRight().setEnabled(false);

        Float yMin1 = Collections.min(yAxisValue1);
        Float yMin2 = Collections.min(yAxisValue2);
        Float yMin3 = Collections.min(yAxisValue3);
        Float yMax1 = Collections.max(yAxisValue1);
        Float yMax2 = Collections.max(yAxisValue2);
        Float yMax3 = Collections.max(yAxisValue3);
        Float yMinTemp = yMin1 < yMin2 ? yMin1 : yMin2;
        Float yMin = yMinTemp < yMin3 ? yMinTemp : yMin3;
        Float yMaxTemp = yMax1 > yMax2 ? yMax1 : yMax2;
        Float yMax = yMaxTemp > yMax3 ? yMaxTemp : yMax3;
        leftAxis.setAxisMinimum(Double.valueOf(yMin * 0.9).floatValue());
        leftAxis.setAxisMaximum(Double.valueOf(yMax * 1.1).floatValue());

        //设置柱状图数据
        setThreeBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, yAxisValue3, title1, title2, title3);

        barChart.animateX(1500);//数据显示动画，从左往右依次显示
        barChart.invalidate();

    }

    private static void setThreeBarChartData(BarChart barChart, List<String> xAxisValue, List<Float> yAxisValue1, List<Float> yAxisValue2,
                                             List<Float> yAxisValue3, String title1, String title2, String title3) {
        float groupSpace = 0.04f;
        float barSpace = 0.02f;
        float barWidth = 0.3f;
        // (0.3 + 0.02) * 3 + 0.04 = 1，即一个间隔为一组，包含三个柱图

        ArrayList<BarEntry> first_entries = new ArrayList<>();
        ArrayList<BarEntry> second_entries = new ArrayList<>();
        ArrayList<BarEntry> third_entries = new ArrayList<>();

        for (int i = 0, n = xAxisValue.size(); i < n; ++i) {

            first_entries.add(new BarEntry(i, yAxisValue1.get(i)));
            second_entries.add(new BarEntry(i, yAxisValue2.get(i)));
            third_entries.add(new BarEntry(i, yAxisValue3.get(i)));
        }

        BarDataSet first_set, second_set, third_set;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            first_set = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            second_set = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            third_set = (BarDataSet) barChart.getData().getDataSetByIndex(2);
            first_set.setValues(first_entries);
            second_set.setValues(second_entries);
            third_set.setValues(third_entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            first_set = new BarDataSet(first_entries, title1);
            second_set = new BarDataSet(second_entries, title2);
            third_set = new BarDataSet(third_entries, title3);

            first_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.bar1));
            second_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.bar2));
            third_set.setColor(ContextCompat.getColor(barChart.getContext(), R.color.bar3));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(first_set);
            dataSets.add(second_set);
            dataSets.add(third_set);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    return StringUtils.double2String(value, 2);
                }
            });

            barChart.setData(data);
        }

        barChart.getBarData().setBarWidth(barWidth);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * xAxisValue.size() + 0);
        barChart.groupBars(0, groupSpace, barSpace);

        //禁用拖拽放大事件
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
    }


    //设置饼图的各种参数及数据
    public static void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
        //设置使用百分比
        pieChart.setUsePercentValues(true);
        //描述
        pieChart.getDescription().setEnabled(false);
        //边距
        pieChart.setExtraOffsets(20, 15, 20, 15);
        //环中文字
        pieChart.setCenterText(title);
        //环中文字大小
        pieChart.setCenterTextSize(10f);
        //设置绘制环中文字
        pieChart.setDrawCenterText(true);
        //设置旋转角度 没用啊
        pieChart.setRotation(0f);

        //图例设置
        Legend legend = pieChart.getLegend();
        if (showLegend) {
            legend.setEnabled(true);
            //设置图例的位置
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            //设置子图例的方向
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setDrawInside(false);
            //图例的顺序
            legend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        } else {
            legend.setEnabled(false);
        }

        //设置饼图数据
        setPieChartData(pieChart, pieValues);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);

    }

    private static void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
        List<PieEntry> entries = new ArrayList<>();

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        //饼块间的距离
        dataSet.setSliceSpace(3f);
        //饼块选中时偏离饼图中心的距离
        dataSet.setSelectionShift(5f);
        //颜色
        dataSet.setColors(Color.RED, Color.BLUE, Color.GREEN, Color.CYAN);

        //数据连接线距图片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(90f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        //连接线的颜色
        dataSet.setValueLineColor(Color.BLACK);

        //设置百分数在饼图内还是饼图外 在外的话会有连接线比较丑-。-
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.highlightValue(null);
        //刷新数据
        pieChart.invalidate();

    }


    //用于对字符串型的坐标轴进行格式化
    private static class StringAxisValueFormatter implements IAxisValueFormatter {

        //区域值
        private List<String> mStrs;

        /**
         * 对字符串类型的坐标轴标记进行格式化
         *
         * @param strs
         */
        public StringAxisValueFormatter(List<String> strs) {
            this.mStrs = strs;
        }

        @Override
        public String getFormattedValue(float v, AxisBase axisBase) {
            return mStrs.get((int) v);
        }
    }

}
