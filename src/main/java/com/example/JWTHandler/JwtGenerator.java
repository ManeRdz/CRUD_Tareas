package com.example.JWTHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtGenerator {
    private static final String SigningKey = "MTIzMTI5ODM2MTI5MTJkOTE4OTJoZDMxZHhuYjMxMjEybDNkc3huY2Fzamxoa2RoYURBUw==";
    public String GetToken(UserDetails user){
        Map<String, Object> claims = new HashMap<>();
        return GenerateToken(claims, user);

    }

    public String GenerateToken(Map<String, Object> claims, UserDetails user){
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(GetKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key GetKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }




    public String getUserNameFromToken(String token) {

        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(GetKey()).build().parseClaimsJws(token).getBody();
    }

    public  <T> T getClaim(String Token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaims(Token);
        return claimResolver.apply(claims);
    }
    private Date getExpiration(String Token){
        return getClaim(Token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String Token){
        return getExpiration(Token).before(new Date());
    }
}
