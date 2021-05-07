package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dao.HouseDaoImpl;
import com.ssafy.happyhouse.model.dto.House;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public class HouseServiceImpl implements HouseService{
	private HouseDao houseDao = new HouseDaoImpl();
	
	// 동별 거래정보 조회
	@Override
	public List<House> getHouseList(String dong) throws SQLException {
		ArrayList<HouseDeal> houseDeal = (ArrayList<HouseDeal>) houseDao.selectTransaction(dong);
		ArrayList<HouseInfo> houseInfo = (ArrayList<HouseInfo>) houseDao.selectAddress(dong);
		ArrayList<House> list = new ArrayList<>();
		
		for(HouseDeal hd : houseDeal) {
			String lat = null, lng = null;
			for(HouseInfo hi : houseInfo) {
				if(hd.getAptName().equals(hi.getAptName())) {
					lat = hi.getLat();
					lng = hi.getLng();
					break;
				}
			}
			list.add(new House(hd.getDong(), hd.getAptName(), hd.getDealAmount(), hd.getBuildYear(), hd.getDealYear(), hd.getDealMonth(), hd.getDealDay(), hd.getArea(), hd.getFloor(), hd.getJibun(), hd.getType(), lat, lng));
		}
		
		return list;
	}

	@Override
	public List<House> getHouseListByName(String aptName) throws SQLException {
		ArrayList<HouseDeal> houseDeal = (ArrayList<HouseDeal>) houseDao.selectTransactionByName(aptName);
		ArrayList<HouseInfo> houseInfo = (ArrayList<HouseInfo>) houseDao.selectAddressByName(aptName);
		ArrayList<House> list = new ArrayList<>();
		
		for(HouseDeal hd : houseDeal) {
			String lat = null, lng = null;
			for(HouseInfo hi : houseInfo) {
				if(hd.getAptName().equals(hi.getAptName())) {
					lat = hi.getLat();
					lng = hi.getLng();
					break;
				}
			}
			list.add(new House(hd.getDong(), hd.getAptName(), hd.getDealAmount(), hd.getBuildYear(), hd.getDealYear(), hd.getDealMonth(), hd.getDealDay(), hd.getArea(), hd.getFloor(), hd.getJibun(), hd.getType(), lat, lng));
		}
		
		return list;
	}
}
