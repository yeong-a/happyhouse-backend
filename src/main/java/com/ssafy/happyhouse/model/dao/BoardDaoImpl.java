package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	@Override
	public boolean insert(String title, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into board(title, content) values(?, ?)";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			return stmt.executeUpdate() > 0;
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public List<Board> select() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board";
		ArrayList<Board> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Board(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public void delete(String bno) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "delete from board where bno = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bno);
			stmt.executeUpdate();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public void update(String bno, String title, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update board set title=?, content=? where bno = ?";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, bno);
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
