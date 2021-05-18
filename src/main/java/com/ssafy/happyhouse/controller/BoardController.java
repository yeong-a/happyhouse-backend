package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {

	private BoardService boardService;

	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping("addArticle")
	private String addArticle(@RequestParam String title, @RequestParam String content) throws SQLException {
		boardService.register(title, content);

		return "redirect:/board/showArticle";
	}

	@PostMapping("deleteArticle")
	private String deleteArticle(@RequestParam String bno) throws SQLException, ServletException, IOException {
		boardService.delete(bno);
		return "redirect:/board/showArticle";
	}

	@PostMapping("updateArticle")
	private String updateArticle(@RequestParam String bno, @RequestParam String title, @RequestParam String content)
			throws SQLException {

		boardService.update(bno, title, content);
		return "redirect:/board/showArticle";
	}

	@RequestMapping("showArticle")
	private String showArticle(HttpServletRequest request) throws SQLException {
		request.setAttribute("boardList", boardService.getBoardList());

		return "/notice";
	}

}
