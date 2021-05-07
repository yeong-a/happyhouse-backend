package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.ssafy.happyhouse.model.dto.House;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;

@Controller
public class HouseController {
	private HouseService houseService = new HouseServiceImpl();

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// /house/xxx.do
		String url = request.getServletPath().substring(6); // /xxx.do
		if (url.equals("/detail.do")) {
			getHouseList(request, response);
		} else if (url.equals("/detail2.do")) {
			getHouseListByName(request, response);
		}
	}

	private void getHouseListByName(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String aptName = request.getParameter("aptName");
		List<House> houseList = houseService.getHouseListByName(aptName);
		request.setAttribute("houseList", houseList);
		request.getRequestDispatcher("/detail2.jsp").forward(request, response);
	}

	private void getHouseList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String dong = request.getParameter("dong");
		List<House> houseList = houseService.getHouseList(dong);
		request.setAttribute("houseList", houseList);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}
}
