package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.QnA;

public interface QnABoardService {
	public List<QnA> retrieveBoard();

	public QnA detailBoard(int no);

	public boolean writeBoard(QnA qna);

	public boolean updateBoard(QnA qna);

	public boolean deleteBoard(int no);
}
