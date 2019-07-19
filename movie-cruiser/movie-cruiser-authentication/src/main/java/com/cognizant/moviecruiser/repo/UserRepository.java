package com.cognizant.moviecruiser.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.moviecruiser.entity.User;
/**
 * @author Navya Surendran
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserIdAndPassword(String userId, String password);

}
