//package com.vestigershop.ProductService.security;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.Collection;
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//import javax.crypto.SecretKey;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.security.Keys;
//
//
//
//@Component
//public class JwtUtils {
//		private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//		@Value("${vestiger.app.jwtSecret}")
//		private String jwtSecret;
//		private SecretKey secretKey;
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		@Value("${vestiger.app.jwtExpirationMs}")
//		private int jwtExpirationMs;
//
//		@PostConstruct
//	    protected void init() {
//	        var secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
//	        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//	    }
//		public String getUserNameFromJwtToken(String token) {
//			return Jwts.parserBuilder().setSigningKey(secretKey).build().
//					parseClaimsJws(token).getBody().getSubject();
//		}
//
//		 public Authentication getAuthentication(String token) {
//		        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
//		                .parseClaimsJws(token)
//		                .getBody();
//		        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("role").toString());
//		        User principal = new User(claims.getSubject(), "", authorities);
//		        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//		    }
//		public boolean validateJwtToken(String authToken) {
//			try {
//				Jwts.parserBuilder()
//			            .setSigningKey(secretKey).build()
//			            .parseClaimsJws(authToken).getBody();
//				return true;
//			} catch (JwtException e) {
//				//logger.error("Invalid JWT signature: {}", e.getMessage());
//			}
//
//			return false;
//		}
//		
//		public Claims getAllClaimsFromToken(String token) {
//			return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
//		}
//}
//
