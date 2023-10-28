package com.taskmanagement.filter;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

public class CorsConfigurationFilter implements CorsConfigurationSource {

	private final String GET = "GET";
	private final String POST = "POST";
	private final String PUT = "PUT";
	private final String DELETE = "DELETE";

	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowCredentials(true); // "Authorization","x-requested-with","x-xsrf-token","content-type"
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setExposedHeaders(Arrays.asList("Authorization"));// Access-Control-Allow-Origin","Access-Control-Allow-Credentials
		config.setMaxAge(3600L);
		return config;

	}

}
