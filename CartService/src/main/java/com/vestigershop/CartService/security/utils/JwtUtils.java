package com.vestigershop.CartService.security.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

		@Value("${vestiger.app.jwtSecret}")
		private String jwtSecret;
		private SecretKey secretKey;

		@PostConstruct
	    protected void init() {
	        var secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
	        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }
		public String getUserNameFromJwtToken(String token) {
			return Jwts.parserBuilder().setSigningKey(secretKey).build().
					parseClaimsJws(token).getBody().getSubject();
		}

		public boolean validateJwtToken(String authToken) {
			try {
				Jwts.parserBuilder()
			            .setSigningKey(secretKey).build()
			            .parseClaimsJws(authToken).getBody();
				return true;
			} catch (JwtException e) {
				//logger.error("Invalid JWT signature: {}", e.getMessage());
			}

			return false;
		}
	}

