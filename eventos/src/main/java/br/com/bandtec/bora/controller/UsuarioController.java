package br.com.bandtec.bora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.model.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/admin/usuarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para gerenciar usuarios")
public class UsuarioController {

	private final UsuarioService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Iterable<Usuario>> list(Pageable pageable) {
		return new ResponseEntity<>(service.buscarUsuarios(pageable), HttpStatus.OK);

	}

	
}
