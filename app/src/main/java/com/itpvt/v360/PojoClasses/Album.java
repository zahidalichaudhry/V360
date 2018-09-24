package com.itpvt.v360.PojoClasses;

/**
 * Created by zahid on 02-Oct-17.
 */

public class Album {//this is the function through which you get name and url from json file from php array list
    private String name,url;
    public Album(){}

    public Album(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
