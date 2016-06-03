package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/6/3.
 */
public class hongbaocache {

    /**
     * url : http://m.baidu.com/lightapp/3406137/?page=https%3A%2F%2F1.baidu.com%2Fwapproduct%2Fwapdetail%3Fproduct_id%3Db80573e78b40151ff72913b4%26from%3D6265613747474e4e704f4b535041395072526e6d6d4a396d39585176396e7a706634756a347a6c564862567a447a51
     * head_pic : http://wa1.baidu-1img.cn/timg?cbs&size=f656_500&quality=60&sec=0&di=6e7766b157f10264f845b6d14c1e7031&src=http://hiphotos.baidu.com/pay-operone/wh=640,680/sign=da549dee9545d688a357baa290f25128/b90e7bec54e736d13687d8d59c504fc2d4626952.jpg
     * title : 1元许愿Kindle电子书尊贵版
     * price : 1.00
     * product_id : b80573e78b40151ff72913b4
     * appid : 3406137
     * source : 百度钱包
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String url;
        private String head_pic;
        private String title;
        private String price;
        private String product_id;
        private String appid;
        private String source;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getProduct_id() {
            return product_id;
        }

        public String getAppid() {
            return appid;
        }

        public String getSource() {
            return source;
        }
    }
}
