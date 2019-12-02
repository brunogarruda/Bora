package com.example.img.upload.demoigmupload.services;

import com.example.img.upload.demoigmupload.dto.LoginForm;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.example.img.upload.demoigmupload.repository.UsuarioRepositorio;
import com.example.img.upload.demoigmupload.security.ExpiredTokenException;
import com.example.img.upload.demoigmupload.security.InvalidTokenException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserAuthenticationService {
    private final UsuarioRepositorio repositorio;
    private final TokenService service;

    public String criaTokenLogin(LoginForm loginForm){
        Usuario usuario = repositorio.validaLoginESenha(loginForm.getApelido(),loginForm.getSenha());
        if(usuario==null)
            return "erro";
        return service.geraToken(usuario);
    }

    private boolean validate(String token){
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = service.decodeToken(tokenTratado);
            log.info(claims.getIssuer());
            log.info(String.valueOf(claims.getIssuedAt()));
            if(claims.getExpiration().before(new Date(System.currentTimeMillis())))throw new ExpiredTokenException();
            return true;

        }catch (ExpiredTokenException et){
            et.printStackTrace();;
            throw et;
        }catch (Exception e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
