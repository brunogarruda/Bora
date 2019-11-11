package br.com.bandtec.bora.evento.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.evento.model.service.EventoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.validation.Valid;
v1/api/login
@RestController
@RequestMapping("/v1/api/eventos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para gerenciar eventos")
public class EventoController {

    private final EventoService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Evento>> list(Pageable pageable) throws Exception {
        return new ResponseEntity<>(service.buscarEventos(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPeloId(@PathVariable("id") Long id) {
        Evento evento = service.buscarEventoPeloId(id);
        return ResponseEntity.ok(evento);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Evento> cadastrarEvento(@Valid @RequestBody Evento evento) {
		service.cadastrarEvento(evento);
		return ResponseEntity.ok().build();
	}

}
