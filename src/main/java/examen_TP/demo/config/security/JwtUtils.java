package examen_TP.demo.config.security;

import examen_TP.demo.dao.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


import java.security.Key;
import java.util.Date;

import java.util.function.Function;

public  class  JwtUtils {
    public static String generateToken(User userInfo, JwtConfig jwtConfig) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .claim(jwtConfig.getAccessId(), userInfo.getId())
                .claim(jwtConfig.getRole(),userInfo.getRole())
                .setSubject(userInfo.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(jwtConfig.getExpiration())))  // in milliseconds
                .signWith(getSignInKey(jwtConfig.getSigningKey()), SignatureAlgorithm.HS256).compact();

    }

    public static String extractUsername(String token, String signInKey) {
        return extractClaim(token, Claims::getSubject, signInKey);
    }


    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String signInKey) {
        final Claims claims = extractAllClaims(token, signInKey);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token, String signInKey) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(signInKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private static Key getSignInKey(String signInKey) {
        byte[] keyBytes = Decoders.BASE64.decode(signInKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public static boolean isTokenExpired(String token, String signingKey) {
        return extractAllClaims(token,signingKey).getExpiration().after(new Date());
    }

}
