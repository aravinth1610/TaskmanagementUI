package com.taskmanagement.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationBeforeFilter implements Filter {

	private final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private final Charset credentialsCharset = StandardCharsets.UTF_8;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-----1");
		 HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        String header = req.getHeader(AUTHORIZATION);
	        System.out.println(header);
	        if (header != null) {
	            header = header.trim();
	            System.out.println(StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC));
	            if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
	                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
	                byte[] decoded;
	                try {
	                    decoded = Base64.getDecoder().decode(base64Token);
	                    String token = new String(decoded, credentialsCharset);
	                    int delim = token.indexOf(":");
	                    System.out.println(delim);
	                    if (delim == -1) {
	                        throw new BadCredentialsException("Invalid basic authentication token");
	                    }
	                    String email = token.substring(0, delim);
	                    if (email.toLowerCase().contains("test")) {
	                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                        return;
	                    }
	                } catch (IllegalArgumentException e) {
	                	System.out.println("fail");
	                    throw new BadCredentialsException("Failed to decode basic authentication token");
	                }
	            }
	        }
	        chain.doFilter(request, response);
	    }
	}
