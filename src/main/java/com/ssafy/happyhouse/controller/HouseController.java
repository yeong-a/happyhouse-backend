package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Response;
import com.ssafy.happyhouse.model.service.HouseService;

@RequestMapping("/house")
@Controller
public class HouseController {
	private HouseService houseService;

	@Autowired
	public void setUserService(HouseService houseService) {
		this.houseService = houseService;
	}

	@GetMapping("/search")
	private ResponseEntity<Response> getHouseList(@RequestParam String dong) throws SQLException {
		List<HouseInfo> houseList = houseService.getAddressByDong(dong);
		if (houseList.isEmpty()) {
			return Response.newError(HttpStatus.BAD_REQUEST, "올바른 검색어를 입력하세요.");
		}
		return Response.newResult(HttpStatus.OK, houseList);
	}

	@GetMapping("/detail")
	private ResponseEntity<Response> getHouseListByName(@RequestParam String aptName) throws SQLException {
		List<HouseDeal> houseList = houseService.getTransactionByName(aptName);
		if (houseList.isEmpty()) {
			return Response.newError(HttpStatus.BAD_REQUEST, "올바른 검색어를 입력하세요.");
		}
		return Response.newResult(HttpStatus.OK, houseList);
	}

	@GetMapping("/search-around")
	public ResponseEntity<Response> search(@RequestParam Double lat, @RequestParam Double lng,
			@RequestParam Integer distance) throws SQLException {
		return Response.newResult(HttpStatus.OK, houseService.searchAround(lat, lng, distance));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		return Response.newError(HttpStatus.BAD_REQUEST, name + " parameter is missing");
	}
}
