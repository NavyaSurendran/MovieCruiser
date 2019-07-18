package com.cognizant.moviecruiser.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.moviecruiser.domain.Movie;
import com.cognizant.moviecruiser.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;





@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient MovieService service;
	
	@InjectMocks
	private MovieController controller;
	
	private Movie movie;
	
	static List<Movie> movies;	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		movies = new ArrayList<>();
		movie = new Movie(11,1323,"superman","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon");
		movies.add(movie);
		movie = new Movie(12,12343,"Batman","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon");
		movies.add(movie);
		movie = new Movie(13,123343,"Mario","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon");
		movies.add(movie);
	}
	
	@Test
	public void saveNewMovieTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb24iLCJpYXQiOjE1MzYxNTE3NDV9.BBMhGNYi2k04soJuPw8LmT4nzhGtIzkRq3bM6n_JvxI";
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/v1/movie")
				.header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
			.andExpect(status().isCreated());
		verify(service, times(1)).saveMovie(Mockito.any(Movie.class));		
	}
	
	@Test
	public void updateMovieTest() throws Exception {
		movie.setComments("bad movie");
		when(service.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/v1/movie/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
			.andExpect(status().isOk());
		verify(service, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
		
	}
	
	@Test
	public void deleteMovieByIdTest() throws Exception {
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/movie/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
			.andExpect(status().isOk());
		verify(service, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void getMovieByIdTest() throws Exception {
		when(service.getMovieById(1)).thenReturn(movie);
		mvc.perform(get("/api/v1/movie/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
			.andExpect(status().isOk());
		verify(service, times(1)).getMovieById(1);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void getMyMoviesTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb24iLCJpYXQiOjE1MzYxNTE3NDV9.BBMhGNYi2k04soJuPw8LmT4nzhGtIzkRq3bM6n_JvxI";
		when(service.getMyMovies("jon")).thenReturn(movies);
		mvc.perform(get("/api/v1/movie/")
				.header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		verify(service, times(1)).getMyMovies("jon");
		verifyNoMoreInteractions(service);
	}
	
	private static String jsonToString(final Object obj) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
			} catch (JsonProcessingException e) {
				result = "JSON Parsing error";				
			}
		return result;
	}
}
