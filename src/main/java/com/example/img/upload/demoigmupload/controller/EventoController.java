package com.example.img.upload.demoigmupload.controller;

import com.example.img.upload.demoigmupload.dto.ApelidoForm;
import com.example.img.upload.demoigmupload.dto.CadastrarEvento;
import com.example.img.upload.demoigmupload.dto.EventosRespostaDTO;
import com.example.img.upload.demoigmupload.dto.ParticiparEvento;
import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.UsuarioEvento;
import com.example.img.upload.demoigmupload.services.EventoService;
import com.example.img.upload.demoigmupload.services.UsuarioEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/eventos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoController {

    private final EventoService service;
    private final UsuarioEventoService usuarioEventoService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CadastrarEvento> cadastrarEvento(@Valid @RequestBody CadastrarEvento cadastrarEvento){
        service.cadastrarEvento(cadastrarEvento);
        //service.gravar(cadastrarEvento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{idEvento}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ParticiparEvento> entrarEvento(@PathVariable("idEvento")Long idEvento, @Valid @RequestBody ParticiparEvento participarEvento){

        if (service.buscarPeloIdEvento(idEvento)==null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        service.entrarEvento(participarEvento);

        if(participarEvento.getUsuario()==null||participarEvento.getEvento()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Evento>> list(Pageable pageable){
        return new ResponseEntity<>(service.buscarEventos(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPeloId(@PathVariable("id") Long id) {
        Evento evento = service.buscarPeloIdEvento(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/participantes/{id}")
    public ResponseEntity<Evento> buscaParticipantesDoEvento(@PathVariable("id")Long id){
        try {
            return new ResponseEntity<>(service.retornaParticipantes(id),HttpStatus.OK);
        }catch (ResponseStatusException re){
            throw new ResponseStatusException(re.getStatus(),re.getMessage());
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Evento>> buscarEventoPeloTitulo(@PathVariable(value = "titulo")String titulo){
        return ResponseEntity.ok(service.buscarEventoPeloTitulo(titulo));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Evento>> buscarEventoPorCategoria(@PathVariable(value = "categoria") String categoria){
        return ResponseEntity.ok(service.buscarEventoPelaCategoria(categoria));
    }

    @GetMapping("/organizador/{organizador}")
    public ResponseEntity<List<Evento>> buscarEventoPeloOrganizador(@PathVariable(value = "organizador") String organizador){
        return ResponseEntity.ok(service.buscarEventoPeloOrganizador(organizador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioEvento> deletarEvento(@PathVariable("id") Long id){
        usuarioEventoService.sairEvento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sair/{idEvento}")
    public ResponseEntity<Void> sairEvento(@PathVariable(value = "idEvento")Long idEvento, @RequestBody ApelidoForm apelidoForm){
        try {
            service.sairDoEvento(idEvento,apelidoForm);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("")
}
