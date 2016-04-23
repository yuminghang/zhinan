package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by deng on 16-4-23.
 */
public class IPublish {

    /**
     * _id : 5718d4511274030d1a538f07
     * name : dengyi
     * head : http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48
     * time : {"date":"2016-04-21T13:23:29.385Z","year":2016,"month":"2016-4","day":"2016-4-21","minute":"2016-4-21 21:23"}
     * addesc : 广告标底。****促销信息。****广告内容。
     * tags : [null,null,null]
     * adurl : www.baidu.com
     * imgurl : http://123.206.84.242:2888/images/1edc664135aa750be2394bf73ed24efc.jpg
     * icons : 2
     * comments : []
     * reprint_info : []
     * pv : 0
     */

    private List<SuccessEntity> success;

    public List<SuccessEntity> getSuccess() {
        return success;
    }

    public void setSuccess(List<SuccessEntity> success) {
        this.success = success;
    }

    public static class SuccessEntity {
        private String _id;
        private String name;
        private String head;
        /**
         * date : 2016-04-21T13:23:29.385Z
         * year : 2016
         * month : 2016-4
         * day : 2016-4-21
         * minute : 2016-4-21 21:23
         */

        private TimeEntity time;
        private String addesc;
        private String adurl;
        private String imgurl;
        private int icons;
        private int pv;
        private List<?> tags;
        private List<?> comments;
        private List<?> reprint_info;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
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

        public String getAdurl() {
            return adurl;
        }

        public void setAdurl(String adurl) {
            this.adurl = adurl;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getIcons() {
            return icons;
        }

        public void setIcons(int icons) {
            this.icons = icons;
        }

        public int getPv() {
            return pv;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
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
