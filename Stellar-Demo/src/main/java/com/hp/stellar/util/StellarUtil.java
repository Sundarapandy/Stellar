package com.hp.stellar.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.stellar.beans.HourlyOrderCount;
import com.hp.stellar.beans.RecycleFee;
import com.hp.stellar.beans.StockVisit;
import com.hp.stellar.repository.StellarRepository;

@Service
public class StellarUtil {

	String className = this.getClass().getName();
	private final Logger log = LoggerFactory.getLogger(className);
	
	@Autowired
	StellarRepository repo;
	
	private static final String STOCK_VISIT_QUERY = "SELECT DISTINCT ir.sku, ir.quantity, JSON_UNQUOTE(JSON_EXTRACT(ir.metadata, '$.object_increment_id')) AS increment_id, so.state, so.status, isi.quantity AS Magento_inventory FROM inventory_reservation AS ir JOIN sales_order AS so ON JSON_UNQUOTE(JSON_EXTRACT(ir.metadata, '$.object_increment_id')) = so.increment_id JOIN inventory_source_item AS isi ON ir.sku = isi.sku";
	private static final String HOURLY_ORDER_COUNT_QUERY = "SELECT DATE_FORMAT(CONVERT_TZ(`Created_at`, 'UTC', 'CET'), '%Y-%m-%d %H:00:00') AS Time_Period, COUNT(*) AS Order_Count, SUM(Grand_total) AS Hourly_Grand_Total FROM sales_order_grid WHERE DATE(CONVERT_TZ(`Created_at`, 'UTC', 'CET')) = ? and status in ('closed','complete','dropped_to_lsp','erp_hold','erp_order_clean','erp_submission_issue','out_for_delivery','payment_review','pending','pending_payment','processing','processing_payment') GROUP BY Time_Period ORDER BY Time_Period";
	private static final String RECYCLE_FEE_QUERY = "SELECT cpe.sku,cpev.value as hp_fpt_battery_fee,cpeb.value as hp_fpt_recycling_fee,cpec.value as hp_fpt_copyright_fee FROM `catalog_product_entity` as cpe LEFT JOIN `catalog_product_entity_varchar` as cpev on cpe.entity_id = cpev.row_id AND cpev.`attribute_id` in (SELECT attribute_id FROM eav_attribute WHERE attribute_code = 'hp_fpt_battery_fee') LEFT JOIN `catalog_product_entity_varchar` as cpeb on cpe.entity_id = cpeb.row_id AND cpeb.`attribute_id` in (SELECT attribute_id FROM eav_attribute WHERE attribute_code = 'hp_fpt_recycling_fee') LEFT JOIN `catalog_product_entity_varchar` as cpec on cpe.entity_id = cpec.row_id AND cpec.`attribute_id` in (SELECT attribute_id FROM eav_attribute WHERE attribute_code = 'hp_fpt_copyright_fee')";
	
	public List<StockVisit> getStockVisit(String country) {
		
		String functionName = "getStockVisit";
		log.trace("ENTRY function : " + className + "." + functionName);
		
		List<StockVisit> stockList = new ArrayList<>();
		try {
			ResultSet result = repo.executeQuery(country, STOCK_VISIT_QUERY,null);
			while(result.next()) {
				StockVisit stock = new StockVisit();
				stock.setSku(result.getString("sku"));
				stock.setQuantity(result.getString("quantity"));
				stock.setIncrement_id(result.getString("increment_id"));
				stock.setMagento_inventory(result.getString("Magento_inventory"));
				stock.setState(result.getString("state"));
				stock.setStatus(result.getString("status"));
				
				stockList.add(stock);
			}
		}
		catch(Exception ex) {
			log.error("ERROR : " + ex.getLocalizedMessage()+ " at " + className + "." + functionName);
		}
		log.trace("EXIT function : " + className + "." + functionName);
		return stockList;	
	}
	
	public List<HourlyOrderCount> getHourlyOrderCount(String country,String date) {
		
		String functionName = "getHourlyOrderCount";
		log.trace("ENTRY function : " + className + "." + functionName);
		
		List<HourlyOrderCount> orderCountList = new ArrayList<>();
		String[] params = {date};
		try {
			ResultSet result = repo.executeQuery(country, HOURLY_ORDER_COUNT_QUERY,params);
			while(result.next()) {
				HourlyOrderCount ordercount = new HourlyOrderCount();
				ordercount.setTimePeriod(result.getString("Time_Period"));
				ordercount.setHourlyGrandTotal(result.getString("Hourly_Grand_Total"));
				ordercount.setOrderCount(result.getString("Order_Count"));
		
				orderCountList.add(ordercount);
			}
		}
		catch(Exception ex) {
			log.error("ERROR : " + ex.getLocalizedMessage()+ " at " + className + "." + functionName);
		}
		log.trace("EXIT function : " + className + "." + functionName);
		return orderCountList;	
	}
	
	public List<RecycleFee> getRecycleFee(String country) {
		
		String functionName = "getRecycleFee";
		log.trace("ENTRY function : " + className + "." + functionName);
		
		List<RecycleFee> recycleFeeList = new ArrayList<>();
		
		try {
			ResultSet result = repo.executeQuery(country, RECYCLE_FEE_QUERY,null);
			while(result.next()) {
				RecycleFee recycleFee = new RecycleFee();
				recycleFee.setSku(result.getString("sku"));
				recycleFee.setHpFptBatteryFee(result.getString("hp_fpt_battery_fee"));;
				recycleFee.setHpFptCopyrightFee(result.getString("hp_fpt_copyright_fee"));
				recycleFee.setHpFptRecyclingFee(result.getString("hp_fpt_recycling_fee"));
		
				recycleFeeList.add(recycleFee);
			}
		}
		catch(Exception ex) {
			log.error("ERROR : " + ex.getLocalizedMessage()+ " at " + className + "." + functionName);
		}
		log.trace("EXIT function : " + className + "." + functionName);
		return recycleFeeList;	
	}
}
