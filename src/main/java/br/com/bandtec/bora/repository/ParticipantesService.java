package br.com.bandtec.bora.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.model.entity.UsuarioEvento;

@Service
public class ParticipantesService {
	
	private UsuarioEventoRepositorio usuarioEvento;
	
	@Autowired
	public ParticipantesService(UsuarioEventoRepositorio usuarioEvento) {
		this.usuarioEvento = usuarioEvento;
	}
	
	public List<UsuarioEvento> buscarParticipanteEvento(int id){
		return usuarioEvento.findAll();
		//return usuarioEvento.participantes(id);
	}

	
}
