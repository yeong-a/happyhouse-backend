package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.BoardDAO;
import com.ssafy.happyhouse.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDAO BoardDAO;
	@Autowired
	public void setUserDAO(BoardDAO BoardDAO) {
		this.BoardDAO = BoardDAO;
	}

	@Override
	public int register(@Param("title") String title, @Param("content") String content) throws SQLException {
		return BoardDAO.insert(title, content);
	}

	@Override
	public List<Board> getBoardList() throws SQLException {
		return BoardDAO.select();
	}

	@Override
	public void update(@Param("bno") String bno, @Param("title") String title, @Param("content") String content) throws SQLException {
		BoardDAO.update(bno, title, content);
	}

	@Override
	public void delete(String bno) throws SQLException {
		BoardDAO.delete(bno);
	}
}
