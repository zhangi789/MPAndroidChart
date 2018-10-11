package com.smartchart.cn.mpandroidchart.view;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.NomalMarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.smartchart.cn.mpandroidchart.R;

import java.text.DecimalFormat;

/**
 * @author 张海洋
 * @Date on 2018/09/27.
 * @org 上海..科技有限公司
 * @describe
 */


public class MyMarkerView  extends NomalMarkerView {

    private TextView tvContent;

    public MyMarkerView(Context context, int layoutResource) {

        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        DecimalFormat df = new DecimalFormat("##0.000");
        if (e instanceof CandleEntry) {
            Log.i("NNN", e.getY() + "-------11--------");
            Log.i("NNN", e.getX() + "-------22--------");
            CandleEntry ce = (CandleEntry) e;
            Log.i("NNN", "CandleEntry" + ce.getHigh() + "---------------");
            // tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
            tvContent.setText(df.format(ce.getHigh()));
        } else {
            Log.i("NNN", e.getX() + "------33---------");
            Log.i("NNN", e.getY() + "--------44-------");
            tvContent.setText(df.format(e.getY()));
            // tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
