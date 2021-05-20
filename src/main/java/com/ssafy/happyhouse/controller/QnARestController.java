package com.ssafy.happyhouse.controller;

import java.util.List;

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
	public ResponseEntity<QnA> detailQnA(@PathVariable int no) {
		return new ResponseEntity<QnA>(qnaBoardService.detailBoard(no), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> writeQnA(@RequestBody QnA qna) {
		if (qnaBoardService.writeBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{no}")
	public ResponseEntity<String> updateQnA(@RequestBody QnA qna, @PathVariable int no) {
		qna.setNo(no);
		if (qnaBoardService.updateBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteQnA(@PathVariable int no) {
		if (qnaBoardService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/answer/{no}")
	public ResponseEntity<List<Answer>> selectAnswer(@PathVariable int no) {
		return new ResponseEntity<List<Answer>>(qnaBoardService.selectAnswer(no), HttpStatus.OK);
	}

	@PostMapping("/answer/{no}")
	public ResponseEntity<String> insertAnswer(@RequestBody Answer answer, @PathVariable int no) {
		answer.setNo(no);
		if (qnaBoardService.insertAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}