package br.com.bandtec.bora.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.dto.CadastrarEventoDTO;
import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.EventoRepositorio;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;
import br.com.bandtec.bora.repository.UsuarioRepositorio;

@Service
public class EventoService {

	@Autowired
	private EventoRepositorio eventoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private UsuarioEventoRepositorio usuarioEventoRepositorio;
	
	
	public List<Evento> buscarEventosHome(){
		return eventoRepositorio.findForHome(new PageRequest(0, 8));
	}
	
	@Transactional
	public void cadastrarEvento(CadastrarEventoDTO cadastrarEvento) {
		Usuario usuario = new Usuario();
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Evento evento = new Evento();
		usuario.setIdUsuario(cadastrarEvento.getUsuario().getIdUsuario());
		
		evento.setCategoria(cadastrarEvento.getEvento().getCategoria());
		evento.setDataHoraInicio(cadastrarEvento.getEvento().getDataHoraInicio());
		evento.setEndereco(cadastrarEvento.getEvento().getEndereco());
		evento.setNome(cadastrarEvento.getEvento().getNome());
		evento.setOrganizador(usuario);

		usuarioEventoRepositorio.save(usuarioEvento);
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		usuarioEvento.setOrganizador(true);
		
	}	
	
	public Evento atualizarEvento(Long idEvento, Evento evento) {
		Evento eventoAtualizado = eventoRepositorio.findById(idEvento).orElse(null);
		eventoAtualizado.setNome(evento.getNome());
		eventoAtualizado.setEndereco(evento.getEndereco());
		eventoAtualizado.setCategoria(evento.getCategoria());
		eventoAtualizado.setDataHoraInicio(evento.getDataHoraInicio());
		return eventoRepositorio.save(eventoAtualizado);
	}
	
	public Evento entrarEvento(Long idEvento, Usuario usuario) {
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Evento evento = eventoRepositorio.findById(idEvento).orElse(null);
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		usuarioEvento.setOrganizador(false);
		usuarioEventoRepositorio.save(usuarioEvento);
		return evento;
	}
	
	public List<Evento> buscarTodosEventos(Evento evento) {
		return eventoRepositorio.findAll();
	}

	public List<Evento> buscarEventoPorNome(String nomeEvento) throws Exception {
		List<Evento> eventos = eventoRepositorio.findByNome(nomeEvento);
		if (eventos.isEmpty()) {
			throw new Exception("Evento não encontrado"); 
		}
		return eventos;
	}
	
	public Evento buscarEventoPorIdEvento(Long idEvento) throws Exception {
		Evento evento = eventoRepositorio.findById(idEvento).orElse(null);
		if (evento == null) {
			throw new Exception("Evento não encontrado");
		}
		
		List<UsuarioEvento> usuarioEvento = usuarioEventoRepositorio.findByEvento_idEvento(idEvento);
		List<Usuario> participantes = new ArrayList<Usuario>();
		
		for(int i = 0; i<usuarioEvento.size(); i++) {
			Usuario usuario = usuarioEvento.get(i).getUsuario();
			
			if (usuario != null) {
				participantes.add(usuario);
			}
		}
		
		if(participantes.isEmpty()) {
			evento.setParticipantes(null);
		}else {
			evento.setParticipantes(participantes);
		}
		
		return evento;
		
	}

//	public List<Evento> buscarEventosPorUsuario(Usuario usuario) {
//		return eventoRepositorio.findByOrganizador(usuario.getApelido());
//	}
		
}
