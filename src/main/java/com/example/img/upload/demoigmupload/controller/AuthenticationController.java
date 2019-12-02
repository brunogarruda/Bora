package com.example.img.upload.demoigmupload.controller;

import com.example.img.upload.demoigmupload.dto.LoginForm;
import com.example.img.upload.demoigmupload.dto.TokenDTO;
import com.example.img.upload.demoigmupload.services.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
