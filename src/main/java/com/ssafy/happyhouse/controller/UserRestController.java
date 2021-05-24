package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Favorite;
import com.ssafy.happyhouse.model.dto.House;
import com.ssafy.happyhouse.model.dto.Response;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

import lombok.Data;

@RequestMapping("/user")
@RestController
public class UserRestController {

	private UserService userService = new UserServiceImpl();
	private HouseService houseService = new HouseServiceImpl();

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
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
	private Object modifyInfo(@RequestBody User user, HttpSession session) throws SQLException {
		if (session.getAttribute("email") == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (!session.getAttribute("email").equals(user.getEmail())) {
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
		if (request.getCurrentPwd() == null) {
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
	private Object signup(@RequestBody User user, HttpSession session) throws SQLException {
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
	private Object deleteInfo(HttpSession session) throws SQLException {
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

	@GetMapping("/favorites")
	private ResponseEntity<Response> viewFavorites(HttpSession session) throws SQLException {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		List<String> dong = userService.getFavoriteDong(email);
		if (dong.size() == 0) {
			return Response.newResult(HttpStatus.NO_CONTENT, dong);
		}
		return Response.newResult(HttpStatus.OK, dong);
	}

	@GetMapping("/favorites/houses")
	private ResponseEntity<Response> viewFavoriteHouses(HttpSession session, @RequestParam String dong)
			throws SQLException {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (dong == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "동이름을 입력해주세요");
		}
		List<House> houses = houseService.getHouseList(dong);
		if (houses.size() == 0) {
			return Response.newError(HttpStatus.BAD_REQUEST, "결과가 없습니다.");
		}
		return Response.newResult(HttpStatus.OK, houses);
	}

	@PostMapping("/favorites")
	private Object addFavorite(HttpSession session, @RequestBody Favorite favorite) throws SQLException {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
		}
		if (favorite.getDong() == null) {
			return Response.newError(HttpStatus.BAD_REQUEST, "동이름을 입력해주세요");
		}
		List<String> list = userService.getFavoriteDong(email);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(favorite.getDong())) {
					return Response.newError(HttpStatus.BAD_REQUEST, "이미 관심지역에 등록된 지역입니다.");
				}
			}
		}
		favorite.setEmail(email);
		if (userService.addFavoriteDong(favorite)) {
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return Response.newError(HttpStatus.INTERNAL_SERVER_ERROR, "에러");
	}
}
