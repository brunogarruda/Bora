
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
import br.com.bandtec.bora.model.excecoes.DomainException;
import br.com.bandtec.bora.model.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

	private final UsuarioService usuarioService;


	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuario));
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() throws Exception {
		return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPeloIdUsuario(@PathVariable(value = "id") Long idUsuario) {
		Usuario buscarUsuario = usuarioService.buscarUsuarioPeloIdUsuario(idUsuario);

		if (buscarUsuario == null) {
			throw new DomainException("Usuario não encontrado");
		}

		return ResponseEntity.ok(usuarioService.buscarUsuarioPeloIdUsuario(idUsuario));

	}
//
//	/*
//	 * atualizar um usuario cadastrado pelo idUsuario
//	 */
//
//	@PutMapping("/usuarios/{idUsuario}")
//	public ResponseEntity<Usuario> atualizarUsuarioPeloIdUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
//			@Valid @RequestBody Usuario usuario) {
//		Optional<Usuario> alterarUsuario = usuarioDAO.encontrarUsuarioPeloId(idUsuario);
//
//		if (alterarUsuario == null) {
//			return ResponseEntity.notFound().build();
//		}
//		usuario.setNome(usuario.getNome());
//		usuario.setUsuario(usuario.getUsuario());
//		usuario.setCelular(usuario.getCelular());
//		usuario.setSenha(usuario.getSenha());
//
//		Usuario usuarioAlterado = usuarioDAO.cadastrar(usuario);
//		return ResponseEntity.ok(usuarioAlterado);
//	}
//
	@DeleteMapping("/{idUsuario}")
	public void deletarUsuario(@PathVariable(value = "idUsuario") Long idUsuario) {
		 usuarioService.deletarUsuario(idUsuario);
	}

}
