package com.itpvt.v360.PojoClasses;


public class AlbumPort {
    private String username,url;
    public AlbumPort(){}

    public AlbumPort(String username, String url) {
        this.username = username;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
