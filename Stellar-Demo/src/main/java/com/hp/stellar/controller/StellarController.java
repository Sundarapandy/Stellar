package com.hp.stellar.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hp.stellar.beans.HourlyOrderCount;
import com.hp.stellar.beans.RecycleFee;
import com.hp.stellar.beans.StockVisit;
import com.hp.stellar.service.StellarService;

@RestController
public class StellarController {
	
	String className = this.getClass().getName();
	private final Logger log = LoggerFactory.getLogger(className);
	
	@Autowired
	StellarService service;
	
	@GetMapping("/test")
	public String test() {
		return "success";
	}

	@RequestMapping("/unauthorised")
	public String unauthorised() {
		String functionName = "unauthorised";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.trace("EXIT function : " + className + "." + functionName);
		return "Unauthorised";
	}

	@RequestMapping("/user_info")
	public String homePage(Principal principal) {
		String functionName = "homePage";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.trace("EXIT function : " + className + "." + functionName);
		return principal.getName();
	}
	
	@GetMapping("/getStockUnit")
	public List<StockVisit> getStockUnit(@RequestParam(name = "country",required = true)String country) {	
		String functionName = "getStockUnit";
		log.trace("ENTRY function : " + className + "." + functionName);
		List<StockVisit> stockVisitList = service.getStockVisit(country.toLowerCase());
		log.trace("EXIT function : " + className + "." + functionName);

		return stockVisitList;
	}
	
	@GetMapping("/getRecycleFee")
	public List<RecycleFee> getRecycleFee(@RequestParam(name = "country",required = true)String country) {	
		String functionName = "getRecycleFee";
		log.trace("ENTRY function : " + className + "." + functionName);
		List<RecycleFee> recycleFeeList = service.getRecycleFee(country.toLowerCase());
		log.trace("EXIT function : " + className + "." + functionName);

		return recycleFeeList;
	}
	
	@GetMapping("/getHourlyOrder")
	public List<HourlyOrderCount> getHourlyOrder(@RequestParam(name = "country",required = true)String country,@RequestParam(name = "date",required = true)String date ) {
		String functionName = "getHourlyOrder";
		log.trace("ENTRY function : " + className + "." + functionName);
		
		List<HourlyOrderCount> hourlyOrderCountList = service.getHourlyOrderCount(country.toLowerCase(), date);
		log.trace("EXIT function : " + className + "." + functionName);

		return hourlyOrderCountList;
	}
}
