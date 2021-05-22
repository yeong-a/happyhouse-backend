package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.User;

// Model : Service
// 회원 관련 서비스 로직

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 회원가입
	@Override
	public boolean signup(User user) throws SQLException {
		if (getUser(user.getEmail()) == null) {
			return userDAO.insert(user) > 0;
		} else {
			throw new RuntimeException("아이디가 중복되어 회원가입이 불가능합니다.");
		}
	}

	// 회원조회
	@Override
	public User getUser(String userId) throws SQLException {
		return userDAO.select(userId);
	}

	// 로그인
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
	public boolean update(String email, String name, String address, String detailAddress) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return false;
		}
		user.setName(name);
		user.setAddress(address);
		user.setDetailAddress(detailAddress);
		return userDAO.update(user) > 0;
	}

	@Override
	public boolean changePassword(String email, String pwd) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return false;
		}
		user.setPwd(pwd);
		return userDAO.update(user) > 0;
	}

	@Override
	public boolean delete(String email) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return false;
		}
		return userDAO.delete(user) > 0;
	}
}
