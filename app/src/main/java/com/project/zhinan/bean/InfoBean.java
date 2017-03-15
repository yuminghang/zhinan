package com.project.zhinan.bean;

/**
 * Created by ymh on 2017/3/15.
 */
public class InfoBean {

    /**
     * success : true
     * sub : {"_id":"58c902006ce2515841d6aa0f","name":"dengyi","password":"c07b982d7ae82ed3c852455cecccf131","email":"dengyi@qq.com","enterprise":"false","uuid":"6f8fe800-095d-11e7-9079-0f43ff0b0aff","enterprisename":"","enterpriseid":"","legalperson":"","user_collection":"","account":1000,"yue":0,"quan":[],"head":"http://www.gravatar.com/avatar/2105d5823f14811f46a99657c36a283b?s=48"}
     */

    private boolean success;
    /**
     * _id : 58c902006ce2515841d6aa0f
     * name : dengyi
     * password : c07b982d7ae82ed3c852455cecccf131
     * email : dengyi@qq.com
     * enterprise : false
     * uuid : 6f8fe800-095d-11e7-9079-0f43ff0b0aff
     * enterprisename :
     * enterpriseid :
     * legalperson :
     * user_collection :
     * account : 1000
     * yue : 0
     * quan : []
     * head : http://www.gravatar.com/avatar/2105d5823f14811f46a99657c36a283b?s=48
     */

    private SubEntity sub;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SubEntity getSub() {
        return sub;
    }

    public void setSub(SubEntity sub) {
        this.sub = sub;
    }

    public static class SubEntity {
        private String _id;
        private String name;
        private String password;
        private String email;
        private String enterprise;
        private String uuid;
        private String enterprisename;
        private String enterpriseid;
        private String legalperson;
        private String user_collection;
        private int account;
        private int yue;
        private String head;
        private String[] quan;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(String enterprise) {
            this.enterprise = enterprise;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getEnterprisename() {
            return enterprisename;
        }

        public void setEnterprisename(String enterprisename) {
            this.enterprisename = enterprisename;
        }

        public String getEnterpriseid() {
            return enterpriseid;
        }

        public void setEnterpriseid(String enterpriseid) {
            this.enterpriseid = enterpriseid;
        }

        public String getLegalperson() {
            return legalperson;
        }

        public void setLegalperson(String legalperson) {
            this.legalperson = legalperson;
        }

        public String getUser_collection() {
            return user_collection;
        }

        public void setUser_collection(String user_collection) {
            this.user_collection = user_collection;
        }

        public int getAccount() {
            return account;
        }

        public void setAccount(int account) {
            this.account = account;
        }

        public int getYue() {
            return yue;
        }

        public void setYue(int yue) {
            this.yue = yue;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String[] getQuan() {
            return quan;
        }

        public void setQuan(String[] quan) {
            this.quan = quan;
        }
    }
}
