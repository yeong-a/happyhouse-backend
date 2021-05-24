package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

@Mapper
@Repository
public interface HouseDAO {
	List<HouseDeal> selectTransaction(String dong) throws SQLException;

	List<HouseDeal> selectTransactionByName(String aptName) throws SQLException;

	List<HouseInfo> selectAddress(String dong) throws SQLException;

	List<HouseInfo> selectAddressByName(String aptName) throws SQLException;
}
