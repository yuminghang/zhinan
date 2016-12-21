package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/12/21.
 */
public class posBean {

    /**
     * success : true
     * msg : 成功
     * data : [{"_id":"5859fc5ac450d8692c60efed","ad_order":"1232132131221312","lat":"34.240721","lon":"108.92594","time":{"date":"2016-12-21T03:51:54.001Z","year":2016,"month":"2016-12","day":"2016-12-21","minute":"2016-12-21 11:51"}},{"_id":"5859fc36c450d8692c60efeb","ad_order":"1232132131221321","lat":"34.240740","lon":"108.925951","time":{"date":"2016-12-21T03:51:36.778Z","year":2016,"month":"2016-12","day":"2016-12-21","minute":"2016-12-21 11:51"}},{"_id":"5859fc3dc450d8692c60efec","ad_order":"1232132131221323","lat":"34.240740","lon":"108.925953","time":{"date":"2016-12-21T03:51:25.178Z","year":2016,"month":"2016-12","day":"2016-12-21","minute":"2016-12-21 11:51"}}]
     */

    private boolean success;
    private String msg;
    /**
     * _id : 5859fc5ac450d8692c60efed
     * ad_order : 1232132131221312
     * lat : 34.240721
     * lon : 108.92594
     * time : {"date":"2016-12-21T03:51:54.001Z","year":2016,"month":"2016-12","day":"2016-12-21","minute":"2016-12-21 11:51"}
     */

    private List<DataEntity> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String _id;
        private String ad_order;
        private double lat;
        private double lon;
        /**
         * date : 2016-12-21T03:51:54.001Z
         * year : 2016
         * month : 2016-12
         * day : 2016-12-21
         * minute : 2016-12-21 11:51
         */

        private TimeEntity time;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setAd_order(String ad_order) {
            this.ad_order = ad_order;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public void setTime(TimeEntity time) {
            this.time = time;
        }

        public String get_id() {
            return _id;
        }

        public String getAd_order() {
            return ad_order;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public TimeEntity getTime() {
            return time;
        }

        public static class TimeEntity {
            private String date;
            private int year;
            private String month;
            private String day;
            private String minute;

            public void setDate(String date) {
                this.date = date;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public void setMinute(String minute) {
                this.minute = minute;
            }

            public String getDate() {
                return date;
            }

            public int getYear() {
                return year;
            }

            public String getMonth() {
                return month;
            }

            public String getDay() {
                return day;
            }

            public String getMinute() {
                return minute;
            }
        }
    }
}
