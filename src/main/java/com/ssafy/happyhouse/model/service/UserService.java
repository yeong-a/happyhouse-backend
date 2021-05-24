package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Favorite;
import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	boolean signup(User user) throws SQLException;

	User getUser(String email) throws SQLException;

	User login(String email, String pwd) throws SQLException;

	boolean updateUser(String email, String name, String address, String detailAddress) throws SQLException;

	boolean updatePassword(String email, String pwd) throws SQLException;

	boolean deleteUser(String email) throws SQLException;

	List<String> getFavoriteDong(String email) throws SQLException;

	boolean addFavoriteDong(Favorite favorite);
}