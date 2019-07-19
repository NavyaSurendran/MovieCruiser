package com.cognizant.moviecruiser.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.moviecruiser.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * @author Navya Surendran
 *
 */

@Component
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator {

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiser.service.SecurityTokenGenerator#generateToken(com.cognizant.moviecruiser.entity.User)
	 * method for generating JWT Access Token
	 */
	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId())
					   .setIssuedAt(new Date())
					   .setSubject(user.getUserId())
					   .signWith(SignatureAlgorithm.HS256, "secretKey")
					   .compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}

}
