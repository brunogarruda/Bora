package br.com.bandtec.bora.evento.controller;

import br.com.bandtec.bora.core.model.Categoria;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.evento.model.dto.CadastrarEvento;
import br.com.bandtec.bora.evento.model.dto.ParticiparEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.bandtec.bora.evento.model.service.EventoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.validation.Valid;
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
        //service.gravar(cadastrarEvento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{idEvento}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ParticiparEvento> entrarEvento(@PathVariable("idEvento")Long idEvento, @Valid @RequestBody ParticiparEvento participarEvento){

        if (service.buscarEventoPeloId(idEvento)==null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        service.entrarEvento(participarEvento);

        if(participarEvento.getUsuario()==null||participarEvento.getEvento()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
    public ResponseEntity<List<Evento>> buscarEventoPorCategoria(@PathVariable("categoria") Categoria categoria){
        return ResponseEntity.ok(service.buscarEventoPelaCategoria(categoria));
    }

    @GetMapping("/organizador/{organizador}")
    public ResponseEntity<Evento> buscarEventoPeloOrganizador(@PathVariable("organizador")Usuario organizador){
        return ResponseEntity.ok(service.buscarEventoPeloOrganizador(organizador));
    }

//    @GetMapping("teste")
//    public ResponseEntity<Categoria> teste(Categoria categoria){
//        return ResponseEntity.ok(service.categoriaTeste(categoria));
//    }

}
