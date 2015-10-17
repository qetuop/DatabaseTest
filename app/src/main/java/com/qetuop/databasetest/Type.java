package com.qetuop.databasetest;

import java.io.Serializable;

/**
 * Created by brian on 10/11/15.
 */
public class Type implements Serializable {
    private long id = 0;
    private String type = null;

    public Type() {

    }

    public Type(String type) {
        this.type = type;
    }

    public Type(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
