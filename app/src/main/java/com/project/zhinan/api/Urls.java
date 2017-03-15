package com.project.zhinan.api;

/**
 * Created by ymh on 2016/4/11.
 */
public class Urls {
    //http://120.27.41.245:2888/getAds?tag=fangchan&page=1
    public static final String BaseUrl = "http://120.27.41.245:2888/";
    public static final String TagUrl = "http://120.27.41.245:2888/getAds?tag=";
    public static final String PageNum = "&page=1";
    public static final String BaoxiangUrl = TagUrl + "edu" + PageNum;
    public static final String CanyinUrl = TagUrl + "food" + PageNum;
    public static final String FangchanUrl = TagUrl + "property" + PageNum;
    public static final String FuwuUrl = TagUrl + "service" + PageNum;
    public static final String GongyiUrl = TagUrl + "public_welfare" + PageNum;
    public static final String HongbaoUrl = TagUrl + "edu" + PageNum;
    public static final String HuodongUrl = TagUrl + "edu" + PageNum;
    public static final String JiancaiUrl = TagUrl + "build" + PageNum;
    public static final String JinrongUrl = TagUrl + "financial" + PageNum;
    public static final String Jiaoyu = TagUrl + "edu" + PageNum;
    public static final String Quanbu = "http://120.27.41.245:2888/getAds";//全部
//    public static final String Quanbu = TagUrl +  PageNum;//全部
    public static final String LvyouUrl = TagUrl + "tour" + PageNum;
    public static final String MeizhuangUrl = TagUrl + "makeups" + PageNum;
    public static final String QianggouUrl = "http://119.29.191.229:2888/com/try";
    public static final String QicheUrl = TagUrl + "car" + PageNum;
    public static final String ShangquanUrl = TagUrl + "edu" + PageNum;
    public static final String TongxunUrl = TagUrl + "commuication" + PageNum;
    public static final String XiuxianUrl = TagUrl + "edu" + PageNum;
    public static final String YiyaoUrl = TagUrl + "med" + PageNum;
    public static final String ZhaopinUrl = TagUrl + "recruitment" + PageNum;
    public static final String ZhaoshangUrl = TagUrl + "edu" + PageNum;
    public static final String DetailUrl = "http://120.27.41.245:2888/tryhtml.html";
    public static final String Collection_Url = "http://120.27.41.245:2888/collection_c";
    public static final String Map_Url = "http://120.27.41.245:2888/adposition";
    public static final String Map_Detail_Url = "http://120.27.41.245:2888/ad_detail?adorder=";
    public static final String get_Account_Info_Url = "http://120.27.41.245:2888/getOwnInfo";

}
