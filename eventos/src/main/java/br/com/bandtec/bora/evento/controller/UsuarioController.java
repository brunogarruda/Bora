package br.com.bandtec.bora.evento.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.evento.model.dto.CadastrarUsuario;
import br.com.bandtec.bora.evento.model.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/admin/usuarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para gerenciar usuarios")
public class UsuarioController {

	private final UsuarioService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Iterable<Usuario>> list(Pageable pageable) throws Exception {
		Iterable<Usuario> usuario = service.buscarUsuarios(pageable);
		if (usuario == null)
			ResponseEntity.noContent().build();

		return ResponseEntity.ok(usuario);

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CadastrarUsuario> cadastrarUsuario(@Valid @RequestBody CadastrarUsuario cadastrarUsuario) {
		service.cadastrarUsuario(cadastrarUsuario);
		if (cadastrarUsuario == null)
			ResponseEntity.badRequest().build();

		return ResponseEntity.ok().build();
	}

	// @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario
	// usuario) {
	// service.cadastrar(usuario);
	// return ResponseEntity.ok().build();
	// }

}
