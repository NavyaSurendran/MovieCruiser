package com.cognizant.moviecruiser.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Navya Surendran
 *
 */
@Entity
@Table(name="moviedb")
public class Movie {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "movie_id")
	private int movie_id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "poster_path")
	private String poster_path;
	
	@Column(name = "vote_count")
	private int vote_count;
	
	@Column(name = "release_date")
	private Date release_date;
	
	@Column(name = "overview" , length=2500)
	private String overview;
	
	@Column(name = "user_id")
	private String userId;
	
	public Movie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Movie(int id, int movie_id, String title, String comments, String poster_path, int vote_count,
			Date release_date, String overview, String userId) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.vote_count = vote_count;
		this.release_date = release_date;
		this.overview = overview;
		this.userId = userId;
	}
	
	
	
}
