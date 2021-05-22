package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean signup(User user) throws SQLException {
		return userDAO.insert(user) > 0;
	}

	@Override
	public User getUser(String email) throws SQLException {
		return userDAO.select(email);
	}

	@Override
	public User login(String email, String pwd) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return null; // 이메일 일치하지 않음
		}
		if (user.getPwd().equals(pwd)) {
			return user;
		} else {
			return null; // 비번 틀림
		}
	}

	@Override
	public boolean updateUser(String email, String name, String address, String detailAddress) throws SQLException {
		return userDAO.update(getUser(email)) > 0;
	}

	@Override
	public boolean updatePassword(String email, String pwd) throws SQLException {
		return userDAO.update(getUser(email)) > 0;
	}

	@Override
	public boolean deleteUser(String email) throws SQLException {
		return userDAO.delete(getUser(email)) > 0;
	}
}
