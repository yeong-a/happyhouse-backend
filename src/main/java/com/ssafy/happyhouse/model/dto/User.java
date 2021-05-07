package com.ssafy.happyhouse.model.dto;

// Model : VO, DTO
// 회원 정보를 표현
public class User {
	private String email;
	private String name;
	private String pwd;
	private String address;
	private String detailAddress;
	
	public User(String email, String name, String pwd, String address, String detailAddress) {
		super();
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}	
}
