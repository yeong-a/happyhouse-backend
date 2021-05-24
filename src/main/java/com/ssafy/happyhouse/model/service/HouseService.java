package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseService {

	List<HouseInfo> getAllAddress() throws SQLException;

	List<HouseInfo> getAddressByDong(String dong) throws SQLException;

	List<HouseInfo> getAddressByName(String aptName) throws SQLException;

	List<HouseDeal> getAllTransaction() throws SQLException;

	List<HouseDeal> getTransactionByDong(String dong) throws SQLException;

	List<HouseDeal> getTransactionByName(String aptName) throws SQLException;

	List<HouseInfo> searchAround(double lat, double lng, int distance) throws SQLException;
}
