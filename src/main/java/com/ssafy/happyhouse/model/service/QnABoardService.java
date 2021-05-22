package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.QnA;

public interface QnABoardService {
	public List<QnA> retrieveBoard();

	public QnA detailQnA(int no);

	public boolean writeQnA(QnA qna);

	public boolean updateQnA(QnA qna);

	public boolean deleteQnA(int no);

	public List<Answer> selectAnswer(int no);

	public boolean insertAnswer(Answer answer);

	public String getEmail(int no);
}
