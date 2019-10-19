package br.com.bandtec.bora.evento.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import br.com.bandtec.bora.evento.model.dto.CadastrarUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

	private final UsuarioRepositorio usuarioRepositorio;

	public Iterable<Usuario> buscarUsuarios(Pageable pageable) {
		log.info("Listing all eventos");
		return usuarioRepositorio.findAll();
	}

	@Transactional
	public void cadastrarUsuario(CadastrarUsuario cadastrarUsuario){
		Usuario usuario = new Usuario();
		usuarioRepositorio.save(usuario);
		usuario.setApelido(cadastrarUsuario.getUsuario().getApelido());
		usuario.setSenha(cadastrarUsuario.getUsuario().getSenha());
		usuario.setRole(cadastrarUsuario.getUsuario().getRole());
		System.out.println(usuario);
	}

	// public Usuario cadastrar(Usuario usuario) {
	// 	System.out.println(usuario);
	// 	return usuarioRepositorio.save(usuario);
	// }
	
}