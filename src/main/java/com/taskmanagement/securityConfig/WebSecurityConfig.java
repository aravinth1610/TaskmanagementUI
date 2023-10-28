package com.taskmanagement.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.taskmanagement.filter.CorsConfigurationFilter;
import com.taskmanagement.filter.CsrfCookieFilter;
import com.taskmanagement.filter.JWTTokenGeneratorFilter;
import com.taskmanagement.filter.JWTTokenValidatorFilter;
import com.taskmanagement.filter.RequestValidationBeforeFilter;
import com.taskmanagement.globalException.JwtAuthenticationException;

@Configuration
public class WebSecurityConfig {

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationFilter()))
				.csrf(csrfCustomizer -> csrfCustomizer.disable())
				// .csrfTokenRequestHandler(requestHandler)
				// .ignoringRequestMatchers("/user/register","/vice/createTask")
				// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
			//	.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests(request -> request
						.requestMatchers("/vice/createTask").hasAuthority("VICE")
						.requestMatchers("/user/login").authenticated().
						requestMatchers("/user/register").permitAll())
				.exceptionHandling(excpt -> excpt.authenticationEntryPoint(new JwtAuthenticationException()))
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		;

		return http.build();
	}

}
