package com.allen.mobileshop.bean;

/**
 * Created by Allen on 2016/2/22.
 */
public class Category {

    /**
     * id : 1
     * name : 热门推荐
     * sort : 1
     */

    private int id;
    private String name;
    private int sort;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSort() {
        return sort;
    }
}
