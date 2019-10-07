package br.com.bandtec.bora.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.dto.CadastrarEvento;
import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;

@Service
public class EventoService {

	@Autowired
	private EventoRepositorio eventoRepositorio;

	@Autowired
	private UsuarioEventoRepositorio usuarioEventoRepositorio;

	
	public Evento atualizarEvento(Long idEvento, Evento evento) {
		evento.setIdEvento(idEvento);
		evento.setNome(evento.getNome());
		evento.setEndereco(evento.getEndereco());
		evento.setCategoria(evento.getCategoria());
		evento.setHoraInicio(evento.getHoraInicio());
		return eventoRepositorio.save(evento);
	}

	
	public List<Evento> buscarEventoPorNome(String nomeEvento) {
		return (List<Evento>) eventoRepositorio.findByNome(nomeEvento);
	}

//	public List<Evento> buscarEventosPorUsuario(Usuario usuario) {
//		return eventoRepositorio.findByOrganizador(usuario.getApelido());
//	}

	public List<Evento> buscarTodosEventos(Evento evento) {
		return eventoRepositorio.findAll();
	}

	@Transactional
	public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
		Usuario usuario = new Usuario();
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Evento evento = new Evento();
//		usuario.setIdUsuario(cadastrarEvento.getUsuario().getIdUsuario());

		evento.setCategoria(cadastrarEvento.getEvento().getCategoria());
		evento.setHoraInicio(cadastrarEvento.getEvento().getHoraInicio());
		evento.setEndereco(cadastrarEvento.getEvento().getEndereco());
		evento.setNome(cadastrarEvento.getEvento().getNome());
		evento.setOrganizador(usuario);

		usuarioEventoRepositorio.save(usuarioEvento);
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);

	}

}
