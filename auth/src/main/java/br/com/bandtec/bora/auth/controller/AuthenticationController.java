package br.com.bandtec.bora.auth.controller;

import br.com.bandtec.bora.auth.dto.LoginForm;
import br.com.bandtec.bora.auth.dto.TokenDTO;
import br.com.bandtec.bora.auth.service.UserAuthenticationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoint para autenticar usuario")
@Slf4j
public class AuthenticationController {
    private final UserAuthenticationService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginForm loginForm) {
       String token = service.criaTokenLogin(loginForm);
       if(token.isEmpty()||token==null || token.contains("erro"))
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }
}
