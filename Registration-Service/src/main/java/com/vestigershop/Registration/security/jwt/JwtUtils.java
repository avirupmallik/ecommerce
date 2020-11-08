package com.vestigershop.Registration.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.vestigershop.Registration.Data.User;
import com.vestigershop.Registration.service.impl.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

		@Value("${vestiger.app.jwtSecret}")
		private String jwtSecret;
		private SecretKey secretKey;
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		@Value("${vestiger.app.jwtExpirationMs}")
		private int jwtExpirationMs;

		@PostConstruct
	    protected void init() {
	        var secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
	        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	    }
		public String generateJwtToken(Authentication authentication) {

			UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userPrincipal.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			Map<String, Object> claims = new HashMap<>();
			claims.put("role", roles);
			
			return Jwts.builder()
					.setClaims(claims)
					.setSubject((userPrincipal.getUsername()))
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(secretKey,signatureAlgorithm)
					.compact();
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

