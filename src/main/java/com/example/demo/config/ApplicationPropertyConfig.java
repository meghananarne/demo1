package com.example.demo.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ConfigurationProperties(prefix = "applicationProperties")
public class ApplicationPropertyConfig {

	private Map<String, String> kafkaproducerpropmap;

	public Map<String, String> getKafkaproducerpropmap() {
		return kafkaproducerpropmap;
	}

	public void setKafkaproducerpropmap(Map<String, String> kafkaproducerpropmap) {
		this.kafkaproducerpropmap = kafkaproducerpropmap;
	}

}
