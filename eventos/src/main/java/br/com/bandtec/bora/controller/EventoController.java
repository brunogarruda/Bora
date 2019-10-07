package br.com.bandtec.bora.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.bora.model.dto.CadastrarEvento;
import br.com.bandtec.bora.model.service.EventoService;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private UsuarioEventoRepositorio repositorio;

	@PostMapping
	public ResponseEntity<CadastrarEvento> evento(@RequestBody CadastrarEvento cadastrarEvento) {
//		eventoService.cadastrarEvento(cadastrarEvento);
		eventoService.cadastrarEvento(cadastrarEvento);
		return ResponseEntity.ok().build();
	}
	
//	@PostMapping("/eventos")
//	public ResponseEntity<Evento> evento(@RequestBody Evento cadastrarEvento) {
//		eventoService.criarEvento(cadastrarEvento);
//		return ResponseEntity.ok().build();
//	}
	

	@PutMapping("/eventos/{idEvento}")
	public ResponseEntity<Evento> atualizarEvento(@PathVariable(value = "idEvento") Long idEvento,
			@Valid @RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.atualizarEvento(idEvento, evento));
	}

	@GetMapping("eventos/{nomeEvento}")
	public ResponseEntity<List<Evento>> buscarEventoPorNome(@PathVariable(value = "nomeEvento") String nomeEvento) {
		return ResponseEntity.ok(eventoService.buscarEventoPorNome(nomeEvento));

	}

//	@GetMapping("/eventos/{usuario}")
//	public List<Evento> buscarEventosPorUsuario(@RequestBody Evento evento) {
//		return eventoService.buscarEventosPorUsuario(evento.getOrganizador());
//	}

	@GetMapping("/eventos")
	public List<Evento> buscarTodosEventos(Evento evento) {
		return eventoService.buscarTodosEventos(evento);
	}
	
//	@GetMapping("/eventos")
//	public List<UsuarioEvento> buscarTodosEventos(UsuarioEvento usuarioEvento) {
//		return repositorio.findAll();
//	}

}