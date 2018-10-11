package com.github.mikephil.charting.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张海洋
 * @Date on 2018/09/26.
 * @org 上海..科技有限公司
 * @describe
 */

public class PercentageEntity {
    private String overviewName;//区块名称
    private List<Record> recordList;//数据列表，按时间区间

    public static class Record implements Serializable {
        private String date;//时间区间
        private String exponentList;
        private String solidLineData;

        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }
        public String getExponentList() {
            return exponentList;
        }
        public void setExponentList(String exponentList) {
            this.exponentList = exponentList;
        }

        public String getSolidLineData() {
            return solidLineData;
        }
        public void setSolidLineData(String solidLineData) {
            this.solidLineData = solidLineData;
        }
    }


}
