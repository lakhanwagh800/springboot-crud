package com.springboot.rest.config;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.rest.entity.CustomeException;
import com.springboot.rest.model.Student;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenManager {
	 private static String secretKey="Ip0jPQ/4&|8K@|$usx(~Lx]e{mt+$8^yfu(/mx%7-Q4pN>+ga!_:cyf.?QZIP%2K";
	    

	    public static String issueToken( Student student, long days) {

	        try {
	            // The JWT signature algorithm we will be using to sign the token
	            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	            long nowMillis = System.currentTimeMillis();
	            Date now = new Date(nowMillis);

	            // We will sign our JWT with our ApiKey secret
	            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	            SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	            // Adding only the essential properties of User in JWT Token
	            Map<String, Object> userMap = new HashMap<>();
	            userMap.put("id", student.getId());
	            userMap.put("email", student.getEmail());
	            userMap.put("logInUserType", "MANAGER");

	            // Let's set the JWT Claims
	            JwtBuilder builder = Jwts.builder().claim("userMap", userMap)
	                    .setId(Long.toString(student.getId())).setIssuedAt(now)
	                    .signWith(signatureAlgorithm, signingKey);

	            // if it has been specified, let's add the expiration
	            if (days > 0) {
	                ZoneId defaultZoneId = ZoneId.systemDefault();
	                LocalDate localDate =  LocalDate.now().plusDays(days);
	                Date expiryDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//	               long expMillis = nowMillis + (1000 * 60 * 2 );
//	               Date exp = new Date(expMillis);
	                 builder.setExpiration(expiryDate);
	           }

	            // Builds the JWT and serializes it to a compact, URL-safe string
	            return builder.compact();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public String getSecretKey() {
	        return secretKey;
	    }

//	    @Value("${token.secretekey}")
	    public void setSecretKey(String secretKey) {
	        TokenManager.secretKey = secretKey;
	    }



	    public static int getUser(String token) throws Exception {

	        try {

	            Map<String, Object> userDetailsMap = null;

	            Claims claims = Jwts.parser()
	                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
	                    .parseClaimsJws(token).getBody();

	            if (claims.containsKey("userMap")) {

	                ObjectMapper mapper = new ObjectMapper();
	                userDetailsMap = mapper.convertValue(claims.get("userMap", LinkedHashMap.class), Map.class);

	                if (!Objects.toString(userDetailsMap.get("logInUserType")).equalsIgnoreCase("MANAGER")) {
	                    throw new CustomeException("Unauthorized User");
	                }

	            } else {
	                throw new CustomeException("Unauthorized User");
	            }
//	            int tokenUserId = (Integer) mapData.get("userId");

	            return  (Integer) userDetailsMap.get("id");

	        } catch (Exception ex) {
	        	ex.printStackTrace();
	            throw new CustomeException("Unauthorized User");
	        }
	    }
}
