package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.BoardServiceImpl;

@Controller
public class BoardController {
	private BoardService boardService = new BoardServiceImpl();

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String url = request.getServletPath().substring(6); // /xxx.do
		if (url.equals("/addArticle.do")) {
			addArticle(request, response);
		} else if (url.equals("/showArticle.do")) {
			showArticle(request, response);
		} else if (url.equals("/updateArticle.do")) {
			updateArticle(request, response);
		} else if (url.equals("/deleteArticle.do")) {
			deleteArticle(request, response);
		}
	}

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String bno = request.getParameter("bno");
		boardService.delete(bno);
		request.getRequestDispatcher("/notice.jsp").forward(request, response);
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		boardService.update(bno, title, content);
		request.getRequestDispatcher("/notice.jsp").forward(request, response);
	}

	private void showArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("boardList", boardService.getBoardList());
		request.getRequestDispatcher("/notice.jsp").forward(request, response);
	}

	private void addArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		boardService.register(title, content);
		request.getRequestDispatcher("/notice.jsp").forward(request, response);
	}
}
