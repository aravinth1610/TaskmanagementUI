package com.taskmanagement.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {

	public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	private String username = null;
	private String authorities = null;
	private Date expiration = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("---v");
		String requestJWTToken = request.getHeader("Authorization");
		System.out.println(requestJWTToken+"--");
		if (requestJWTToken != null) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(requestJWTToken).getBody();
				this.username = String.valueOf(claims.get("username"));
				this.authorities = (String) claims.get("authorities");
				this.expiration = (Date) claims.getExpiration();
			} catch (IllegalArgumentException e) {
				throw new BadCredentialsException("Invalid Token received!");
			} catch (ExpiredJwtException e) {
				throw new BadCredentialsException("Credentials is Exceptions!");
			}
		}

		if (this.username != null && this.authorities != null && !this.expiration.before(new Date())) {
			Authentication auth = new UsernamePasswordAuthenticationToken(this.username, null,
					AuthorityUtils.commaSeparatedStringToAuthorityList(this.authorities));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
System.out.println("not--v");
		boolean isExists = request.getServletPath().equals("/user/login")
				|| request.getServletPath().equals("/user/register");
		return isExists;
	}

}
