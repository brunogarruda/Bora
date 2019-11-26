package br.com.bandtec.bora.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.bora.model.dto.CadastrarEventoDTO;
import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.enums.AvaliacaoEnum;
import br.com.bandtec.bora.model.form.ApelidoForm;
import br.com.bandtec.bora.model.service.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	
	@GetMapping("/eventosHome")
	public ResponseEntity<List<Evento>> buscarEventosHome(){
		return ResponseEntity.ok().body(eventoService.buscarEventosHome()); 
	}
	
	@PostMapping
	public ResponseEntity<CadastrarEventoDTO> cadastrarEvento(@RequestBody CadastrarEventoDTO cadastrarEvento) {
		eventoService.cadastrarEvento(cadastrarEvento);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Evento> buscarTodosEventos(Evento evento) {
		return eventoService.buscarTodosEventos(evento);
	}
	
	@GetMapping("/{idEvento}")
	public ResponseEntity<Evento> buscarEventoPorIdEvento(@PathVariable(value = "idEvento") Long idEvento) throws Exception {
		return ResponseEntity.ok(eventoService.buscarEventoPorIdEvento(idEvento));
	}

	@PostMapping("/{idEvento}")
	public ResponseEntity<?> participarEvento(@PathVariable(value = "idEvento") Long idEvento,String apelido) {
		return ResponseEntity.ok(eventoService.entrarEvento(idEvento, apelido));
	}

	@DeleteMapping("/sair/{idEvento}")
	public ResponseEntity<?> sairEvento(@PathVariable(value = "idEvento") Long idEvento,@RequestBody ApelidoForm apelido) {
		eventoService.sairEvento(idEvento, apelido);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/avaliar/{idEvento}")
	public ResponseEntity<?> avaliarEvento(@PathVariable(value = "idEvento") Long idEvento,@RequestBody AvaliacaoEnum nota) {
		eventoService.avaliarEvento(idEvento, nota);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idEvento}")
	public ResponseEntity<Evento> atualizarEvento(@PathVariable(value = "idEvento") Long idEvento,@Valid @RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.atualizarEvento(idEvento, evento));
	}

	@GetMapping("/evento/{nomeEvento}")
	public ResponseEntity<List<Evento>> buscarEventoPorNome(@PathVariable(value = "nomeEvento") String nomeEvento) throws Exception {
		return ResponseEntity.ok(eventoService.buscarEventoPorNome(nomeEvento));
	}
	
	@GetMapping("/sub-categoria/{subcategoriaIdFk}")
	public ResponseEntity<List<Evento>> buscarEventoPorSubCategoria(@PathVariable(value = "subcategoriaIdFk") Long subcategoriaIdFk) throws Exception {
		return ResponseEntity.ok(eventoService.buscarEventoPorSubCategoria(subcategoriaIdFk));
	}
	

//	@GetMapping("/{usuario}")
//	public List<Evento> buscarEventosPorUsuario(@RequestBody Evento evento) {
//		return eventoService.buscarEventosPorUsuario(evento.getOrganizador());
//	}

}