package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.User;

public interface UserDAO {
	// 회원정보 insert
	boolean insert(User user) throws SQLException;

	// 회원정보 select
	User select(String email) throws SQLException;

	// 회원정보 update
	boolean update(User user) throws SQLException;
	
	// 회원정보 delete
	boolean delete(User user) throws SQLException;
}