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
public class StockVisit {

	@Column(name = "SKU")
	private String sku;
	
	@Column(name = "QUANTITY")
	private String quantity;
	
	@Column(name = "INCREMENT_ID")
	private String increment_id;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "MAGENTO_INVENTORY")
	private String Magento_inventory;
}
