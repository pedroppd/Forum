package br.com.forum.config.validation.security;

import br.com.forum.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public String generateTokenJwt(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("api-forum")
                .setSubject(user.getUuid().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
}
