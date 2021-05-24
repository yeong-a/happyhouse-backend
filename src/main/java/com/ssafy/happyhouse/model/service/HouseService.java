package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.House;

public interface HouseService {
	List<House> getHouseList(String dong) throws SQLException;

	List<House> getHouseListByName(String aptName) throws SQLException;
}
