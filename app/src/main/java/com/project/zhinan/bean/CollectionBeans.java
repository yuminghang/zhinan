package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by deng on 17-2-24.
 */

public class CollectionBeans {

    /**
     * success : 成功
     * data : [{"_id":"58af8b781241022e2fb5dc2a","userid":"49be2350-f001-11e6-8f12-7b1152cef69f","uuid":"166c0150-fa30-11e6-9942-57dac5d6a3b4","adid":"12321312","order_id":"asdasdas","title":"asdads","imgurl":"asdasd","time":{"date":"2017-02-24T01:25:12.293Z","year":2017,"month":"2017-2","day":"2017-2-24","minute":"2017-2-24 9:25"}}]
     */

    private String success;
    private List<DataBean> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 58af8b781241022e2fb5dc2a
         * userid : 49be2350-f001-11e6-8f12-7b1152cef69f
         * uuid : 166c0150-fa30-11e6-9942-57dac5d6a3b4
         * adid : 12321312
         * order_id : asdasdas
         * title : asdads
         * imgurl : asdasd
         * time : {"date":"2017-02-24T01:25:12.293Z","year":2017,"month":"2017-2","day":"2017-2-24","minute":"2017-2-24 9:25"}
         */

        private String _id;
        private String userid;
        private String uuid;
        private String adid;
        private String order_id;
        private String title;
        private String imgurl;
        private TimeBean time;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getAdid() {
            return adid;
        }

        public void setAdid(String adid) {
            this.adid = adid;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * date : 2017-02-24T01:25:12.293Z
             * year : 2017
             * month : 2017-2
             * day : 2017-2-24
             * minute : 2017-2-24 9:25
             */

            private String date;
            private int year;
            private String month;
            private String day;
            private String minute;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getMinute() {
                return minute;
            }

            public void setMinute(String minute) {
                this.minute = minute;
            }
        }
    }
}
