package br.com.bandtec.bora.model.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.EventoRepositorio;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;
import br.com.bandtec.bora.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private UsuarioEventoRepositorio usuarioEventoRepositorio;
	
	@Autowired
	private EventoRepositorio eventoRepositorio;

	
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

	
	public Usuario buscarUsuarioPeloIdUsuario(Long idUsuario) throws Exception {
		Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
		
		if (usuario == null) {
			throw new Exception("Usuario Não Encontrado");
		}
		
		List<UsuarioEvento> usuarioEvento = usuarioEventoRepositorio.findByUsuario_idUsuario(idUsuario);
		List<Evento> eventos = new ArrayList<Evento>();
		
		for(int i = 0; i<usuarioEvento.size(); i++) {
			Evento evento = eventoRepositorio.findById(usuarioEvento.get(i).getEvento().getIdEvento().longValue()).orElse(null);
			eventos.add(evento);
		}
		
		usuario.setEventosQueParticipo(eventos);
		return usuario;
	}

	
	public void deletarUsuario(Long idUsuario) {
		usuarioRepositorio.deleteById(idUsuario);
	}

	public Usuario buscarUsuarioPeloNome(String apelido) throws Exception {
		Usuario usuario = usuarioRepositorio.findByApelido(apelido);
		if (usuario == null) {
			
		}
		return usuario;
	}
}