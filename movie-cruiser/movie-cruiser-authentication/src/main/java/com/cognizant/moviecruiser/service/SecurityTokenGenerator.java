package com.cognizant.moviecruiser.service;

import java.util.Map;

import com.cognizant.moviecruiser.entity.User;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
