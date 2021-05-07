package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	// 회원가입
	boolean signup(User user) throws SQLException;

	// 회원조회
	User getUser(String userId) throws SQLException;

	// 로그인
	String login(String userId, String userPwd) throws SQLException;
	
	// 내 정보 수정
	boolean update(String email, String name, String address, String detailAddress) throws SQLException;
	
	// 비밀번호 변경
	boolean changePassword(String email, String pwd) throws SQLException;
	
	// 회원 탈퇴
	boolean delete(String email) throws SQLException;
}