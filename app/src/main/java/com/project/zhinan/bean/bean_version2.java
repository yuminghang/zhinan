package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/11/7.
 */
public class bean_version2 {

    /**
     * success : true
     * data : [{"_id":"58202f4482382715b557bd03","orderno":"14785042607679242","name":"admin","head":null,"title":"eeee","time":{"date":"2016-11-07T07:37:40.767Z","year":2016,"month":"2016-11","day":"2016-11-7","minute":"2016-11-7 15:37"},"addesc":"快来买呀！便宜处理了","tags":["jiaoyu","fangchan"],"imgurls":"http://guide-10035266.image.myqcloud.com/guide1478504205168","budget":"2444","ad_put_begintime":"2016-11-07","ad_put_endtime":"2016-11-16","key":"eeee","icons":"2444","sig_money":null,"comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"581ff50282382715b557bd01","orderno":"14784893462762682","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","title":"123123","time":{"date":"2016-11-07T03:29:06.276Z","year":2016,"month":"2016-11","day":"2016-11-7","minute":"2016-11-7 11:29"},"addesc":"快来买呀！便宜处理了","tags":["jiaoyu","lvyou","fangchan"],"imgurls":"http://guide-10035266.image.myqcloud.com/guide1478489320330","budget":"123","ad_put_begintime":"2016-11-01","ad_put_endtime":"2016-11-09","key":"12312","icons":"123","sig_money":null,"comments":[],"reprint_info":[],"pv":0,"isPaid":false},{"_id":"581ff4dc82382715b557bd00","orderno":"14784893088140556","name":"dengyi","head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48","title":"name","time":{"date":"2016-11-07T03:28:28.814Z","year":2016,"month":"2016-11","day":"2016-11-7","minute":"2016-11-7 11:28"},"addesc":"快来买呀！便宜处理了","tags":["jiaoyu","lvyou"],"imgurls":["http://guide-10035266.image.myqcloud.com/guide1478489272074","http://guide-10035266.image.myqcloud.com/guide1478489282373"],"budget":"1000","ad_put_begintime":"2016-11-08","ad_put_endtime":"2016-11-08","key":"key","icons":"1000","sig_money":null,"comments":[],"reprint_info":[],"pv":0,"isPaid":false}]
     * page : 1
     * total : 3
     */

    private boolean success;
    private String page;
    private int total;
    /**
     * _id : 58202f4482382715b557bd03
     * orderno : 14785042607679242
     * name : admin
     * head : null
     * title : eeee
     * time : {"date":"2016-11-07T07:37:40.767Z","year":2016,"month":"2016-11","day":"2016-11-7","minute":"2016-11-7 15:37"}
     * addesc : 快来买呀！便宜处理了
     * tags : ["jiaoyu","fangchan"]
     * imgurls : http://guide-10035266.image.myqcloud.com/guide1478504205168
     * budget : 2444
     * ad_put_begintime : 2016-11-07
     * ad_put_endtime : 2016-11-16
     * key : eeee
     * icons : 2444
     * sig_money : null
     * comments : []
     * reprint_info : []
     * pv : 0
     * isPaid : false
     */

    private List<DataEntity> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String _id;
        private String orderno;
        private String name;
        private Object head;
        private String title;
        /**
         * date : 2016-11-07T07:37:40.767Z
         * year : 2016
         * month : 2016-11
         * day : 2016-11-7
         * minute : 2016-11-7 15:37
         */

        private TimeEntity time;
        private String addesc;
        private Object imgurls;
        private String budget;
        private String ad_put_begintime;
        private String ad_put_endtime;
        private String key;
        private String icons;
        private Object sig_money;
        private int pv;
        private boolean isPaid;
        private Object tags;
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

        public Object getHead() {
            return head;
        }

        public void setHead(Object head) {
            this.head = head;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public Object getImgurls() {
            return imgurls;
        }

        public void setImgurls(Object imgurls) {
            this.imgurls = imgurls;
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

        public String getIcons() {
            return icons;
        }

        public void setIcons(String icons) {
            this.icons = icons;
        }

        public Object getSig_money() {
            return sig_money;
        }

        public void setSig_money(Object sig_money) {
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

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
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
