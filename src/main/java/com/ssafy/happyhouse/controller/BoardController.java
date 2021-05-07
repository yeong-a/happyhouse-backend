package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import org.springframework.stereotype.Controller;
>>>>>>> e2f102ffa061febe047a25f014b0bb09cde294f9

import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.BoardServiceImpl;

<<<<<<< HEAD


@RequestMapping("/board")
=======
@Controller
>>>>>>> e2f102ffa061febe047a25f014b0bb09cde294f9
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("addArticle")
	private String addArticle(@RequestParam String title,@RequestParam String content)
			throws SQLException{
		boardService.register(title, content);

		return "/notice";
	}
	
	@GetMapping("deleteArticle")
	private String deleteArticle(@RequestParam String bno)
			throws SQLException, ServletException, IOException {
		boardService.delete(bno);
		return "/notice";
	}

	
	@GetMapping("updateArticle")
	private String updateArticle(@RequestParam String bno,@RequestParam String title,@RequestParam String content) throws SQLException {
	
		boardService.update(bno, title, content);
		return "/notice";
	}

	@RequestMapping
	private String showArticle(HttpServletRequest request) throws SQLException
		{
		request.setAttribute("boardList", boardService.getBoardList());
		
		return "/notice";
	}

	
}
