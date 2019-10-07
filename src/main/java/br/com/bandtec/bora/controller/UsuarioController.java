
package br.com.bandtec.bora.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.bandtec.bora.model.service.notfound.NaoEncontreiUsuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	/*
	 * Para cadastrar um usuario
	 */

	@PostMapping()
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(service.cadastrarUsuario(usuario));
	}

	/*
	 * Para buscar todos os usuarios cadastrados
	 */

	@GetMapping
	public List<Usuario> buscarTodosUsuarios() {
		return service.buscarTodosUsuarios();
	}

	/*
	 * Para buscar usuarios cadastrados pelo idUsuario
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> buscarUsuarioPeloIdUsuario(@PathVariable Long id) {
		Optional<Usuario> buscarUsuario = service.buscarUsuarioPeloIdUsuario(id);

		if (!buscarUsuario.isPresent()) {
			throw new NaoEncontreiUsuario("id: " + id);
		}

		return ResponseEntity.ok(service.buscarUsuarioPeloIdUsuario(id));

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

	/*
	 * Deletar um usuario
	 * 
	 * @DeleteMapping("/{id}") public void deletarUsuario(@PathVariable(value =
	 * "idUsuario") Long idUsuario) { usuarioService.deletarUsuario(idUsuario); }
	 * 
	 */
}
