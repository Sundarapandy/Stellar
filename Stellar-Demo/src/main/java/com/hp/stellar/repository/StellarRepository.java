package com.hp.stellar.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StellarRepository {

	String className = this.getClass().getName();
	private final Logger log = LoggerFactory.getLogger(className);
	
	@Autowired
	private Environment env;
	
	private Connection connection;
	
	public void getConnection(String country) {
		try {
			
			String prefix = "spring." + country + ".datasource.";
			String url = prefix + "url";
			String username = prefix + "username";
			String password = prefix + "password";
			connection = DriverManager.getConnection(env.getProperty(url),
					env.getProperty(username), env.getProperty(password));
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String country,String sql,String[] params) {
		
		String functionName = "executeQuery";
		log.trace("ENTRY function : " + className + "." + functionName);
		ResultSet result = null;
		try {
			getConnection(country);
			PreparedStatement statement = connection.prepareStatement(sql);
			if(params!=null)
				for(int i=0;i<params.length;i++)
					statement.setString(i+1, params[i]);
			result = statement.executeQuery();
		}
		catch(Exception ex) {
			log.error("ERROR : " + ex.getLocalizedMessage()+ " at " + className + "." + functionName);
		}
		log.trace("EXIT function : " + className + "." + functionName);
		return result;
	}
}
