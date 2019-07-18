package com.cognizant.moviecruiser.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.moviecruiser.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class UserRepositoryTests {
	
	@Autowired
	private transient UserRepository repo;
	
	private User user;
	
	public UserRepositoryTests () {}
	
	@Before
	public void setup() throws Exception {
		user = new User("jon", "John", "Doe", "password", new Date());		
	}

	@Test
	public void registerUserTest() {
		repo.save(user);
		Optional<User> u = repo.findById(user.getUserId());
		assertThat(u.equals(user));
	}
	
	@Test
	public void loginUserTest() {
		repo.save(user);
		User u = repo.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertThat(u.equals(user));
	}
	
}
