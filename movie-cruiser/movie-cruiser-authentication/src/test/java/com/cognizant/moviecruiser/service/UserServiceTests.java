package com.cognizant.moviecruiser.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.moviecruiser.entity.User;
import com.cognizant.moviecruiser.exception.UserAlreadyExsitsException;
import com.cognizant.moviecruiser.exception.UserNotFoundException;
import com.cognizant.moviecruiser.repo.UserRepository;

public class UserServiceTests {

	@Mock
	private transient UserRepository repo;
	
	private transient User user;
	
	@InjectMocks
	private transient UserServiceImpl service;
	
	transient Optional<User> options;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User("jon", "John", "Doe", "password", new Date());		
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExsitsException, UserNotFoundException {
		when(repo.save(user)).thenReturn(user);
		final boolean flag = service.saveUser(user);
		assertTrue("Cannot save user", flag);
		verify(repo,times(1)).save(user);	
		verify(repo,times(1)).findById(user.getUserId());			
	}
	
	@Test(expected =UserAlreadyExsitsException.class)
	public void testSaveMovieFailure() throws UserAlreadyExsitsException, UserNotFoundException {
		when(repo.findById(user.getUserId())).thenReturn(options);
		when(repo.save(user)).thenReturn(user);
		final boolean flag = service.saveUser(user);
		assertFalse("Saving movie failed",flag);		
		verify(repo,times(1)).findById(user.getUserId());			
	}
	
	@Test
	public void testLoginSuccess() throws  UserNotFoundException {
		when(repo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		final User u = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(u);
		assertTrue("Cannot login  user", u.getUserId().equals(user.getUserId()));		
		verify(repo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());			
	}
	
	
	@Test(expected = UserNotFoundException.class)
	public void testLoginFailure() throws  UserNotFoundException {
		when(repo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(null);
		final User u = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(u);
		assertTrue("Cannot login  user", u.getUserId().equals(user.getUserId()));		
		verify(repo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());			
	}
	
}
