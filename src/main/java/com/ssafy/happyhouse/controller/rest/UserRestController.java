package com.ssafy.happyhouse.controller.rest;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userService = new UserServiceImpl();

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@PostMapping("/login")  //REST!!!!!!!!!!!
	private boolean login(@ModelAttribute User user, HttpSession session,HttpServletResponse response) throws SQLException {

		String name = userService.login(user.getEmail(), user.getPwd());

		if (name == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return true;
		}

		session.setAttribute("email", user.getEmail());
		session.setAttribute("name", name);
		response.setStatus(HttpServletResponse.SC_OK);
		return true;
	}
	
	@PostMapping("/updateInfo")    //REST!!!!!!!!!!!
	private boolean updateInfo(@ModelAttribute User user, HttpSession session,HttpServletResponse response) throws SQLException {
		
		String email = user.getEmail();

		String name = user.getName();
		String address = user.getAddress();
		String detailAddress = user.getDetailAddress();

		if (userService.update(email, name, address, detailAddress)){
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/changepassword")  //REST!!!!!!!!!!!
	private boolean changePassword(@RequestParam(value="current_password") String currentPassword,@RequestParam String password,HttpSession session,HttpServletResponse response) throws SQLException {
		
		String email = (String)session.getAttribute("email");

		User user = userService.getUser(email);
		if (!user.getPwd().equals(currentPassword)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return true;
		}

		if (userService.changePassword(email, password)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/signup")  //REST!!!!!!!!!!!
	private boolean signup(@ModelAttribute User user, HttpSession session,HttpServletResponse response) throws SQLException {

		if (userService.signup(user)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
	@PostMapping("/deleteUser") //REST!!!!!!!!!!!
	private boolean deleteUser(HttpSession session,HttpServletResponse response) throws SQLException  {
		
		String email = (String) session.getAttribute("email");

		if (userService.delete(email)) {
			session.invalidate();
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return true;
	}
	
}
