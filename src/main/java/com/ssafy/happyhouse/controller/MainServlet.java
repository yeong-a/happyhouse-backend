package com.ssafy.happyhouse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// loadOnStartup : 미리 init() 호출해서 만들어놈, 안할경우는 사용자가 호출할때 한번 init() 호출함
@WebServlet(loadOnStartup = 1, urlPatterns = {"*.do"})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController userController = new UserController();
	private BoardController boardController = new BoardController();
	private HouseController houseController = new HouseController();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://127.0.0.1:8080/myapp/user/login.do
		
		// /user/register.do
		// /user/login.do
		// /user/mypage.do
		
		// /board/register.do
		
		try {
			String url = request.getServletPath();
			if(url.startsWith("/user")) {
				userController.process(request, response);
			} else if(url.startsWith("/board")) {
				boardController.process(request, response);
			} else if(url.startsWith("/house")) {
				houseController.process(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			// forwarding: / ==> app root
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
