package com.cjng.management;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ManagementApplicationTests {
    @Test
    void testJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "cjng");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "cjng")
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600 * 1000))// 1 hour
                .compact();
        System.out.println(jwt);
    }

    @Test
    void testParseJWT() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzA4MjU5MzEyLCJ1c2VybmFtZSI6ImNqbmcifQ.rHh8FRS1r_X8qUsOBZTtE_CMLYG1lLC9B1FR7hWF2Z4";
        System.out.println(Jwts.parser().setSigningKey("cjng").parseClaimsJws(jwt).getBody());
    }
}
