package com.taskmanagement.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.User;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.services.UserServices;

@Service
public class UserImpl implements UserServices {

	@Autowired
	private UserRepository userRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public ResponseEntity<String> createUser(User user) {

		user.setUpdateBy("-");
		user.setCreateBy(user.getGmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User isUserSaved = userRepo.save(user);

		if (isUserSaved != null) {
			return new ResponseEntity<String>("saved", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("not saved", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<User> UserAuth(String username) {
		List<User> userData = userRepo.findByGmail(username);
		
		if(userData.size() > 0)
		{
			return new ResponseEntity<User>(userData.get(0), HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}

}
