package com.ssafy.happyhouse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/happyhouse";
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
