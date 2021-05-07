package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.House;

public interface HouseService {
	// 동별 거래정보 조회
	List<House> getHouseList(String dong) throws SQLException;

	// 아파트명으로 거래정보 조회
	List<House> getHouseListByName(String aptName) throws SQLException;
}
