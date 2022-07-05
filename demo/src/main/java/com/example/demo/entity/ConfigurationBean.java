package com.example.demo.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationBean {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
