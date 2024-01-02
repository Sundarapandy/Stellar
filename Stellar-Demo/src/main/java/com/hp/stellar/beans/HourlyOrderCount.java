package com.hp.stellar.beans;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HourlyOrderCount {

	@Column(name = "Time_Period")
	private String timePeriod;
	
	@Column(name = "Order_Count")
	private String orderCount;
	
	@Column(name = "Hourly_Grand_Total")
	private String hourlyGrandTotal;
	
}
