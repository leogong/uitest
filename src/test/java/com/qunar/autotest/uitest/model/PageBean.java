package com.qunar.autotest.uitest.model;

public class PageBean {
	private String[] sort;
	private String softSize;
	private String desp;
	private String picURL;
	private DownLoad[] download;
	private String updateDate;

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

	public String[] getSort() {
		return sort;
	}

	public void setSort(String[] sort) {
		this.sort = sort;
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

}
