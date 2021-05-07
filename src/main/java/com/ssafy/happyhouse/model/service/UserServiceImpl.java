package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dao.UserDAOImpl;
import com.ssafy.happyhouse.model.dto.User;

// Model : Service
// 회원 관련 서비스 로직
public class UserServiceImpl implements UserService {
	private UserDAO userDao = new UserDAOImpl();

	// 회원가입
	@Override
	public boolean signup(User user) throws SQLException {
		if (getUser(user.getEmail()) == null) {
			return userDao.insert(user);
		} else {
			throw new RuntimeException("아이디가 중복되어 회원가입이 불가능합니다.");
		}
	}

	// 회원조회
	@Override
	public User getUser(String userId) throws SQLException {
		return userDao.select(userId);
	}

	// 로그인
	@Override
	public String login(String email, String pwd) throws SQLException {
		User user = getUser(email);
		if (user == null)
			return null;	// 이메일 일치하지 않음

		if (user.getPwd().equals(pwd))
			return user.getName();
		else
			return null;	// 비번 틀림
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
		return userDao.update(user);
	}

	@Override
	public boolean changePassword(String email, String pwd) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return false;
		}
		user.setPwd(pwd);
		return userDao.update(user);
	}

	@Override
	public boolean delete(String email) throws SQLException {
		User user = getUser(email);
		if (user == null) {
			return false;
		}
		return userDao.delete(user);
	}
}
