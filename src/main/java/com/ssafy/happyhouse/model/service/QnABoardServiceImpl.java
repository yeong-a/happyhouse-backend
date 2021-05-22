package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.QnABoardDAO;
import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.QnA;

@Service
public class QnABoardServiceImpl implements QnABoardService {

	@Autowired
	private QnABoardDAO qnaBoardDao;

	@Override
	public List<QnA> retrieveBoard() {
		return qnaBoardDao.selectQnA();
	}

	@Override
	public boolean writeQnA(QnA board) {
		return qnaBoardDao.insertQnA(board) > 0;
	}

	@Override
	public QnA detailQnA(int no) {
		return qnaBoardDao.selectQnAByNo(no);
	}

	@Override
	@Transactional
	public boolean updateQnA(QnA board) {
		return qnaBoardDao.updateQnA(board) > 0;
	}

	@Override
	@Transactional
	public boolean deleteQnA(int no) {
		return qnaBoardDao.deleteQnA(no) > 0;
	}

	@Override
	public List<Answer> selectAnswer(int no) {
		return qnaBoardDao.selectAnswer(no);
	}

	@Override
	public boolean insertAnswer(Answer answer) {
		return qnaBoardDao.insertAnswer(answer) > 0;
	}

	@Override
	public String getEmail(int no) {
		return qnaBoardDao.getEmail(no);
	}
}