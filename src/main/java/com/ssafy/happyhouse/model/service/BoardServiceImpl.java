package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.BoardDao;
import com.ssafy.happyhouse.model.dao.BoardDaoImpl;
import com.ssafy.happyhouse.model.dto.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = new BoardDaoImpl();

	@Override
	public boolean register(String title, String content) throws SQLException {
		return boardDao.insert(title, content);
	}

	@Override
	public List<Board> getBoardList() throws SQLException {
		return boardDao.select();
	}

	@Override
	public void update(String bno, String title, String content) throws SQLException {
		boardDao.update(bno, title, content);
	}

	@Override
	public void delete(String bno) throws SQLException {
		boardDao.delete(bno);
	}
}
