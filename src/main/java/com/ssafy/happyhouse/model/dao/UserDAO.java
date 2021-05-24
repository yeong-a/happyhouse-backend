package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Favorite;
import com.ssafy.happyhouse.model.dto.User;

@Mapper
@Repository
public interface UserDAO {
	int insert(User user) throws SQLException;

	User select(String email) throws SQLException;

	int update(User user) throws SQLException;

	int delete(User user) throws SQLException;

	List<String> getFavoriteDong(String email);

	int addFavoriteDong(Favorite favorite);
}