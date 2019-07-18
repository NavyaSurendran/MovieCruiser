package com.cognizant.moviecruiser.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.domain.Movie;
import com.cognizant.moviecruiser.exception.MovieAlreadyExistsException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.repo.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepo;

	@Autowired
	public MovieServiceImpl(final MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		final Optional<Movie> object = movieRepo.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieAlreadyExistsException("Could not save, movie already exsits");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie updatedMovie) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(updatedMovie.getId()).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not update, movie not found");
		}
		movie.setComments(updatedMovie.getComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found");
		}		
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {			
		movieRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepo.findByUserId(userId);
	}

}
