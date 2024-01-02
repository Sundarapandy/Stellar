package com.hp.stellar.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.stellar.beans.HourlyOrderCount;
import com.hp.stellar.beans.RecycleFee;
import com.hp.stellar.beans.StockVisit;
import com.hp.stellar.util.StellarUtil;

@Service
public class StellarService {

	String className = this.getClass().getName();
	private final Logger log = LoggerFactory.getLogger(className);
	
	@Autowired
	StellarUtil util;
	
	public List<StockVisit> getStockVisit(String country) {
		String functionName = "getStockVisit";
		log.trace("ENTRY function : " + className + "." + functionName);
		List<StockVisit> stockVisitList = util.getStockVisit(country);
		log.trace("EXIT function : " + className + "." + functionName);

		return stockVisitList;
	}
	
	public List<HourlyOrderCount> getHourlyOrderCount(String country,String date) {
		String functionName = "getHourlyOrderCount";
		log.trace("ENTRY function : " + className + "." + functionName);
		
		List<HourlyOrderCount> hourlyOrderCountList = util.getHourlyOrderCount(country, date);
		log.trace("EXIT function : " + className + "." + functionName);

		return hourlyOrderCountList;
	}

	public List<RecycleFee> getRecycleFee(String country) {

		String functionName = "getRecycleFee";
		log.trace("ENTRY function : " + className + "." + functionName);
		List<RecycleFee> recycleFeeList = util.getRecycleFee(country);
		log.trace("EXIT function : " + className + "." + functionName);

		return recycleFeeList;
	}
}
