package com.ssafy.happyhouse.model.dto;

public class Board {
	private String bno;
	private String title;
	private String content;
	private String hit;
	public Board(String bno, String title, String content, String hit) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.hit = hit;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
}
