package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.Favorite;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.UserInfo;

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
			return null;
		}
		if (user.getPwd().equals(pwd)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean updateUser(String email, String name, String address, String detailAddress) throws SQLException {
		User user = getUser(email);
		user.setName(name);
		user.setAddress(address);
		user.setDetailAddress(detailAddress);
		return userDAO.update(user) > 0;
	}

	@Override
	public boolean updatePassword(String email, String pwd) throws SQLException {
		User user = getUser(email);
		user.setPwd(pwd);
		return userDAO.update(user) > 0;
	}

	@Override
	public boolean deleteUser(String email) throws SQLException {
		return userDAO.delete(getUser(email)) > 0;
	}

	@Override
	public List<String> getFavoriteDong(String email) throws SQLException {
		return userDAO.getFavoriteDong(email);
	}

	@Override
	public boolean addFavoriteDong(Favorite favorite) {
		return userDAO.addFavoriteDong(favorite) > 0;
	}

	@Override
	public UserInfo getInfo(String email) throws SQLException {
		User user = getUser(email);
		return new UserInfo(user.getEmail(), user.getName(), user.getAddress(), user.getDetailAddress(),
				user.isAdmin());
	}
}
