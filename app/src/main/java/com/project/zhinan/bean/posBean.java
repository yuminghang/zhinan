package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/12/21.
 */
public class posBean {

    /**
     * success : true
     * msg : 成功
     * data : [{"_id":"5860d198c5406857074d7c95","ad_order":"14827401207567098","lat":"34.330446","lon":"108.898409","time":{"date":"2016-12-26T08:15:20.829Z","year":2016,"month":"2016-12","day":"2016-12-26","minute":"2016-12-26 16:15"},"user":{"_id":"5816e8f1b19df1fb6fcf9919","name":"dengyi","password":"e10adc3949ba59abbe56e057f20f883e","email":null,"phone":null,"state":0,"account":0,"head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48"}}]
     */

    private boolean success;
    private String msg;
    /**
     * _id : 5860d198c5406857074d7c95
     * ad_order : 14827401207567098
     * lat : 34.330446
     * lon : 108.898409
     * time : {"date":"2016-12-26T08:15:20.829Z","year":2016,"month":"2016-12","day":"2016-12-26","minute":"2016-12-26 16:15"}
     * user : {"_id":"5816e8f1b19df1fb6fcf9919","name":"dengyi","password":"e10adc3949ba59abbe56e057f20f883e","email":null,"phone":null,"state":0,"account":0,"head":"http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48"}
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
         * date : 2016-12-26T08:15:20.829Z
         * year : 2016
         * month : 2016-12
         * day : 2016-12-26
         * minute : 2016-12-26 16:15
         */

        private TimeEntity time;
        /**
         * _id : 5816e8f1b19df1fb6fcf9919
         * name : dengyi
         * password : e10adc3949ba59abbe56e057f20f883e
         * email : null
         * phone : null
         * state : 0
         * account : 0
         * head : http://www.gravatar.com/avatar/1a885ae77be414b8e1beda9298e08f7a?s=48
         */

        private UserEntity user;

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

        public void setUser(UserEntity user) {
            this.user = user;
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

        public UserEntity getUser() {
            return user;
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

        public static class UserEntity {
            private String _id;
            private String name;
            private String password;
            private Object email;
            private Object phone;
            private int state;
            private int account;
            private String head;

            public void set_id(String _id) {
                this._id = _id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setAccount(int account) {
                this.account = account;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String get_id() {
                return _id;
            }

            public String getName() {
                return name;
            }

            public String getPassword() {
                return password;
            }

            public Object getEmail() {
                return email;
            }

            public Object getPhone() {
                return phone;
            }

            public int getState() {
                return state;
            }

            public int getAccount() {
                return account;
            }

            public String getHead() {
                return head;
            }
        }
    }
}
