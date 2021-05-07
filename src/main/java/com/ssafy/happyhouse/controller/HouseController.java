package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.ssafy.happyhouse.model.dto.House;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;


@RequestMapping("/house")
@Controller
public class HouseController {
	private HouseService houseService;

	@Autowired
	public void setUserService(HouseService houseService) {
		this.houseService = houseService;
	}
	
	@GetMapping("/datail2")
	private String getHouseListByName(@RequestParam String aptName,HttpServletRequest request) throws SQLException{
		
		List<House> houseList = houseService.getHouseListByName(aptName);
		request.setAttribute("houseList", houseList);		
		return "/house/detail2";
	}
	
//	private void getHouseListByName(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//		String aptName = request.getParameter("aptName");
//		List<House> houseList = houseService.getHouseListByName(aptName);
//		request.setAttribute("houseList", houseList);
//		request.getRequestDispatcher("/detail2.jsp").forward(request, response);
//	}
	@GetMapping("/datail")
	private String getHouseList(@RequestParam String sido,@RequestParam String gugun,@RequestParam String dong,@RequestParam String type,HttpServletRequest request) throws SQLException {
		List<House> houseList = houseService.getHouseList(dong);
		request.setAttribute("houseList", houseList);
		return "/datail";
	}
}



//private void getHouseList(HttpServletRequest request, HttpServletResponse response)
//		throws SQLException, ServletException, IOException {
//	String dong = request.getParameter("dong");
//	List<House> houseList = houseService.getHouseList(dong);
//	request.setAttribute("houseList", houseList);
//	request.getRequestDispatcher("/detail.jsp").forward(request, response);
//}