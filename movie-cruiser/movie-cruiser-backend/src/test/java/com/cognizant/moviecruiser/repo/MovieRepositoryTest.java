package com.cognizant.moviecruiser.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.moviecruiser.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private transient MovieRepository repo;

	public MovieRepository getRepo() {
		return repo;
	}

	public void setRepo(MovieRepository repo) {
		this.repo = repo;
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		repo.save(new Movie(1,123,"Matrix","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		final Movie movie = repo.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		repo.save(new Movie(1,123,"Matrix","Awesome film","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		final Movie movie = repo.getOne(1);
		assertThat(movie.getComments()).isEqualTo("Awesome film");
	}
	
	@Test
	public void testGetMovieById() throws Exception {	
		repo.save(new Movie(1,123,"Matrix","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		final Optional<Movie> movie = repo.findById(1);
		assertThat(movie.get().getId()).isEqualTo(1);
	}
	
	@Test
	public void testDeleteMovieById() throws Exception {
		repo.save(new Movie(1,123,"Matrix","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		repo.deleteById(1);
		assertThat(repo.findById(1)).isEmpty();		
	}
	
	@Test
	public void testGetAllMovies() throws Exception {		
		repo.save(new Movie(11,1323,"superman","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		repo.save(new Movie(12,12343,"Batman","Awesome movie","www.google.com",0,new Date(System.currentTimeMillis()),"ok","jon"));
		List<Movie> movies = repo.findByUserId("jon");
		assertThat(movies.size()).isGreaterThan(1);		
	}
	
}
