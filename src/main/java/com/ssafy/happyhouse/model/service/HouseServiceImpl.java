package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.HouseDAO;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseDAO houseDAO;

	@Autowired
	public void setUserDAO(HouseDAO houseDAO) {
		this.houseDAO = houseDAO;
	}

	@Override
	public List<HouseInfo> getAllAddress() throws SQLException {
		return houseDAO.selectAllAddress();
	}

	@Override
	public List<HouseInfo> getAddressByDong(String dong) throws SQLException {
		return houseDAO.selectAddressByDong(dong);
	}

	@Override
	public List<HouseInfo> getAddressByName(String aptName) throws SQLException {
		return houseDAO.selectAddressByName(aptName);
	}

	@Override
	public List<HouseDeal> getAllTransaction() throws SQLException {
		return houseDAO.selectAllTransaction();
	}

	@Override
	public List<HouseDeal> getTransactionByDong(String dong) throws SQLException {
		return houseDAO.selectTransactionByDong(dong);
	}

	@Override
	public List<HouseDeal> getTransactionByName(String aptName) throws SQLException {
		return houseDAO.selectTransactionByName(aptName);
	}

	@Override
	public List<HouseInfo> searchAround(double lat, double lng, int distance) throws SQLException {
		List<HouseInfo> houses = getAllAddress();
		List<HouseInfo> result = new ArrayList<>();
		for (HouseInfo house : houses) {
			if (distance >= getDistance(lat, lng, Double.parseDouble(house.getLat()),
					Double.parseDouble(house.getLng()))) {
				result.add(house);
			}
		}
		return result;
	}

	private double getDistance(double startLat, double startLong, double endLat, double endLong) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return 6371000 * c;
	}

	private static double haversine(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
}
