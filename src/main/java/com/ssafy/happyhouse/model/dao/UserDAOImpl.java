package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.util.DBUtil;

public class UserDAOImpl implements UserDAO {
	// 회원정보 insert
	@Override
	public boolean insert(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into member(email, name, pwd, address, detailAddress) values(?, ?, ?, ?, ?)";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getPwd());
			stmt.setString(4, user.getAddress());
			stmt.setString(5, user.getDetailAddress());
			return stmt.executeUpdate() > 0;
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	// 회원정보 select
	@Override
	public User select(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from member where email = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs.next()) {
				// 1: email, 2: name, 3: pwd, 4: address, 5: detailAddress
				return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public boolean update(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update member set name = ?, address = ?, detailAddress = ?, pwd = ? where email = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getAddress());
			stmt.setString(3, user.getDetailAddress());
			stmt.setString(4, user.getPwd());
			stmt.setString(5, user.getEmail());
			return stmt.executeUpdate() > 0;
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public boolean delete(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from member where email = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			return stmt.executeUpdate() > 0;
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
