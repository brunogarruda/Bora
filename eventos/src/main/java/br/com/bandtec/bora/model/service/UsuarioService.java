package br.com.bandtec.bora.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public List<Usuario> buscarTodosUsuarios() {
		return (List<Usuario>) usuarioRepositorio.findAll();
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	public Optional<Usuario> buscarUsuarioPeloIdUsuario(Long idUsuario) {
		return usuarioRepositorio.findById(idUsuario);
	}

	public void deletarUsuario(Long idUsuario) {
		usuarioRepositorio.deleteById(idUsuario);
	}
}