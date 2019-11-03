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

	
	public List<Usuario> buscarTodosUsuarios() {
		return (List<Usuario>) usuarioRepositorio.findAll();
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
	
	public Usuario buscarUsuarioPorNome(String login) {
		
		Usuario usuario = usuarioRepositorio.findByApelido(login);
		
		if(usuario == null) {
			usuario = usuarioRepositorio.findByEmail(login);
		}
		return usuario;
	}
}