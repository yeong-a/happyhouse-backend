package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.User;

@Mapper
@Repository
public interface UserDAO {
	// 회원정보 insert
	int insert(User user) throws SQLException;

	// 회원정보 select
	User select(String email) throws SQLException;

	// 회원정보 update
	int update(User user) throws SQLException;
	
	// 회원정보 delete
	int delete(User user) throws SQLException;
}