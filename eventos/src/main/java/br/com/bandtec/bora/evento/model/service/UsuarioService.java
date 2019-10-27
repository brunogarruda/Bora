package br.com.bandtec.bora.evento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import br.com.bandtec.bora.evento.model.dto.CadastrarUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

	private final UsuarioRepositorio usuarioRepositorio;

	@Cacheable(value = "boraCache")
	public Iterable<Usuario> buscarUsuarios(Pageable pageable) throws Exception {
		Iterable<Usuario> usuario = usuarioRepositorio.findAll(Sort.by("apelido"));
		log.info("Listing all usuario");

		if (usuario == null)
			throw new Exception("Nenhum usuario encontrado");

		return usuario;
	}

	// @Transactional
	public void cadastrarUsuario(CadastrarUsuario cadastrarUsuario) {
		Usuario usuario = new Usuario();
		usuarioRepositorio.save(usuario);
		usuario.setApelido(cadastrarUsuario.getUsuario().getApelido());
		usuario.setSenha(cadastrarUsuario.getUsuario().getSenha());
		usuario.setRole(cadastrarUsuario.getUsuario().getRole());
		System.out.println(usuario);
	}
}
