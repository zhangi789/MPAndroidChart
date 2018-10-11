package com.smartchart.cn.mpandroidchart.utils;

import android.content.Context;
import android.util.Log;


import com.github.mikephil.charting.data.Entry;
import com.smartchart.cn.mpandroidchart.bean.YomthEnity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张海洋
 * @Date on 2018/09/13.
 * @org 上海相舆科技有限公司
 * @describe
 */


public class FileUtils {
    /**
     * 解析出字符串
     * @return
     */
    public static String getParseString(Context mContext) {
        String result = "";
        try {
            InputStream is = mContext.getAssets().open("test.json");//此处为要加载的json文件名称
            InputStreamReader reader = new InputStreamReader(is, "GB2312");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer("");
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                buffer.append("\n");
            }
            result = buffer.toString();//把读取的数据返回
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        return result;

    }



    /**
     * 解析出字符串
     * @return
     */
    public static String getParseString2(Context mContext) {
        String result = "";
        try {
            InputStream is = mContext.getAssets().open("test1.json");//此处为要加载的json文件名称
            InputStreamReader reader = new InputStreamReader(is, "GB2312");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer("");
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                buffer.append("\n");
            }
            result = buffer.toString();//把读取的数据返回
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        return result;

    }

    public static List<YomthEnity> getDataFromAsset2(Context mContext) {
        List<YomthEnity> mResult = new ArrayList<>();
        String data = getParseString2(mContext);

        Log.i("GGG","" +data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("rsp");

            Log.i("GGG","jsonArray size " +jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mjsonObject = jsonArray.getJSONObject(i);
                String month = mjsonObject.getString("month");//""内填写你要读取的数据
                String electricity = mjsonObject.getString("electricity");//""内填写你要读取的数据
                mResult.add(new YomthEnity(month, electricity));
            }
        } catch (Exception e) {
            Log.d("handleCitiesResponse", e.toString());
        }


        return mResult;
    }






    public static List<YomthEnity> getDataFromAsset(Context mContext) {
        List<YomthEnity> mResult = new ArrayList<>();
        String data = getParseString(mContext);

        Log.i("GGG","" +data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("rsp");

            Log.i("GGG","jsonArray size " +jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject mjsonObject = jsonArray.getJSONObject(i);
                String month = mjsonObject.getString("month");//""内填写你要读取的数据
                String electricity = mjsonObject.getString("electricity");//""内填写你要读取的数据
                mResult.add(new YomthEnity(month, electricity));
            }
        } catch (Exception e) {
            Log.d("handleCitiesResponse", e.toString());
        }


        return mResult;
    }

    public   static List<Entry> getDataEntry(List<YomthEnity> yomthEnities) {
        Log.i("GGG","getDataEntry size " +yomthEnities.size());
        List<Entry> values1 = new ArrayList<>();
        for (int i = 0; i < yomthEnities.size(); i++) {
            YomthEnity yomthEnity = yomthEnities.get(i);
            String electricity = yomthEnity.getElectricity();
            if (electricity != null) {
                float f = 0;
                try {
                    f = Float.parseFloat(electricity);
                } catch (Exception e) {
                    e.printStackTrace();
                    f = 0;
                }
                Entry entry = new Entry(i + 1, f);
                values1.add(entry);
            }
        }
        return values1;

    }


}
