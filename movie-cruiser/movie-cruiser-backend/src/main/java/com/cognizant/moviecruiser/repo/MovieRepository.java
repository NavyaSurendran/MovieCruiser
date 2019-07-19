package com.cognizant.moviecruiser.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.moviecruiser.domain.Movie;
/**
 * @author Navya Surendran
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	List<Movie> findByUserId(String userId);
}
