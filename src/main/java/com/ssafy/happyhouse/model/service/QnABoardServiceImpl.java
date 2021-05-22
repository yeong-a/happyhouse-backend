package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.QnABoardDAO;
import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;

@Service
public class QnABoardServiceImpl implements QnABoardService {

	@Autowired
	private QnABoardDAO qnaBoardDao;

	@Override
	public List<Question> getQuestions() {
		return qnaBoardDao.selectQuestions();
	}

	@Override
	public Question getQuestion(int no) {
		return qnaBoardDao.selectQuestionByNo(no);
	}

	@Override
	public boolean createQuestion(Question question) {
		return qnaBoardDao.insertQuestion(question) > 0;
	}

	@Override
	@Transactional
	public boolean updateQustion(Question question) {
		return qnaBoardDao.updateQuestion(question) > 0;
	}

	@Override
	@Transactional
	public boolean deleteQuestion(int no) {
		return qnaBoardDao.deleteQuestion(no) > 0;
	}

	@Override
	public List<Answer> getAnswers(int no) {
		return qnaBoardDao.selectAnswers(no);
	}

	@Override
	public boolean createAnswer(Answer answer) {
		return qnaBoardDao.insertAnswer(answer) > 0;
	}

	@Override
	public String getEmail(int no) {
		return qnaBoardDao.getEmail(no);
	}
}