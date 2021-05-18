package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Board;

@Mapper
@Repository
public interface BoardDAO {
	int insert(String title, String content) throws SQLException;

	List<Board> select() throws SQLException;

	void delete(String bno) throws SQLException;

	void update(String bno, String title, String content) throws SQLException;
}
