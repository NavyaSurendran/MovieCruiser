package com.cognizant.moviecruiser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cognizant.moviecruiser.entity.User;
import com.cognizant.moviecruiser.service.SecurityTokenGenerator;
import com.cognizant.moviecruiser.service.UserService;

/**
 * @author Navya Surendran
 *
 */
@RestController
@EnableWebMvc
@RequestMapping(path="api/v1/user")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class UserController {

	private UserService userService;
	
	private SecurityTokenGenerator tokenGenerator;

	@Autowired
	public UserController(UserService userService, SecurityTokenGenerator tokenGenerator) {
		super();
		this.userService = userService;
		this.tokenGenerator = tokenGenerator;
	}
	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path="/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("user registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
	}
	/**
	 * @param loginDetails
	 * @return
	 */
	@PostMapping(path="/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
		try {
			String userId = loginDetails.getUserId();
			String password = loginDetails.getPassword();
			
			if(userId == null || password == null) {
				throw new Exception("Username or password cannot be empty");
			}
			
			User user = userService.findByUserIdAndPassword(userId, password);
			if(user == null ) {
				throw new Exception("User with the given Id does not exsit");
			}
			
			String pwd = user.getPassword();
			if (!pwd.equals(password)) {
				throw new Exception("Invalid login credentials, please check username and password");
			}
			
			Map<String, String> map = tokenGenerator.generateToken(user);			
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
		}
	}
	
	
}
