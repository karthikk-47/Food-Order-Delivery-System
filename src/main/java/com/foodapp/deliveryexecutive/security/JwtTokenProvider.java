/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.jsonwebtoken.Claims
 *  io.jsonwebtoken.JwtException
 *  io.jsonwebtoken.Jwts
 *  io.jsonwebtoken.SignatureAlgorithm
 *  io.jsonwebtoken.security.Keys
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.GrantedAuthority
 *  org.springframework.security.core.authority.SimpleGrantedAuthority
 *  org.springframework.security.core.userdetails.User
 *  org.springframework.stereotype.Component
 */
package com.foodapp.deliveryexecutive.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_VALIDITY = 86400000L;
    private final Key key;

    public JwtTokenProvider(@Value(value="${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor((byte[])secretKey.getBytes());
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = new Date().getTime();
        Date validity = new Date(now + 86400000L);
        return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities).signWith(this.key, SignatureAlgorithm.HS256).setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = (Claims)Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        Collection authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(authToken);
            return true;
        }
        catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = (Claims)Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = (Claims)Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.get((Object)"userId").toString());
    }
}
