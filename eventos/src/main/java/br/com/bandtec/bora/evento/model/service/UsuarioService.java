package br.com.bandtec.bora.evento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
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
}