package com.cognizant.moviecruiser.service;

import java.util.List;

import com.cognizant.moviecruiser.domain.Movie;
import com.cognizant.moviecruiser.exception.MovieAlreadyExistsException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;

public interface MovieService {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	Movie getMovieById(int id) throws MovieNotFoundException;
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	List<Movie> getMyMovies(String userId);
	
}
