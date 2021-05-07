package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Board;

public interface BoardService {
	boolean register(String title, String content) throws SQLException;
	List<Board> getBoardList() throws SQLException;
	void update(String bno, String title, String content) throws SQLException;
	void delete(String bno) throws SQLException;
}
