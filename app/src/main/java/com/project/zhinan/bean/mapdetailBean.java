package com.project.zhinan.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ymh on 2017/1/20.
 */
public class mapdetailBean {

    /**
     * status : success
     * success : 获取成功
     * data : {"_id":"588319559b0fea3760bdb92c","orderno":"14849867095588178","name":"dengyi","head":null,"title":"哇哈哈哈","uuid":"30b30470-dfb2-11e6-859b-0337312d9df5","time":{"date":"2017-01-21T08:18:29.558Z","year":2017,"month":"2017-1","day":"2017-1-21","minute":"2017-1-21 16:18"},"addesc":"快来买呀！便宜处理了","tags":"tour","imgurls":["http://guide-10035266.image.myqcloud.com/guide1484986656495"],"budget":"100","ad_put_begintime":"2017-01-21","ad_put_endtime":"2017-02-04","key":"测试。。。。。","read":"测试。。。。。","icons":"100","sig_money":"2","comments":[],"reprint_info":[],"pv":0,"isPaid":false}
     */

    private String status;
    private String success;
    /**
     * _id : 588319559b0fea3760bdb92c
     * orderno : 14849867095588178
     * name : dengyi
     * head : null
     * title : 哇哈哈哈
     * uuid : 30b30470-dfb2-11e6-859b-0337312d9df5
     * time : {"date":"2017-01-21T08:18:29.558Z","year":2017,"month":"2017-1","day":"2017-1-21","minute":"2017-1-21 16:18"}
     * addesc : 快来买呀！便宜处理了
     * tags : tour
     * imgurls : ["http://guide-10035266.image.myqcloud.com/guide1484986656495"]
     * budget : 100
     * ad_put_begintime : 2017-01-21
     * ad_put_endtime : 2017-02-04
     * key : 测试。。。。。
     * read : 测试。。。。。
     * icons : 100
     * sig_money : 2
     * comments : []
     * reprint_info : []
     * pv : 0
     * isPaid : false
     */

    private DataEntity data;

    public static mapdetailBean objectFromData(String str) {

        return new Gson().fromJson(str, mapdetailBean.class);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private String _id;
        private String orderno;
        private String name;
        private String title;
        private String uuid;
        /**
         * date : 2017-01-21T08:18:29.558Z
         * year : 2017
         * month : 2017-1
         * day : 2017-1-21
         * minute : 2017-1-21 16:18
         */

        private TimeEntity time;
        private String addesc;
        private String tags;
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

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

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

        public TimeEntity getTime() {
            return time;
        }

        public void setTime(TimeEntity time) {
            this.time = time;
        }

        public String getAddesc() {
            return addesc;
        }

        public void setAddesc(String addesc) {
            this.addesc = addesc;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
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

        public static class TimeEntity {
            private String date;
            private int year;
            private String month;
            private String day;
            private String minute;

            public static TimeEntity objectFromData(String str) {

                return new Gson().fromJson(str, TimeEntity.class);
            }

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
