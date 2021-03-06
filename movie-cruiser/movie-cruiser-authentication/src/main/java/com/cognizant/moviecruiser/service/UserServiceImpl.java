package com.cognizant.moviecruiser.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.entity.User;
import com.cognizant.moviecruiser.exception.UserAlreadyExsitsException;
import com.cognizant.moviecruiser.exception.UserNotFoundException;
import com.cognizant.moviecruiser.repo.UserRepository;
/**
 * @author Navya Surendran
 *
 */
@Service
public class UserServiceImpl implements UserService {	
	
	private UserRepository repo;	
	
	@Autowired
	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiser.service.UserService#saveUser(com.cognizant.moviecruiser.entity.User)
	 * This method will save user Entity.
	 */
	@Override
	public boolean saveUser(User user) throws UserAlreadyExsitsException, UserNotFoundException {
		Optional<User> u = repo.findById(user.getUserId());
		if(u.isPresent()) {
			throw new UserAlreadyExsitsException("User with Id already exsits");
		}
		repo.save(user);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiser.service.UserService#findByUserIdAndPassword(java.lang.String, java.lang.String)
	 * This method will retrieve user object by userId and password
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = repo.findByUserIdAndPassword(userId, password);
		if(user == null) {
			throw new UserNotFoundException("UserId and password do not match");
		}
		return user;
	}

}
