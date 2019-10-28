package br.com.bandtec.bora.auth.controller;

import br.com.bandtec.bora.auth.dto.DadosLogin;
import br.com.bandtec.bora.auth.dto.UserAutheticatedDTO;
import br.com.bandtec.bora.auth.service.UserAuthenticationService;
import br.com.bandtec.bora.core.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/v1/api/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para autenticar usuario")
public class AuthenticationController {
    private final UserAuthenticationService service;

    @PostMapping
    public String autenticar(@RequestBody DadosLogin dadosLogin) throws ServletException {
        String jwtToken = "";

        if(dadosLogin.getApelido()==null || dadosLogin.getSenha()==null)
            throw new ServletException("Por favor preenche os campos");

        Usuario usuario = service.findByApelido(dadosLogin.getApelido());
        if (usuario==null)
            throw new ServletException("Usuario nao encontrado");

        jwtToken = Jwts.builder()
            .setSubject(dadosLogin.getApelido()).claim("roles","user").setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256,"secretKey").compact();

        return jwtToken;
    }
}
