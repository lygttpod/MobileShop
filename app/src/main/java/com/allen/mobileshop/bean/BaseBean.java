package com.allen.mobileshop.bean;

import java.io.Serializable;

/**
 * Created by Allen on 2016/2/2.
 */
public class BaseBean implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
