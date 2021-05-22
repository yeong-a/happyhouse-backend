package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	// 회원가입
	boolean signup(User user) throws SQLException;

	// 회원조회
	User getUser(String email) throws SQLException;

	// 로그인
	User login(String email, String pwd) throws SQLException;

	// 내 정보 수정
	boolean updateUser(String email, String name, String address, String detailAddress) throws SQLException;

	// 비밀번호 변경
	boolean updatePassword(String email, String pwd) throws SQLException;

	// 회원 탈퇴
	boolean deleteUser(String email) throws SQLException;
}