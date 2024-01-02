package com.hp.stellar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

	String className = this.getClass().getName();
	private final Logger log = LoggerFactory.getLogger(className);
	
	@RequestMapping("/")
	public String homeRedirect() {
		String functionName = "homeRedirect";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.trace("Redirecting to homePage");
		log.trace("EXIT function : " + className + "." + functionName);
		return "redirect:home";
	}
	
	@RequestMapping("/home")
	public String homePage() {
		String functionName = "homePage";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.info("Redirecting to Homepage.jsp");
		log.trace("EXIT function : " + className + "." + functionName);
		return "Homepage";
	}
	
	@RequestMapping("/HourlyCountDashboard")
	public String HourlyCountDashboard() {
		String functionName = "HourlyCountDashboard";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.info("Redirecting to HourlyCountDashboard.html");
		log.trace("EXIT function : " + className + "." + functionName);
		return "HourlyCountDashboard";
	}
	
	@RequestMapping("/InventoryReservationDashboard")
	public String InventoryReservationDashboard() {
		String functionName = "HourlyCountDashboard";
		log.trace("ENTRY function : " + className + "." + functionName);
		log.info("Redirecting to HourlyCountDashboard.html");
		log.trace("EXIT function : " + className + "." + functionName);
		return "InventoryReservationDashboard";
	}
}
