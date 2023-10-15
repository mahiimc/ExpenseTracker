package com.expense.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.expense.exception.ExpenseException;

import lombok.Getter;

public enum ApplicationProperties {
	
	INSTANCE;
	
	
	@Getter
	private String tokenExpiration;
	
	@Getter
	private String tokenSecret;
	
	public static ApplicationProperties getInstance() {
		return INSTANCE;
	}
	
	private ApplicationProperties() {
		Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);
		logger.info("Initializing Application Properties");
		Properties properties = new Properties();
		try(var inputStream = new ClassPathResource("application.properties").getInputStream()) {
			properties.load(new BufferedReader(new InputStreamReader(inputStream)));
			this.tokenExpiration = properties.getProperty("token.expiration");
			this.tokenSecret = properties.getProperty("token.secret");
		}
		catch (Exception e) {
			throw new ExpenseException(e.getMessage());
		}
	}
	
}
