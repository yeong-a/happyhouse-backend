package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

public class UserController {
	private UserService userService = new UserServiceImpl();

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// /user/xxx.do
		String url = request.getServletPath().substring(5); // /xxx.do
		if (url.equals("/login.do")) {
			login(request, response);
		} else if (url.equals("/mypage.do")) {
			mypage(request, response);
		} else if (url.equals("/logout.do")) {
			logout(request, response);
		} else if (url.equals("/signuppage.do")) {
			signuppage(request, response);
		} else if (url.equals("/signup.do")) {
			signup(request, response);
		} else if (url.equals("/updateinfo.do")) {
			updateInfo(request, response);
		} else if (url.equals("/changepassword.do")) {
			changePassword(request, response);
		} else if (url.equals("/delete.do")) {
			deleteUser(request, response);
		} else if (url.equals("/searchPassword.do")) {
			searchPassword(request, response);
		} else if (url.equals("/interestsSetting.do")) {
			interestsSetting(request, response);
		} else if (url.equals("/interestsInfo.do")) {
			interestsInfo(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		String name = userService.login(email, pwd);
		if (name != null) { // 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("name", name);

			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		User user = userService.getUser(userId);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/mypage.jsp").forward(request, response);
	}

	private void signuppage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");

		if (userService.signup(new User(email, name, pwd, address, detailAddress))) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddresss");

		if (userService.update(email, name, address, detailAddress)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		String currentPassword = request.getParameter("current_password");
		String password = request.getParameter("password");

		User user = userService.getUser(email);
		if (!user.getPwd().equals(currentPassword)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (userService.changePassword(email, password)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (userService.delete(email)) {
			request.getSession().invalidate();
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void interestsSetting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/interests_setting.jsp").forward(request, response);
	}

	private void interestsInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/interests_info.jsp").forward(request, response);
	}

	private void searchPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/search_password.jsp").forward(request, response);
	}

}
