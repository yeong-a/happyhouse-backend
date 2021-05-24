package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Answer;
import com.ssafy.happyhouse.model.dto.Question;
import com.ssafy.happyhouse.model.dto.Response;
import com.ssafy.happyhouse.model.service.QnABoardService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qnaboard")
public class QnARestController {

	@Autowired
	private QnABoardService qnaBoardService;

	@GetMapping()
	public ResponseEntity<Response> getQuestions() {
		List<Question> questions = qnaBoardService.getQuestions();
		return Response.newResult(HttpStatus.OK, questions);
	}

	@GetMapping("/{no}")
	public ResponseEntity<Response> viewQuestion(@PathVariable int no) {
		Question question = qnaBoardService.getQuestion(no);
		if (question == null) {
			return Response.newError(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.");
		}
		return Response.newResult(HttpStatus.OK, question);
	}

	@PostMapping
	public Object writeQuestion(HttpSession session, @RequestBody Question question) {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
		}
		if (question.getTitle() == null || question.getContent() == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "제목과 내용을 입력하세요.");
		}
		question.setWriter((String) session.getAttribute("name"));
		question.setEmail((String) session.getAttribute("email"));
		if (qnaBoardService.createQuestion(question)) {
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}

	@PutMapping("/{no}")
	public Object updateQustion(@RequestBody Question question, HttpSession session, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
		}
		if (qnaBoardService.getQuestion(no) == null) {
			return Response.newError(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다.");
		}
		if (!qnaBoardService.getEmail(no).equals(session.getAttribute("email"))) {
			return Response.newError(HttpStatus.BAD_REQUEST, "작성자가 다릅니다.");
		}
		if (question.getTitle() == null || question.getContent() == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "제목과 내용을 입력하세요.");
		}
		question.setNo(no);
		if (qnaBoardService.updateQustion(question)) {
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");

	}

	@DeleteMapping("/{no}")
	public Object deleteQuestion(HttpSession session, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
		}
		if (qnaBoardService.getQuestion(no) == null) {
			return Response.newError(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다.");
		}
		if (!qnaBoardService.getEmail(no).equals(session.getAttribute("email"))) {
			return Response.newError(HttpStatus.BAD_REQUEST, "작성자가 다릅니다.");
		}
		if (qnaBoardService.deleteQuestion(no)) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}

	@GetMapping("/{no}/answers")
	public ResponseEntity<Response> getAnswers(@PathVariable int no) {
		if (qnaBoardService.getQuestion(no) == null) {
			return Response.newError(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다.");
		}
		return Response.newResult(HttpStatus.OK, qnaBoardService.getAnswers(no));
	}

	@PostMapping("/{no}/answers")
	public Object writeAnswer(HttpSession session, @RequestBody Answer answer, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
		}
		if (qnaBoardService.getQuestion(no) == null) {
			return Response.newError(HttpStatus.NOT_FOUND, "게시물이 존재하지 않습니다.");
		}
		if ((!(boolean) session.getAttribute("isAdmin"))) {
			return Response.newError(HttpStatus.BAD_REQUEST, "관리자가 아닙니다.");
		}
		if (answer.getContent() == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "내용을 입력하세요.");
		}
		answer.setNo(no);
		answer.setWriter((String) session.getAttribute("name"));
		if (qnaBoardService.createAnswer(answer)) {
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");

	}
}