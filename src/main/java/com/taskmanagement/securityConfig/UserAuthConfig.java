package com.taskmanagement.securityConfig;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.taskmanagement.entity.Authority;
import com.taskmanagement.entity.User;
import com.taskmanagement.repository.UserRepository;

@Component
public class UserAuthConfig implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepo;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private String username = "";
	private String password = "";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		username = authentication.getName();
		password = authentication.getCredentials().toString();

		if (!username.isEmpty() && !password.isEmpty()) {
			List<User> user = userRepo.findByGmail(username);

			if (user != null) {
				if (passwordEncoder.matches(password, user.get(0).getPassword())
						&& username.equals(user.get(0).getGmail())) {
					return new UsernamePasswordAuthenticationToken(username, password,
							getGrantedAuthorities(user.get(0).getAuthority()));
				} else {
					throw new BadCredentialsException("Invalid password!");
				}
			} else {
				throw new BadCredentialsException("No user registered with this details!");
			}
		} else {
			throw new BadCredentialsException("No user registered with this details!");
		}

	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
		for (Authority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
