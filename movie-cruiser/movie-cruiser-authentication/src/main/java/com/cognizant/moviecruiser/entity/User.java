package com.cognizant.moviecruiser.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
/**
 * @author Navya Surendran
 *
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "password")
	private String password;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Date created;
	
	public User() {}

	public User(String userId, String firstname, String lastname, String password, Date created) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.created = created;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
