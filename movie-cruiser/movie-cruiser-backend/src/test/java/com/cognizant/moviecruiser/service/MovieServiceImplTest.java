package com.cognizant.moviecruiser.service;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.moviecruiser.domain.Movie;
import com.cognizant.moviecruiser.exception.MovieAlreadyExistsException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.repo.MovieRepository;
import com.cognizant.moviecruiser.service.MovieServiceImpl;
		



public class MovieServiceImplTest {
	
	@Mock
	private transient MovieRepository repo;
	
	private transient Movie movie;
	
	@InjectMocks
	private MovieServiceImpl service;
	
	transient Optional<Movie> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,123,"Matrix","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon");
		options = Optional.of(movie);
	}
	
	
	@Test
	public void testMockCreation() {
		assertNotNull("JPA repo creation failed", movie);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException{
		when(repo.save(movie)).thenReturn(movie);
		final boolean flag = service.saveMovie(movie);
		assertTrue("Saving movie success", flag);
		verify(repo,times(1)).save(movie);	
		verify(repo,times(1)).findById(movie.getId());			
	}
	
	@Test(expected =MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException{
		when(repo.findById(1)).thenReturn(options);
		when(repo.save(movie)).thenReturn(movie);
		final boolean flag = service.saveMovie(movie);
		assertFalse("Saving movie failed",flag);		
		verify(repo,times(1)).findById(movie.getId());			
	}
	
	@Test
	public void testUpdateMovie() throws MovieNotFoundException{
		when(repo.findById(1)).thenReturn(options);
		when(repo.save(movie)).thenReturn(movie);
		movie.setComments("Awesome");
		final Movie mv = service.updateMovie(movie);
		assertEquals("Updating movie failed", mv.getComments(), "Awesome");				
		verify(repo,times(1)).findById(movie.getId());		
		verify(repo,times(1)).save(movie);		
	}
	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException{
		when(repo.findById(1)).thenReturn(options);
		doNothing().when(repo).delete(movie);
		movie.setComments("Awesome");
		final boolean flag = service.deleteMovieById(movie.getId());
		assertTrue("Deleting movie success", flag);							
	}
	
	@Test
	public void testgetMovieById() throws MovieNotFoundException{
		when(repo.findById(movie.getId())).thenReturn(options);		
		final Movie mv = service.getMovieById(movie.getId());
		assertEquals("fetching movie by id failed",movie.getId(), mv.getId());		
		verify(repo,times(1)).findById(movie.getId());		
	}
	
	@Test
	public void testgetAllMovies() {
		List<Movie> movies = new ArrayList<>();
		movies.add(options.get());
		when(repo.findByUserId("jon")).thenReturn(movies);		
		List<Movie> mvs = service.getMyMovies("jon");
		assertEquals("fetching all movies  failed",movies.size(), mvs.size());		
		verify(repo,times(1)).findByUserId("jon");		
	}
	
}
