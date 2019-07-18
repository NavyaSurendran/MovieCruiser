package com.cognizant.moviecruiser.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.cognizant.moviecruiser.entity.User;
import com.cognizant.moviecruiser.service.SecurityTokenGenerator;
import com.cognizant.moviecruiser.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
	
	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient UserService service;
	
	@MockBean
	private transient SecurityTokenGenerator securityTokenGenerator;
	
	private transient User user;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User("jon", "John", "Doe", "password", new Date());		
	}
	
	
	@Test
	public void registerUserTest() throws Exception {
		when(service.saveUser(user)).thenReturn(true);
		mvc.perform(post("/api/v1/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
			.andExpect(status().isCreated());
		verify(service, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(service);		
	}
	
	@Test
	public void loginUserTest() throws Exception {
		String userId ="jon";
		String password = "password";
		when(service.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		mvc.perform(post("/api/v1/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
			.andExpect(status().isOk());
		verify(service, times(1)).findByUserIdAndPassword(userId, password);
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
