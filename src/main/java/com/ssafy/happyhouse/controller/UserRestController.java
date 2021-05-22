package com.ssafy.happyhouse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.Data;

@RequestMapping("/user")
@RestController
public class UserRestController {

	private UserService userService = new UserServiceImpl();

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/logout")
	private void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/login")
	private void login(@RequestBody User userInput, HttpSession session, HttpServletResponse response)
			throws SQLException {
		if (session.getAttribute("email") != null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		User user = userService.login(userInput.getEmail(), userInput.getPwd());

		if (user == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (user.isAdmin()) {
			session.setAttribute("isAdmin", true);
		} else {
			session.setAttribute("isAdmin", false);
		}

		session.setAttribute("email", user.getEmail());
		session.setAttribute("name", user.getName());
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@PutMapping("/update")
	private void updateInfo(@RequestBody User user, HttpSession session, HttpServletResponse response)
			throws SQLException {
		if (user.getEmail().equals(session.getAttribute("email"))) {
			String email = user.getEmail();
			String name = user.getName();
			String address = user.getAddress();
			String detailAddress = user.getDetailAddress();

			if (userService.update(email, name, address, detailAddress)) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@Data
	@AllArgsConstructor
	static class ChangePasswordRequest {
		String currentPwd;
		String newPwd;
	}

	@PutMapping("/changepwd")
	private void changePassword(@RequestBody ChangePasswordRequest request, HttpSession session,
			HttpServletResponse response) throws SQLException {
		ChangePasswordRequest cpr = new ChangePasswordRequest(request.currentPwd, request.newPwd);
		User user = userService.getUser((String) session.getAttribute("email"));
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (cpr.getCurrentPwd().equals(user.getPwd())) {
			if (userService.changePassword((String) session.getAttribute("email"), cpr.getNewPwd())) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@PostMapping("/signup")
	private void signup(@RequestBody User user, HttpSession session, HttpServletResponse response) throws SQLException {
		if (session.getAttribute("email") == null) {
			if (userService.signup(user)) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	@DeleteMapping("/delete")
	private void delete(HttpSession session, HttpServletResponse response) throws SQLException {

		String email = (String) session.getAttribute("email");
		if (email == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (userService.delete(email)) {
			session.invalidate();
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
}
