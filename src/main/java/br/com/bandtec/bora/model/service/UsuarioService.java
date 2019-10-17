package br.com.bandtec.bora.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	
	public List<Usuario> buscarTodosUsuarios() throws Exception {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		if (usuarios.isEmpty()) {
			throw new Exception("Nenhum usuario encontrado");
		}
		return usuarios;
	}
	
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	
	public Usuario buscarUsuarioPeloIdUsuario(Long idUsuario) {
		return usuarioRepositorio.findById(idUsuario).orElse(null);
	}

	
	public void deletarUsuario(Long idUsuario) {
		usuarioRepositorio.deleteById(idUsuario);
	}
}