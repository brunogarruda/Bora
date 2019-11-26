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
import br.com.bandtec.bora.model.enums.AvaliacaoEnum;
import br.com.bandtec.bora.model.form.ApelidoForm;
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
	
	public void cadastrarEvento(CadastrarEventoDTO cadastrarEvento) {
		Usuario usuario = usuarioRepositorio.findById(cadastrarEvento.getUsuario().getIdUsuario()).orElse(null);
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Evento evento = new Evento();
		
		evento.setIdSubCategoria(cadastrarEvento.getEvento().getIdSubCategoria());
		evento.setDataHoraInicio(cadastrarEvento.getEvento().getDataHoraInicio());
		evento.setEndereco(cadastrarEvento.getEvento().getEndereco());
		evento.setNome(cadastrarEvento.getEvento().getNome());
		evento.setOrganizador(usuario);

		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		usuarioEvento.setOrganizador(true);
		usuarioEventoRepositorio.save(usuarioEvento);
	}	
	
	public Evento atualizarEvento(Long idEvento, Evento evento) {
		Evento eventoAtualizado = eventoRepositorio.findById(idEvento).orElse(null);
		eventoAtualizado.setNome(evento.getNome() != null ? evento.getNome() : eventoAtualizado.getNome());
		eventoAtualizado.setDescricaoEvento(evento.getDescricaoEvento() != null ? evento.getDescricaoEvento() : eventoAtualizado.getDescricaoEvento());
		eventoAtualizado.setDataHoraInicio(evento.getDataHoraInicio() != null ? evento.getDataHoraInicio() : eventoAtualizado.getDataHoraInicio());
		eventoAtualizado.setDataHoraFim(evento.getDataHoraFim() != null ? evento.getDataHoraFim() : eventoAtualizado.getDataHoraFim());
		eventoAtualizado.setEndereco(evento.getEndereco() != null ? evento.getEndereco() : eventoAtualizado.getEndereco());
		eventoAtualizado.setIdSubCategoria(evento.getIdSubCategoria() != null ? evento.getIdSubCategoria() : eventoAtualizado.getIdSubCategoria());
		return eventoRepositorio.save(eventoAtualizado);
	}
	
	public Evento entrarEvento(Long idEvento, String apelido) {
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		Usuario usuario = usuarioRepositorio.findByApelido(apelido);
		Evento evento = eventoRepositorio.findById(idEvento).orElse(null);
		
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		usuarioEvento.setOrganizador(false);
		usuarioEventoRepositorio.save(usuarioEvento);
		return evento;
	}
	
	public void sairEvento(Long idEvento, ApelidoForm apelido) {
		Usuario usuario = usuarioRepositorio.findByApelido(apelido.getApelido());
		Evento evento = eventoRepositorio.findById(idEvento).orElse(null);
		UsuarioEvento usuarioEvento = usuarioEventoRepositorio.buscaPorUsuarioEventoPeloIdUsuarioIdEvento(usuario.getIdUsuario(), evento.getIdEvento());
		usuarioEventoRepositorio.deleteById(usuarioEvento.getId());
	}

	public void avaliarEvento(Long idEvento, AvaliacaoEnum nota) {
		Evento evento = eventoRepositorio.findById(idEvento).orElse(null);
		double media = evento.getAvaliacao();

		switch (nota) {
            case ESTRELA1:
                media += 1;
                break;
            case ESTRELA2:
				media += 2;
                break;
            case ESTRELA3:
                media += 3;
				break;
			case ESTRELA4:
                media += 4;
				break;
			case ESTRELA5:
                media += 5;
                break;
            default:
                media += 0;
		}
		
		evento.setAvaliacao(media/2);
		eventoRepositorio.save(evento);
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
		
		//trazendo os participantes do evento
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

	public List<Evento> buscarEventoPorSubCategoria(Long subcategoriaIdFk) {
		List<Evento> eventos = eventoRepositorio.findByIdSubCategoria_idSubCategoria(subcategoriaIdFk);
		return eventos;
	}

//	public List<Evento> buscarEventosPorUsuario(Usuario usuario) {
//		return eventoRepositorio.findByOrganizador(usuario.getApelido());
//	}
		
}
