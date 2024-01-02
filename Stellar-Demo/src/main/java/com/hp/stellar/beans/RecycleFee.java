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
public class RecycleFee {

	@Column(name = "sku")
	private String sku;
	
	@Column(name = "hp_fpt_battery_fee")
	private String hpFptBatteryFee;
	
	@Column(name = "hp_fpt_recycling_fee")
	private String hpFptRecyclingFee;
	
	@Column(name = "hp_fpt_copyright_fee")
	private String hpFptCopyrightFee;
}
