package com.qunar.autotest.uitest.model;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class PageBean {
    private String softSize;
    private String desp = StringUtils.EMPTY;
    private String picURL;
    private DownLoad[] download;
    private String updateDate;
    private String title;
    private String sortLevel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortLevel() {
        return sortLevel;
    }

    public void setSortLevel(String sortLevel) {
        this.sortLevel = sortLevel;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public DownLoad[] getDownload() {
        return download;
    }

    public void setDownload(DownLoad[] download) {
        this.download = download;
    }

    public String getSoftSize() {
        return softSize;
    }

    public void setSoftSize(String softSize) {
        this.softSize = softSize;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "softSize='" + softSize + '\'' +
                ", desp='" + desp + '\'' +
                ", picURL='" + picURL + '\'' +
                ", download=" + Arrays.toString(download) +
                ", updateDate='" + updateDate + '\'' +
                ", title='" + title + '\'' +
                ", sortLevel='" + sortLevel + '\'' +
                '}';
    }
}
