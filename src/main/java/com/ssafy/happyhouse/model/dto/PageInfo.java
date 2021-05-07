package com.ssafy.happyhouse.model.dto;

public class PageInfo {
	private String url;
	private boolean isFoward;

	public PageInfo(String url, boolean isFoward) {
		this.url = url;
		this.isFoward = isFoward;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFoward() {
		return isFoward;
	}

	public void setFoward(boolean isFoward) {
		this.isFoward = isFoward;
	}
}
