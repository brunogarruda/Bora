package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.core.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenService {
    //30 minutos
    static final long EXPIRATION_TIME = 1800000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";


    static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }



}
