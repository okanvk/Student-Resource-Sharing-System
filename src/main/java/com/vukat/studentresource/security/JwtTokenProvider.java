package com.vukat.studentresource.security;

import com.vukat.studentresource.domain.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.vukat.studentresource.security.SecurityConstants.EXPIRATION_TIME;
import static com.vukat.studentresource.security.SecurityConstants.SECRET_KEY;

@Component
public class JwtTokenProvider {

    //Generate Token

    public String generateToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expire_date = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();

        claims.put("id",(Long.toString(user.getId())));
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());

        return Jwts.builder().setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now).setExpiration(expire_date)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();



    }

    //Validate Token
    public boolean validateToken(String token){

        try{

            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT Token");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Long getUserIdFromJwt(String token){

        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        String id = (String) (claims.get("id"));

        Long long_id = Long.parseLong(id);

        return long_id;

    }
    //Get UserId from the token



}
