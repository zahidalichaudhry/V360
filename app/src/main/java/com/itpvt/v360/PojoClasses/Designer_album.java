package com.itpvt.v360.PojoClasses;

/**
 * Created by Itpvt on 17-Nov-17.
 */

public class Designer_album {
    private String Name,url,Username;
    public Designer_album(){}
    public Designer_album(String name, String url, String username) {
        this.Name = name;
        this.url = url;
        this.Username = username;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
