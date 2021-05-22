package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.service.QnABoardService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qnaboard")
public class QnARestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnABoardService qnaBoardService;

	@GetMapping
	public ResponseEntity<List<QnA>> selectQnA() throws Exception {
		return new ResponseEntity<List<QnA>>(qnaBoardService.retrieveBoard(), HttpStatus.OK);
	}

	@GetMapping("/{no}")
	public ResponseEntity<String> detailQnA(@PathVariable int no) {
		if (qnaBoardService.detailQnA(no) == null) {
			return new ResponseEntity<String>("게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> writeQnA(HttpSession session, @RequestBody QnA qna) {
		if (session.getAttribute("name") != null) {
			qna.setWriter((String) session.getAttribute("name"));
			qna.setEmail((String) session.getAttribute("email"));
			if (qnaBoardService.writeQnA(qna)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("로그인 필요", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{no}")
	public ResponseEntity<String> updateQnA(@RequestBody QnA qna, HttpSession session, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return new ResponseEntity<String>("로그인 필요", HttpStatus.BAD_REQUEST);
		}
		if (qnaBoardService.getEmail(no).equals(session.getAttribute("email"))) {
			qna.setNo(no);
			if (qnaBoardService.updateQnA(qna)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("작성자 다름", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteQnA(HttpSession session, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return new ResponseEntity<String>("로그인 필요", HttpStatus.BAD_REQUEST);
		}
		if (qnaBoardService.getEmail(no).equals(session.getAttribute("email"))) {
			if (qnaBoardService.deleteQnA(no)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("작성자 다름", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/answer/{no}")
	public ResponseEntity<List<Answer>> selectAnswer(@PathVariable int no) {
		return new ResponseEntity<List<Answer>>(qnaBoardService.selectAnswer(no), HttpStatus.OK);
	}

	@PostMapping("/answer/{no}")
	public ResponseEntity<String> insertAnswer(HttpSession session, HttpServletResponse response,
			@RequestBody Answer answer, @PathVariable int no) {
		if (session.getAttribute("email") == null) {
			return new ResponseEntity<String>("로그인 필요", HttpStatus.BAD_REQUEST);
		}
		if (qnaBoardService.detailQnA(no) == null) {
			return new ResponseEntity<String>("해당번호 게시물 없음", HttpStatus.BAD_REQUEST);
		}
		if (((boolean) session.getAttribute("isAdmin"))) {
			answer.setNo(no);
			answer.setWriter("admin");
			if (qnaBoardService.insertAnswer(answer)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>("관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
	}
}