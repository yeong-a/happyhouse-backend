package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;

public interface QnABoardService {
	public List<Question> getQuestions();

	public Question getQuestion(int no);

	public boolean createQuestion(Question qna);

	public boolean updateQustion(Question qna);

	public boolean deleteQuestion(int no);

	public List<Answer> getAnswers(int no);

	public boolean createAnswer(Answer answer);

	public String getEmail(int no);
}
