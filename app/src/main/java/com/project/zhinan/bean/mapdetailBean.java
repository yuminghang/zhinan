package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2017/1/20.
 */
public class mapdetailBean {


    /**
     * status : success
     * success : 获取成功
     * data : {"_id":"5881bfc4498c2d05dd23a42f","orderno":"14848982445365111","name":"123456","head":"http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48","title":"教育","uuid":"376fc380-dee4-11e6-aebd-1ff799a23fc6","time":{"date":"2017-01-20T07:44:04.536Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:44"},"addesc":null,"tags":["edu","tour"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide265e47ca-1bea-412a-8b41-b56456b7a157"],"budget":"200","ad_put_begintime":"2017-01-20 15:43","ad_put_endtime":"2017-01-20 15:43","key":"便宜，优惠！","read":"欢迎抢购！！","icons":"200","sig_money":"5","comments":[],"reprint_info":[],"pv":0,"isPaid":false}
     */

    private String status;
    private String success;
    /**
     * _id : 5881bfc4498c2d05dd23a42f
     * orderno : 14848982445365111
     * name : 123456
     * head : http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48
     * title : 教育
     * uuid : 376fc380-dee4-11e6-aebd-1ff799a23fc6
     * time : {"date":"2017-01-20T07:44:04.536Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:44"}
     * addesc : null
     * tags : ["edu","tour"]
     * imgurls : ["http://guide-10035266.image.myqcloud.com/guide265e47ca-1bea-412a-8b41-b56456b7a157"]
     * budget : 200
     * ad_put_begintime : 2017-01-20 15:43
     * ad_put_endtime : 2017-01-20 15:43
     * key : 便宜，优惠！
     * read : 欢迎抢购！！
     * icons : 200
     * sig_money : 5
     * comments : []
     * reprint_info : []
     * pv : 0
     * isPaid : false
     */

    private DataEntity data;

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
        private String head;
        private String title;
        private String uuid;
        /**
         * date : 2017-01-20T07:44:04.536Z
         * year : 2017
         * month : 2017-1
         * day : 2017-1-20
         * minute : 2017-1-20 15:44
         */

        private TimeEntity time;
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
        private List<String> tags;
        private String[] imgurls;
        private List<?> comments;
        private List<?> reprint_info;

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

        public TimeEntity getTime() {
            return time;
        }

        public void setTime(TimeEntity time) {
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

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String[] getImgurls() {
            return imgurls;
        }

        public void setImgurls(String[] imgurls) {
            this.imgurls = imgurls;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public List<?> getReprint_info() {
            return reprint_info;
        }

        public void setReprint_info(List<?> reprint_info) {
            this.reprint_info = reprint_info;
        }

        public static class TimeEntity {
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
