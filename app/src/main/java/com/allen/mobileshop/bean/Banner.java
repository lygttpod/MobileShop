package com.allen.mobileshop.bean;

/**
 * Created by Allen on 2016/2/2.
 */
public class Banner extends BaseBean {

    //            "id":1,
    //            "name":"音箱狂欢",
    //            "description":null,
    //            "imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/5608f3b5Nc8d90151.jpg",
    //            "type":1
    private String name;
    private String description;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
