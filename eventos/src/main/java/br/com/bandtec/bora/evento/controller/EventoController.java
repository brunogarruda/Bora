package br.com.bandtec.bora.evento.controller;

import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.evento.model.dto.CadastrarEvento;
import br.com.bandtec.bora.evento.model.dto.EventoDTO;
import br.com.bandtec.bora.evento.model.dto.ParticiparEvento;
import br.com.bandtec.bora.evento.model.service.EventoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/eventos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para gerenciar eventos")
public class EventoController {
    private final EventoService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CadastrarEvento> cadastrarEvento(@Valid @RequestBody CadastrarEvento cadastrarEvento){
        service.cadastrarEvento(cadastrarEvento);
        service.gravar(cadastrarEvento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = "/com-foto/{idEvento}",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Evento> atualizarEventoComFoto(@PathVariable(value = "idEvento")Long idEvento, @RequestParam MultipartFile file, @RequestParam String evento) throws IOException {
        EventoDTO eventoDTO;
        try {
             eventoDTO = new ObjectMapper().readValue(evento,EventoDTO.class);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ResponseStatusException re){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizarEventoComFoto(file,idEvento,eventoDTO);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{idEvento}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> atualizarEvento(@PathVariable(value = "idEvento")Long idEvento, @RequestBody EventoDTO eventoDTO){
//        try {
//            service.atualizarEvento(eventoDTO);
//        }catch (ResponseStatusException re){
//            return ResponseEntity.status(re.getStatus()).body(re.getMessage());
//        } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
        service.atualizarEvento(idEvento,eventoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{idEvento}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ParticiparEvento> entrarEvento(@PathVariable("idEvento")Long idEvento, @Valid @RequestBody ParticiparEvento participarEvento){
        if(participarEvento.getUsuario()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            service.entrarEvento(idEvento,participarEvento);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Evento>> list(Pageable pageable) throws Exception {
        return new ResponseEntity<>(service.buscarEventos(pageable), HttpStatus.OK);
    }
//
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPeloId(@PathVariable("id") Long id) {
        Evento evento = service.buscarEventoPeloId(id);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Evento>> buscarEventoPeloTitulo(@PathVariable("titulo")String titulo){
        return ResponseEntity.ok(service.buscarEventoPeloTitulo(titulo));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Evento>> buscarEventoPorCategoria(@PathVariable("categoria") String categoria){
        return ResponseEntity.ok(service.buscarEventoPelaCategoria(categoria));
    }

    @GetMapping("/organizador/{organizador}")
    public ResponseEntity<List<Evento>> buscarEventoPeloOrganizador(@PathVariable("organizador")String organizador){
        return ResponseEntity.ok(service.buscarEventoPeloOrganizador(organizador));
    }

    @GetMapping("/participantes/{id}")
    public ResponseEntity<Evento> buscaParticipantesDoEvento(@PathVariable("id")Long id){
        try {
            return new ResponseEntity<>(service.retornaParticipantes(id),HttpStatus.OK);
        }catch (ResponseStatusException re){
            throw new ResponseStatusException(re.getStatus(),re.getMessage());
        }
    }


//    @GetMapping("teste")
//    public ResponseEntity<Categoria> teste(Categoria categoria){
//        return ResponseEntity.ok(service.categoriaTeste(categoria));
//    }

}
