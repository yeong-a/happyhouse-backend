package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;

@Mapper
public interface QnABoardDAO {
	public List<Question> selectQuestions();

	public Question selectQuestionByNo(int no);

	public int insertQuestion(Question qna);

	public int updateQuestion(Question qna);

	public int deleteQuestion(int no);

	public List<Answer> selectAnswers(int no);

	public int insertAnswer(Answer answer);

	public String getEmail(int no);
}