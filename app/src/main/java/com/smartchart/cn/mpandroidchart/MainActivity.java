package com.smartchart.cn.mpandroidchart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.newchart.LineChartEntity;
import com.github.mikephil.charting.utils.StringUtils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.github.mikephil.charting.view.LineChartInViewPager;
import com.smartchart.cn.mpandroidchart.bean.YomthEnity;
import com.smartchart.cn.mpandroidchart.utils.FileUtils;
import com.smartchart.cn.mpandroidchart.view.NewMarkerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.trend_lineChart)
    LineChartInViewPager LineChart;
    @Bind(R.id.btn_update)
    Button btnUpdate;
    @Bind(R.id.btn_jump)
    Button btnJump;
    private Context mContext;
    private DecimalFormat mFormat;
    LineChartEntity lineChartEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mFormat = new DecimalFormat("#,###.##");
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        List<YomthEnity> yoyList = FileUtils.getDataFromAsset(mContext);
        List<Entry> values1 = FileUtils.getDataEntry(yoyList);
        List<Entry>[] entries = new ArrayList[1];
        entries[0] = values1;
        Drawable[] drawables = {
                ContextCompat.getDrawable(mContext, R.drawable.chart_callserice_call_casecount)};
        int[] callDurationColors = {Color.parseColor("#5fd1cc")};
        String[] labels = new String[]{"2018"};
        updateLinehart(yoyList, LineChart, callDurationColors, drawables, "", entries, labels);
    }

    //
    private void updateLinehart(final List<YomthEnity> yoyList, LineChart lineChart, int[] colors, Drawable[] drawables,
                                final String unit, List<Entry>[] entries, final String[] labels) {
        lineChartEntity = new LineChartEntity(lineChart, entries, labels, colors, Color.parseColor("#999999"), 12f);
        lineChartEntity.drawHoleCircle(true);
        lineChart.setScaleMinima(1.0f, 1.0f);
        lineChart.getViewPortHandler().refresh(new Matrix(), lineChart, true);
        toggleFilled(lineChartEntity, drawables, colors);
        lineChartEntity.setLineMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineChartEntity.initLegend(Legend.LegendForm.CIRCLE, 12f, Color.parseColor("#999999"));
        lineChartEntity.updateLegendOrientation(Legend.LegendVerticalAlignment.TOP, Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL);
        lineChartEntity.setAxisFormatter(
                new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {


                        Log.i("UUU", "value " + value);
                        if (value == 1.0f) {
                            return mFormat.format(value) + "月";
                        }
                        String monthStr = mFormat.format(value);
                        if (monthStr.contains(".")) {
                            return "";
                        } else {
                            return monthStr;
                        }
//                        return mMonthFormat.format(value);
                    }
                },
                new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return mFormat.format(value) + unit;
                    }
                });

        lineChartEntity.setDataValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mFormat.format(value) + unit;
            }
        });

        final NewMarkerView markerView = new NewMarkerView(MainActivity.this, R.layout.custom_marker_view_layout);
        markerView.setCallBack(new NewMarkerView.CallBack() {
            @Override
            public void onCallBack(float x, String value) {
                int index = (int) (x);
                if (index < 0) {
                    return;
                }
                if (index > yoyList.size()) {
                    return;
                }
                String textTemp = "";

                if (index <= yoyList.size()) {
                    if (!StringUtils.isEmpty(textTemp)) {

                    }
                    textTemp += yoyList.get(index - 1).getMonth() + "-" + index + "  " + mFormat.format(Float.parseFloat(yoyList.get(index - 1).getElectricity())) + unit;
                }

                markerView.getTvContent().setText(textTemp);
            }
        });
        lineChartEntity.setMarkView(markerView);
        lineChart.getData().setDrawValues(false);

        lineChart.setVisibleXRangeMaximum(8);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();

    }

    /**
     * 双平滑曲线添加线下的阴影
     *
     * @param lineChartEntity
     * @param drawables
     * @param colors
     */
    private void toggleFilled(LineChartEntity lineChartEntity, Drawable[] drawables, int[] colors) {
        if (Build.VERSION.SDK_INT >= 18) {

            lineChartEntity.toggleFilled(drawables, null, true);
        } else {
            lineChartEntity.toggleFilled(null, colors, true);
        }
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        LineChart.clear();
        List<YomthEnity> yoyList = FileUtils.getDataFromAsset2(mContext);
        List<Entry> values1 = FileUtils.getDataEntry(yoyList);
        List<Entry>[] entries = new ArrayList[1];
        entries[0] = values1;
        Drawable[] drawables = {
                ContextCompat.getDrawable(mContext, R.drawable.chart_callserice_call_casecount)};
        int[] callDurationColors = {Color.parseColor("#5fd1cc")};
        String[] labels = new String[]{"2018"};
        updateLinehart(yoyList, LineChart, callDurationColors, drawables, "", entries, labels);

        //  lineChartEntity.upDateDaTe(entries);
    }

    @OnClick({R.id.btn_update, R.id.btn_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                LineChart.clear();
                List<YomthEnity> yoyList = FileUtils.getDataFromAsset2(mContext);
                List<Entry> values1 = FileUtils.getDataEntry(yoyList);
                List<Entry>[] entries = new ArrayList[1];
                entries[0] = values1;
                Drawable[] drawables = {
                        ContextCompat.getDrawable(mContext, R.drawable.chart_callserice_call_casecount)};
                int[] callDurationColors = {Color.parseColor("#5fd1cc")};
                String[] labels = new String[]{"2018"};
                updateLinehart(yoyList, LineChart, callDurationColors, drawables, "", entries, labels);
                break;
            case R.id.btn_jump:

                startActivity(new Intent(MainActivity.this,NomalChartActivity.class));
                break;
        }
    }
}
