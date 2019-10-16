
package br.com.bandtec.bora.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuario));
	}

	@GetMapping("/usuarios")
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioService.buscarTodosUsuarios();
	}
	
	@DeleteMapping("usuarios/{idUsuario}")
	public void deletarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
		 usuarioService.deletarUsuario(idUsuario);
	}

}
