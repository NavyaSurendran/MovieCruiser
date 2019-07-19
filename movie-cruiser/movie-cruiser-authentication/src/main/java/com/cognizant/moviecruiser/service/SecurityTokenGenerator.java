package com.cognizant.moviecruiser.service;

import java.util.Map;

import com.cognizant.moviecruiser.entity.User;

/**
 * @author Navya Surendran
 *
 */
public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
