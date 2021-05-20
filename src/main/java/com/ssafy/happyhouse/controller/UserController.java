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

	@RequestMapping("/interestsSetting")
	private String interestsSetting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/interests_setting";
	}

	@RequestMapping("/interestsInfo")
	private String interestsInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/interests_info";
	}

	@RequestMapping("/searchPassword")
	private String searchPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/search_password";
	}

	@RequestMapping("/mypage")
	private String mypage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/mypage";
	}

	@RequestMapping("/signuppage")
	private String signuppage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/signup";
	}
}
