package br.com.bandtec.bora.auth.controller;

import br.com.bandtec.bora.auth.dto.UserRegistrationDTO;
import br.com.bandtec.bora.auth.service.UserRegistrationService;
import br.com.bandtec.bora.core.model.Usuario;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/v1/api/usuarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para cadastrar usuario")
public class UserRegistrationController {
    private final UserRegistrationService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserRegistrationDTO> cadastrarUsuario(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        Usuario usuario = service.salvar(userRegistrationDTO.cadastrar());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
