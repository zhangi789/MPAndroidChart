package com.smartchart.cn.mpandroidchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.smartchart.cn.mpandroidchart.utils.BaseUtils;
import com.smartchart.cn.mpandroidchart.view.NewMarkerView;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 张海洋
 * @Date on 2018/09/27.
 * @org 上海..科技有限公司
 * @describe
 */


public class NomalChartActivity extends AppCompatActivity {


    @Bind(R.id.chart1)
    LineChart mLineChart;
    private ArrayList<Entry> mValues;
    private XAxis xAxis;


    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴

    LineDataSet lineDataSet;
    LineData lineData;
    DatagramSocket datagramSocket=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ButterKnife.bind(this);


        datagramSocket = BaseUtils.initUdpSocket(8266);
        setmChart();
        setLineChartDate();


    }

    private void setViewss() {


        mLineChart.setLogEnabled(true);//打印日志
        //取消描述文字
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setNoDataText("没有数据");//没有数据时显示的文字
        mLineChart.setNoDataTextColor(Color.WHITE);//没有数据时显示文字的颜色
        mLineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        mLineChart.setDrawBorders(false);//是否禁止绘制图表边框的线
        mLineChart.setBorderColor(Color.WHITE); //设置 chart 边框线的颜色。
        mLineChart.setBorderWidth(3f); //设置 chart 边界线的宽度，单位 dp。
        mLineChart.setTouchEnabled(true);     //能否点击
        mLineChart.setDragEnabled(false);   //能否拖拽
        mLineChart.setScaleEnabled(false);  //能否缩放
        mLineChart.animateX(1000);//绘制动画 从左到右
        mLineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
        mLineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        mLineChart.setDragDecelerationEnabled(false);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）

      /*  MyMarkerView mv = new MyMarkerView(mActivity,
                R.layout.custom_marker_view);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv);  */      //设置 marker ,点击后显示的功能 ，布局可以自定义

        XAxis xAxis = mLineChart.getXAxis();       //获取x轴线
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(12f);//设置文字大小
        xAxis.setAxisMinimum(0f);//设置x轴的最小值 //`
        xAxis.setAxisMaximum(31f);//设置最大值 //
        xAxis.setLabelCount(10);  //设置X轴的显示个数
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setAxisLineColor(Color.WHITE);//设置x轴线颜色
        xAxis.setAxisLineWidth(0.5f);//设置x轴线宽度
        YAxis leftAxis = mLineChart.getAxisLeft();
        YAxis axisRight = mLineChart.getAxisRight();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
        leftAxis.setGranularity(20f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#7189a9"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setStartAtZero(true);        //设置Y轴数值 从零开始
        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setTextSize(12f);            //设置Y轴刻度字体
        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(0.5f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setMinWidth(0f);
        leftAxis.setMaxWidth(200f);
        leftAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        Legend l = mLineChart.getLegend();//图例
        l.setEnabled(false);   //是否使用 图例

    }


    private void setLineChartDate() {
        mValues = new ArrayList<>();
        mValues.add(new Entry(0, 10, "2018-1"));
        mValues.add(new Entry(1, 15, "2018-2"));
        mValues.add(new Entry(2, 25, "2018-3"));
        mValues.add(new Entry(3, 19, "2018-4"));
        mValues.add(new Entry(4, 25, "2018-5"));
        mValues.add(new Entry(5, 16,"2018-6"));
        mValues.add(new Entry(6, 40,"2018-7"));
        mValues.add(new Entry(7, 27,"2018-8"));
        //点构成的某条线
        lineDataSet = new LineDataSet(mValues, "");
        //设置该线的颜色
        lineDataSet.setColor(Color.WHITE);
        //设置每个点的颜色
        lineDataSet.setCircleColor(Color.WHITE);
        //设置该线的宽度
        lineDataSet.setLineWidth(1f);
        //设置每个坐标点的圆大小
        lineDataSet.setCircleRadius(2f);
        //设置是否画圆
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        // 设置平滑曲线模式
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置线一面部分是否填充颜色
        lineDataSet.setDrawFilled(true);
        //设置填充的颜色
        lineDataSet.setFillColor(Color.WHITE);
        lineDataSet.setFillAlpha(77);
        //设置高亮    setDrawValues  才能生效
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(false);
        //设置是否显示点的坐标值
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.WHITE);
        //线的集合（可单条或多条线）
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        //把要画的所有线(线的集合)添加到LineData里
        lineData = new LineData(dataSets);
        //切换取消高亮
        mLineChart.highlightValue(null);
        //把最终的数据setData
        mLineChart.setData(lineData);
        mLineChart.invalidate();

    }


    /**
     *
     */
    private void setmChart() {
        leftAxis = mLineChart.getAxisLeft();
        rightAxis = mLineChart.getAxisRight();
        xAxis = mLineChart.getXAxis();
        //网格背景 控制
        mLineChart.setDrawGridBackground(false);
        //禁止X y 伸缩
        mLineChart.setScaleEnabled(false);
        //禁止  拖拽
        mLineChart.setDragEnabled(false);
        //禁止点击放大
        mLineChart.setDoubleTapToZoomEnabled(false);
        Description description = new Description();
        description.setText("");
        description.setTextColor(Color.WHITE);
        //设置描述信息
        mLineChart.setDescription(description);
        //设置没有数据时显示的文本
        mLineChart.setNoDataText("没有数据喔~~");
        //显示边界
        mLineChart.setDrawBorders(false);

        mLineChart.setBorderColor(Color.RED);
        //设置动画效果  x  Y  动画效果
        mLineChart.animateY(1000, Easing.EasingOption.Linear);
        mLineChart.animateX(1000, Easing.EasingOption.Linear);

        //折线图例 标签 设置
        Legend legend = mLineChart.getLegend();
        //设置图例的形状 有圆形、正方形、线  图例是视图下方的东西
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(0f);
        legend.setTextSize(0f);
        //显示位置    控制图例的位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置图例的形状 有圆形、正方形、线  图例是视图下方的东西
        //是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        legend.setDrawInside(false);
        //XY轴的设置
        //X轴设置显示位置在底部


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //leng
        //  xAxis.setAxisMinimum(0f);
        //设置X轴  线显示还是隐藏   setDrawGridLines  设置网格竖直线去掉
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGridLineWidth(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                Entry entry = mValues.get((int) value);
                String data = (String) entry.getData();
                return data;
            }
        });


        // (value, axis) -> mValues.get((int)      value).getData()+"");



      /*  xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mData.get(String.valueOf((int) value));
            }
        });
*/
        YAxis leftAxis = mLineChart.getAxisLeft();
        YAxis axisRight = mLineChart.getAxisRight();
        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
        leftAxis.setGranularity(20f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#F03967"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setStartAtZero(true);        //设置Y轴数值 从零开始
        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setTextSize(12f);            //设置Y轴刻度字体
        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(0.5f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setMinWidth(0f);
        leftAxis.setMaxWidth(200f);
        leftAxis.setDrawGridLines(false);//设置x轴上每个点对应的线

        //保证Y轴从0开始，不然会上移一点
      /*  leftAxis.setAxisMinimum(0f);
        leftAxis.setEnabled(false);
        leftAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);
        rightAxis.setAxisMinimum(0f);*/

        final NewMarkerView markerView = new NewMarkerView(NomalChartActivity.this, R.layout.custom_marker_view_layout);
        markerView.setCallBack(new NewMarkerView.CallBack() {
            @Override
            public void onCallBack(float x, String value) {

                markerView.getTvContent().setText((String) mValues.get((int) x).getData());
            }
        });
        markerView.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(markerView); // Set the marker to the chart
        mLineChart.highlightValue(null); // Set the marker to the chart
    }


}
