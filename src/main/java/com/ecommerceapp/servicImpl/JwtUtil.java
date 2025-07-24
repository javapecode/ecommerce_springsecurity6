package com.ecommerceapp.servicImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String SECRET_KEY;
    

	public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();

	    // ✅ Add roles to the claims
	    claims.put("roles", userDetails.getAuthorities()
	        .stream()
	        .map(GrantedAuthority::getAuthority)
	        .collect(Collectors.toList()));

	    return Jwts.builder()
	        .setClaims(claims)
	        .setSubject(userDetails.getUsername())
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
	        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	        .compact();
	}


    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
    	 Date expiration = Jwts.parser()
    		        .setAllowedClockSkewSeconds(120) // ✅ Allow 2 minutes skew
    		        .setSigningKey(SECRET_KEY)
    		        .parseClaimsJws(token)
    		        .getBody()
    		        .getExpiration();
    		    return expiration.before(new Date());
    }
}

