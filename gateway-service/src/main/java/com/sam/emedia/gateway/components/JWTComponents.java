package com.sam.emedia.gateway.components;



//import com.csa.authservice.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTComponents {

    byte[] secret = Base64.getDecoder().decode("c2FtaW06c2FtaW1AMTweIz454dfgddrtxcv34xcgvdfgdfxcvgthesecritkiymustsxdfbeaslongasitisgoodtobe");
    public HashMap<String,Object> getAccessToken(HashMap<String,Object> dataMap){
        Date issueDate = Date.from(Instant.now());
        Date expDate = Date.from(Instant.now().plus(30*24, ChronoUnit.HOURS));
        Date refreshExp = Date.from(Instant.now().plus(12*30*24,ChronoUnit.HOURS));
        HashMap<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("accessToken",generateToken(dataMap,"accessToken","WBT-APPS",issueDate,expDate));
        tokenMap.put("refreshToken",generateToken(dataMap,"refreshToken","WBT-APPS",issueDate,refreshExp));
        tokenMap.put("accessTokenExpirationDate",expDate.getTime());
        tokenMap.put("refreshTokenExpirationDate",refreshExp.getTime());
        return tokenMap;
    }
    private String generateToken(HashMap<String,Object> data,String subject, String audience, Date issueAt, Date expireAt){

        Key signedKey = Keys.hmacShaKeyFor(secret);
        String jwtStr = Jwts.builder()
                .setSubject(subject)
                .setAudience(audience)
                .claim("claim",data)
                .setIssuedAt(issueAt)
                .setExpiration(expireAt)
                .signWith(signedKey,SignatureAlgorithm.HS512)
                .compact();
        return jwtStr;


    }
    public HashMap<String,Object> getDataMap(String accessToken){
        Key signedKey = Keys.hmacShaKeyFor(secret);
        Jws<Claims> result = Jwts.parserBuilder().setSigningKey(signedKey).build()
                .parseClaimsJws(accessToken);
        return result.getBody().get("claim",HashMap.class);
    }

    public boolean isExpired(String accessToken){
        try{
            Key signedKey = Keys.hmacShaKeyFor(secret);
            Jws<Claims> result = Jwts.parserBuilder().setSigningKey(signedKey).build()
                    .parseClaimsJws(accessToken);

            return false;
        }catch (Exception ignored){
            return true;
        }
    }
    public Date getExpiryDate(String accessToken){
        if(!isExpired(accessToken)){
            Key signedKey = Keys.hmacShaKeyFor(secret);
            Jws<Claims> result = Jwts.parserBuilder().setSigningKey(signedKey).build()
                    .parseClaimsJws(accessToken);
            Claims claims = result.getBody();
            return result.getBody().getExpiration();
        }
        return null;
    }

    public String getHeaderToken(String headerToken){
        if(headerToken != null && headerToken.startsWith("Bearer ")){
            return headerToken.substring(7);
        }
        return null;
    }

    public String getHeaderToken(HttpServletRequest request){
        String headerToken = request.getHeader("Authorization");
        if(headerToken != null && headerToken.startsWith("Bearer ")){
            return headerToken.substring(7);
        }
        return null;
    }

    /**
     * Not part of the library and might be deleted in another project
     * **/

//    public HashMap<String,Object> getToken(User user){
//        HashMap<String,Object> tokenMap = new HashMap<>();
//        tokenMap.put("id",user.getId());
//        tokenMap.put("name",user.getName());
//        return getAccessToken(tokenMap);
//    }
}