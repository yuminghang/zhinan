package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by deng on 17-2-23.
 */

public class MyUploadBean {

    /**
     * success : true
     * data : [{"_id":"58ad9c52f9aeb77cc7dfc7b7","orderno":"14877727545670848","name":"dengyi","head":"http://www.gravatar.com/avatar/2105d5823f14811f46a99657c36a283b?s=48","title":"666","uuid":"f4ee2080-f908-11e6-bb36-f36caa7c7fea","time":{"date":"2017-02-22T14:12:34.567Z","year":2017,"month":"2017-2","day":"2017-2-22","minute":"2017-2-22 22:12"},"addesc":null,"tags":["edu","tour","property"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide9e068380-d992-44d4-8ee2-f0575c26ff65"],"budget":"200","ad_put_begintime":"2017-02-22 22:11","ad_put_endtime":"2017-02-22 22:11","key":"5555","read":"666666","icons":"200","sig_money":"2","comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"589e85baad41aa328b7ae687","orderno":"14867839309583830","name":"dengyi","head":null,"title":"111","uuid":"ac219500-f00a-11e6-8b2a-35e2977586b2","time":{"date":"2017-02-11T03:32:10.958Z","year":2017,"month":"2017-2","day":"2017-2-11","minute":"2017-2-11 11:32"},"addesc":"快来买呀！便宜处理了","tags":"edu","imgurls":["http://guide-10035266.image.myqcloud.com/guide1486783883475"],"budget":"1000","ad_put_begintime":"2017-02-11","ad_put_endtime":"2017-02-25","key":"10000","read":"10000000","icons":"1000","sig_money":"10","comments":[],"reprint_info":[],"pv":0,"isPaid":false}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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
         * _id : 58ad9c52f9aeb77cc7dfc7b7
         * orderno : 14877727545670848
         * name : dengyi
         * head : http://www.gravatar.com/avatar/2105d5823f14811f46a99657c36a283b?s=48
         * title : 666
         * uuid : f4ee2080-f908-11e6-bb36-f36caa7c7fea
         * time : {"date":"2017-02-22T14:12:34.567Z","year":2017,"month":"2017-2","day":"2017-2-22","minute":"2017-2-22 22:12"}
         * addesc : null
         * tags : ["edu","tour","property"]
         * imgurls : ["http://guide-10035266.image.myqcloud.com/guide9e068380-d992-44d4-8ee2-f0575c26ff65"]
         * budget : 200
         * ad_put_begintime : 2017-02-22 22:11
         * ad_put_endtime : 2017-02-22 22:11
         * key : 5555
         * read : 666666
         * icons : 200
         * sig_money : 2
         * comments : []
         * reprint_info : []
         * pv : 0
         * isPaid : false
         */

        private String _id;
        private String orderno;
        private String name;
        private String head;
        private String title;
        private String uuid;
        private TimeBean time;
        private Object addesc;
        private String budget;
        private String ad_put_begintime;
        private String ad_put_endtime;
        private String key;
        private String read;
        private String icons;
        private String sig_money;
        private int pv;
        private boolean isPaid;
        private List<String> imgurls;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public Object getAddesc() {
            return addesc;
        }

        public void setAddesc(Object addesc) {
            this.addesc = addesc;
        }

        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public String getAd_put_begintime() {
            return ad_put_begintime;
        }

        public void setAd_put_begintime(String ad_put_begintime) {
            this.ad_put_begintime = ad_put_begintime;
        }

        public String getAd_put_endtime() {
            return ad_put_endtime;
        }

        public void setAd_put_endtime(String ad_put_endtime) {
            this.ad_put_endtime = ad_put_endtime;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getIcons() {
            return icons;
        }

        public void setIcons(String icons) {
            this.icons = icons;
        }

        public String getSig_money() {
            return sig_money;
        }

        public void setSig_money(String sig_money) {
            this.sig_money = sig_money;
        }

        public int getPv() {
            return pv;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }





        public List<String> getImgurls() {
            return imgurls;
        }

        public void setImgurls(List<String> imgurls) {
            this.imgurls = imgurls;
        }



        public static class TimeBean {
            /**
             * date : 2017-02-22T14:12:34.567Z
             * year : 2017
             * month : 2017-2
             * day : 2017-2-22
             * minute : 2017-2-22 22:12
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
