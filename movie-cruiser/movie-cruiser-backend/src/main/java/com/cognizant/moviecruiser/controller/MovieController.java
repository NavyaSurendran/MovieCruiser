package com.cognizant.moviecruiser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviecruiser.domain.Movie;
import com.cognizant.moviecruiser.exception.MovieAlreadyExistsException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.service.MovieService;

import io.jsonwebtoken.Jwts;
/**
 * @author Navya Surendran
 *
 */
@RestController
@RequestMapping(path="/api/v1/movie")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class MovieController {
	
	private MovieService movieService;

	@Autowired
	public MovieController(final MovieService movieService) {		
		this.movieService = movieService;
	}
	/**
	 * @param movie
	 * @param request
	 * @param response
	 * @return
	 * Rest End point for saving new movie
	 */
	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId =Jwts.parser()
							.setSigningKey("secretKey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		System.out.println("UserId: " + userId);
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	/**
	 * @param id
	 * @param movie
	 * @return
	 * Rest Api end point for updating movie details
	 */
	@PutMapping(path="/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id")final int id, @RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {		
			final Movie fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
		} catch ( MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;		
	}
	
	/**
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * Rest Api end point for deleting a particular Movie
	 */
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id")final int id,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);		
			responseEntity = new ResponseEntity<String>("{\"message\":\"Movie deleted successfully\"}",HttpStatus.OK);
		} catch ( MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
		}		
		return responseEntity;		
	}
	
	/**
	 * @param id
	 * @return
	 * This service will return a movie when given with a movie ID
	 */
	@GetMapping(path="/{id}")
	public ResponseEntity<?> fetchMovie(@PathVariable("id")final int id) {
		ResponseEntity<?> responseEntity;
		try {
			final Movie fetchedMovie = movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
		} catch ( MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
		}
		return responseEntity;		
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * This method will retrive All Movies
	 */
	@GetMapping
	public ResponseEntity<?> fetchMylMovies(HttpServletRequest request, HttpServletResponse response) {		
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId =Jwts.parser()
							.setSigningKey("secretKey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		final List<Movie> allMovies = movieService.getMyMovies(userId);	
		return new ResponseEntity<List<Movie>>(allMovies,HttpStatus.OK);		
	}

}
