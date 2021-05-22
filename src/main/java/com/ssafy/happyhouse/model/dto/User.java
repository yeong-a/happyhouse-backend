package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String email;
	private String name;
	private String pwd;
	private String address;
	private String detailAddress;
	private boolean isAdmin;
}
