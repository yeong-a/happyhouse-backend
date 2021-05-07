package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Board;

public interface BoardDao {
	boolean insert(String title, String content) throws SQLException;
	List<Board> select() throws SQLException;
	void delete(String bno) throws SQLException;
	void update(String bno, String title, String content) throws SQLException;
}
