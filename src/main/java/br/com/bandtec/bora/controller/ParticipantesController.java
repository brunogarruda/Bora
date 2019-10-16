package br.com.bandtec.bora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.ParticipantesService;

@RestController
@RequestMapping("/api")
public class ParticipantesController {
	
	
	private ParticipantesService participantesService;
	
	@Autowired
	public ParticipantesController(ParticipantesService participantesService) {
		super();
		participantesService = participantesService;
	}

	@GetMapping("/participantes")
	public ResponseEntity<List<UsuarioEvento>> buscarParticipantes(){
		return ResponseEntity.ok().body(participantesService.buscarParticipanteEvento(1)); 
	}
	
}
