package br.com.bandtec.bora.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.core.model.Usuario;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("user")
@Api(value = "Endpoints para gerenciar usuario")
public class UserInfoController {

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Will retrieve the information from the user available in the token", response = Usuario.class)
	public ResponseEntity<Usuario> pegaInfoUsuario(Principal principal) {
		Usuario usuario = (Usuario) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

}
