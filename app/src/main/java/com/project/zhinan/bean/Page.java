package com.project.zhinan.bean;


/**
 * Created by ymh on 2016/10/10.
 */
public class Page {
    //问卷id
    private String pageId;
    //问卷状态
    private String status;
    //问卷主题
    private String title;
    //题目
    private Quesition quesitions;


    public Quesition getQuesitions() {
        return quesitions;
    }
    public void setQuesitions(Quesition quesitions) {
        this.quesitions = quesitions;
    }

    public String getPageId() {
        return pageId;
    }
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
