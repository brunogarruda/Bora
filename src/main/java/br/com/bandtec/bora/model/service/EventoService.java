package br.com.bandtec.bora.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.dto.CadastrarEvento;
import br.com.bandtec.bora.model.entity.Categoria;
import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.entity.SubCategoria;
import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.CategoriaRepositorio;
import br.com.bandtec.bora.repository.EventoRepositorio;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;

@Service
public class EventoService {

	@Autowired
	private EventoRepositorio eventoRepositorio;

	@Autowired
	private UsuarioEventoRepositorio usuarioEventoRepositorio;
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	
/*	public Evento atualizarEvento(Long idEvento, Evento evento) {
		evento.setIdEvento(idEvento);
		evento.setNome(evento.getNome());
		evento.setEndereco(evento.getEndereco());
		evento.setIdSubCategoria(evento.getSubcategoriaIdFk());
		evento.setDataHoraInicio(evento.getDataHoraInicio());
		return eventoRepositorio.save(evento);
	} */

	public List<Evento> buscarEventoPorNome(String nomeEvento) {
		return (List<Evento>) eventoRepositorio.findByNome(nomeEvento);
	}

//	public List<Evento> buscarEventosPorUsuario(Usuario usuario) {
//		return eventoRepositorio.findByOrganizador(usuario.getApelido());
//	}

	public List<Evento> buscarTodosEventos(Evento evento) {
		return eventoRepositorio.findAll();
	}
	
	public List<Evento> buscarEventoPorSubCategoria(String subcategoria) {
		return (List<Evento>) eventoRepositorio.buscarEventoPorSubCategoria(subcategoria);
	}


	@Transactional
	public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
		Usuario usuario = new Usuario();
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Evento evento = new Evento();
		usuario.setIdUsuario(cadastrarEvento.getUsuario().getIdUsuario());
		
	/*	evento.setIdCategoria(cadastrarEvento.getEvento().getIdCategoria()); */
		evento.setDataHoraInicio(cadastrarEvento.getEvento().getDataHoraInicio());
		evento.setEndereco(cadastrarEvento.getEvento().getEndereco());
		evento.setNome(cadastrarEvento.getEvento().getNome());
//		evento.setOrganizador(usuario);

		usuarioEventoRepositorio.save(usuarioEvento);
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		usuarioEvento.setOrganizador(true);
		
	}

}
