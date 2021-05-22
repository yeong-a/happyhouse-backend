package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnA {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String email;
	private Date regtime;
}