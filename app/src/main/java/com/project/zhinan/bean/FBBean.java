package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by deng on 16-12-22.
 */

public class FBBean {

    /**
     * name : 123123
     * imgurls[] : ["http://guide-10035266.image.myqcloud.com/guide1482412503557","http://guide-10035266.image.myqcloud.com/guide1482412517443"]
     * key : 123123
     * ad_put_begintime : 2016-12-22
     * ad_put_endtime : 2016-12-23
     * budget : 122222
     * sig_money : 123
     * tags[] : ["jiaoyu","lvyou"]
     * addesc : 快来买呀！便宜处理了
     */

    private String name;
    private String key;
    private String ad_put_begintime;
    private String ad_put_endtime;
    private String budget;
    private String sig_money;
    private String addesc;
    private String paytype;

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    private String read;

    public List<String> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<String> imgurls) {
        this.imgurls = imgurls;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public java.util.List<String> imgurls;
    public java.util.List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSig_money() {
        return sig_money;
    }

    public void setSig_money(String sig_money) {
        this.sig_money = sig_money;
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc;
    }
}
