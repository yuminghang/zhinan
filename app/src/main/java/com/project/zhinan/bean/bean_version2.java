package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/11/7.
 */
public class bean_version2 {

    /**
     * success : true
     * data : [{"_id":"5881c23a498c2d05dd23a435","orderno":"14848988744072649","name":"123456","head":"http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48","title":"教育2","uuid":"aede9a80-dee5-11e6-aebd-1ff799a23fc6","time":{"date":"2017-01-20T07:54:34.407Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:54"},"addesc":null,"tags":["edu","tour"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide6f1a4b20-00b4-41e9-aad0-1900c1960f3c","http://guide-10035266.image.myqcloud.com/guide25fed752-e2a0-421c-bf3d-3a57556213e0"],"budget":"200","ad_put_begintime":"2017-01-20 15:54","ad_put_endtime":"2017-01-20 15:54","key":"福到财神到","read":"读一读，试一试","icons":"200","sig_money":"5","comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"5881c1ba498c2d05dd23a433","orderno":"14848987469239677","name":"123456","head":"http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48","title":"教育广告","uuid":"62e216c0-dee5-11e6-aebd-1ff799a23fc6","time":{"date":"2017-01-20T07:52:26.923Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:52"},"addesc":null,"tags":["edu","tour"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide2d1d5c4e-1f0b-4c9b-a279-412e665cb961"],"budget":"200","ad_put_begintime":"2017-01-20 15:52","ad_put_endtime":"2017-01-20 15:52","key":"优惠大酬宾","read":"读一读，试一试","icons":"200","sig_money":"5","comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"5881c04a498c2d05dd23a431","orderno":"14848983787196664","name":"123456","head":"http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48","title":"测试","uuid":"876a76f0-dee4-11e6-aebd-1ff799a23fc6","time":{"date":"2017-01-20T07:46:18.719Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:46"},"addesc":null,"tags":["edu"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide77a2dd7d-a12f-414f-a4bd-06ad496619c3"],"budget":"200","ad_put_begintime":"2017-01-20 15:45","ad_put_endtime":"2017-01-20 15:45","key":"。。。。。。","read":"123456789","icons":"200","sig_money":"5","comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"5881bfc4498c2d05dd23a42f","orderno":"14848982445365111","name":"123456","head":"http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48","title":"教育","uuid":"376fc380-dee4-11e6-aebd-1ff799a23fc6","time":{"date":"2017-01-20T07:44:04.536Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:44"},"addesc":null,"tags":["edu","tour"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide265e47ca-1bea-412a-8b41-b56456b7a157"],"budget":"200","ad_put_begintime":"2017-01-20 15:43","ad_put_endtime":"2017-01-20 15:43","key":"便宜，优惠！","read":"欢迎抢购！！","icons":"200","sig_money":"5","comments":[],"reprint_info":[],"pv":0,"isPaid":false}]
     * page : 1
     * total : 4
     */

    private boolean success;
    private String page;
    private int total;
    /**
     * _id : 5881c23a498c2d05dd23a435
     * orderno : 14848988744072649
     * name : 123456
     * head : http://www.gravatar.com/avatar/d6600cb4ea0715340349bccc14034059?s=48
     * title : 教育2
     * uuid : aede9a80-dee5-11e6-aebd-1ff799a23fc6
     * time : {"date":"2017-01-20T07:54:34.407Z","year":2017,"month":"2017-1","day":"2017-1-20","minute":"2017-1-20 15:54"}
     * addesc : null
     * tags : ["edu","tour"]
     * imgurls : ["http://guide-10035266.image.myqcloud.com/guide6f1a4b20-00b4-41e9-aad0-1900c1960f3c","http://guide-10035266.image.myqcloud.com/guide25fed752-e2a0-421c-bf3d-3a57556213e0"]
     * budget : 200
     * ad_put_begintime : 2017-01-20 15:54
     * ad_put_endtime : 2017-01-20 15:54
     * key : 福到财神到
     * read : 读一读，试一试
     * icons : 200
     * sig_money : 5
     * comments : []
     * reprint_info : []
     * pv : 0
     * isPaid : false
     */

    private List<DataEntity> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String _id;
        private String orderno;
        private String name;
        private String head;
        private String title;
        private String uuid;
        /**
         * date : 2017-01-20T07:54:34.407Z
         * year : 2017
         * month : 2017-1
         * day : 2017-1-20
         * minute : 2017-1-20 15:54
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
//        private List<String> tags;
        private String[] imgurls;
        private List<?> comments;
        private List<?> reprint_info;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public void setTime(TimeEntity time) {
            this.time = time;
        }

        public void setAddesc(Object addesc) {
            this.addesc = addesc;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public void setAd_put_begintime(String ad_put_begintime) {
            this.ad_put_begintime = ad_put_begintime;
        }

        public void setAd_put_endtime(String ad_put_endtime) {
            this.ad_put_endtime = ad_put_endtime;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public void setIcons(String icons) {
            this.icons = icons;
        }

        public void setSig_money(String sig_money) {
            this.sig_money = sig_money;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

//        public void setTags(List<String> tags) {
//            this.tags = tags;
//        }

        public void setImgurls(String[] imgurls) {
            this.imgurls = imgurls;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public void setReprint_info(List<?> reprint_info) {
            this.reprint_info = reprint_info;
        }

        public String get_id() {
            return _id;
        }

        public String getOrderno() {
            return orderno;
        }

        public String getName() {
            return name;
        }

        public String getHead() {
            return head;
        }

        public String getTitle() {
            return title;
        }

        public String getUuid() {
            return uuid;
        }

        public TimeEntity getTime() {
            return time;
        }

        public Object getAddesc() {
            return addesc;
        }

        public String getBudget() {
            return budget;
        }

        public String getAd_put_begintime() {
            return ad_put_begintime;
        }

        public String getAd_put_endtime() {
            return ad_put_endtime;
        }

        public String getKey() {
            return key;
        }

        public String getRead() {
            return read;
        }

        public String getIcons() {
            return icons;
        }

        public String getSig_money() {
            return sig_money;
        }

        public int getPv() {
            return pv;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

//        public List<String> getTags() {
//            return tags;
//        }

        public String[] getImgurls() {
            return imgurls;
        }

        public List<?> getComments() {
            return comments;
        }

        public List<?> getReprint_info() {
            return reprint_info;
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
