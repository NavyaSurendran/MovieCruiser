package com.cognizant.moviecruiser.service;

import com.cognizant.moviecruiser.entity.User;
import com.cognizant.moviecruiser.exception.UserAlreadyExsitsException;
import com.cognizant.moviecruiser.exception.UserNotFoundException;

public interface UserService {
	
	boolean saveUser (User user) throws UserAlreadyExsitsException, UserNotFoundException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

}
