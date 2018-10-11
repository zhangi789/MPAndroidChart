package com.github.mikephil.charting.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 张海洋
 * @Date on 2018/09/26.
 * @org 上海..科技有限公司
 * @describe
 */


public class ParseUtils {
    public void getData(String data) {
        try {
            JSONObject  jsonObject=new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
