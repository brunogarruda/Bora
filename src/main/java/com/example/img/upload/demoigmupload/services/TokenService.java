package com.example.img.upload.demoigmupload.services;

import com.example.img.upload.demoigmupload.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String geraToken(Usuario usuario){
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime()+ Long.parseLong(expiration));


        return Jwts.builder()
            .setIssuer("API Bora")
            .setSubject(usuario.getApelido())
            .claim("roles","user")
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public Claims decodeToken(String token){
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

}
