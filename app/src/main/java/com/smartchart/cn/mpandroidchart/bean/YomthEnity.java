package com.smartchart.cn.mpandroidchart.bean;

/**
 * @author 张海洋
 * @Date on 2018/09/13.
 * @org 上海相舆科技有限公司
 * @describe
 */


public class YomthEnity {
    private String month;
    private String electricity;

    public YomthEnity(String month, String electricity) {
        this.month = month;
        this.electricity = electricity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }
}
