package com.ssafy.happyhouse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Response;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

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
	private Object login(@RequestBody User userInput, HttpSession session) throws SQLException {
		if (userService.login(userInput.getEmail(), userInput.getPwd()) == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "아이디나 비밀번호가 일치하지 않습니다.");
		}
		User user = userService.getUser(userInput.getEmail());
		session.setAttribute("isAdmin", user.isAdmin());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("name", user.getName());
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	@PutMapping("/update")
	private Object modifyInfo(@RequestBody User user, HttpSession session, HttpServletResponse response)
			throws SQLException {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (!user.getEmail().equals(session.getAttribute("email"))) {
			return Response.newError(HttpStatus.BAD_REQUEST, "자신의 정보만 수정 가능합니다.");
		}
		String email = user.getEmail();
		String name = user.getName();
		String address = user.getAddress();
		String detailAddress = user.getDetailAddress();

		if (userService.updateUser(email, name, address, detailAddress)) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}

	@Data
	static class ChangePasswordRequest {
		String currentPwd;
		String newPwd;
	}

	@PutMapping("/changepwd")
	private Object changePassword(@RequestBody ChangePasswordRequest request, HttpSession session) throws SQLException {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (request.currentPwd == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "현재 비밀번호를 입력해주세요.");
		}
		if (request.newPwd == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "새 비밀번호를 입력해주세요.");
		}
		User user = userService.getUser((String) session.getAttribute("email"));
		if (!request.getCurrentPwd().equals(user.getPwd())) {
			return Response.newError(HttpStatus.BAD_REQUEST, "현재 비밀번호가 틀렸습니다.");
		}
		if (userService.updatePassword((String) session.getAttribute("email"), request.getNewPwd())) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}

	@PostMapping("/signup")
	private Object signup(@RequestBody User user, HttpSession session, HttpServletResponse response)
			throws SQLException {
		if (session.getAttribute("email") != null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그아웃을 먼저 해주세요.");
		}
		if (userService.getUser(user.getEmail()) != null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "이미 존재하는 email 입니다.");
		}
		if (userService.signup(user)) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}

	@DeleteMapping("/delete")
	private Object deleteInfo(HttpSession session, HttpServletResponse response) throws SQLException {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (userService.deleteUser(email)) {
			session.invalidate();
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}
}
