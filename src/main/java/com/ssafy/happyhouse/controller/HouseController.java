package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.House;
import com.ssafy.happyhouse.model.service.HouseService;

@RequestMapping("/house")
@Controller
public class HouseController {
	private HouseService houseService;

	@Autowired
	public void setUserService(HouseService houseService) {
		this.houseService = houseService;
	}

	@GetMapping("/detail")
	private ResponseEntity<List<House>> getHouseListByName(@RequestParam String aptName) throws SQLException {
		List<House> houseList = houseService.getHouseListByName(aptName);
		return new ResponseEntity<List<House>>(houseList, HttpStatus.OK);
	}

	@GetMapping("/search")
	private ResponseEntity<List<House>> getHouseList(@RequestParam String sido, @RequestParam String gugun,
			@RequestParam String dong, @RequestParam String type, HttpServletRequest request) throws SQLException {
		List<House> houseList = houseService.getHouseList(dong);
		return new ResponseEntity<List<House>>(houseList, HttpStatus.OK);
	}
}
