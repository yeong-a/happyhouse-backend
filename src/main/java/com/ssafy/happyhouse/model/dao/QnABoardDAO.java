package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.QnA;

@Mapper
public interface QnABoardDAO {
	public List<QnA> selectQnA();

	public QnA selectQnAByNo(int no);

	public int insertQnA(QnA qna);

	public int updateQnA(QnA qna);

	public int deleteQnA(int no);

	public List<Answer> selectAnswer(int no);

	public int insertAnswer(Answer answer);
}