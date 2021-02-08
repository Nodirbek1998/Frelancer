package uz.freelancer.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.freelancer.entity.Users;

import java.util.Date;

@Component
public class JwtProvider {

    private String key = "freelancer";
    private long liveTime = 4320000000L;

    public String generateToken(Users users){
        return Jwts.builder()
                .setSubject(users.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + liveTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("phone", users.getPhone())
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
