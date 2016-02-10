package com.hotelquickly.niroshan.hotelquickly.beans;

import java.io.Serializable;

/**
 * com.hotelquickly.niroshan.hotelquickly
 * <p/>
 * Created by Niroshan Rathnayake.
 */
public class BeanObjectList implements Serializable{

    private String cache;
    private String pageTitle;
    private String url;
    private String filePath;
    private String namespace;

    public BeanObjectList(){}

    public BeanObjectList(String cache, String pageTitle, String url){

        this.cache = cache;
        this.pageTitle = pageTitle;
        this.url = url;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
