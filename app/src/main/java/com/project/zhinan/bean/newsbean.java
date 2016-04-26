package com.project.zhinan.bean;

/**
 * Created by ymh on 2016/4/26.
 */
public class newsbean {


    /**
     * title : 马云被曝曾要求新员工上下班时间不得超过15分钟
     * description : 【环球科技综合报道】据印度媒体4月24日报道，邓肯·克拉克(Duncan Clark)在新书《阿里巴巴：马云的基业》(Alibaba: The House that Jack Ma Built)中曝出马云在阿里巴巴创业阶段曾要求员工住在公司附近，以节省上下班时间。
     * category : 科技教育
     * pubDate : 2016-04-26 08:46
     */

    private String title;
    private String description;
    private String category;
    private String pubDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPubDate() {
        return pubDate;
    }
}
