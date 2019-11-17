package br.com.bandtec.bora.evento.model.service;

import br.com.bandtec.bora.core.model.UsuarioEvento;
import br.com.bandtec.bora.core.repository.UsuarioEventoRepositorio;
import br.com.bandtec.bora.evento.model.dto.CadastrarEvento;
import br.com.bandtec.bora.evento.model.dto.ParticiparEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.core.repository.EventoRepositorio;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
    private final EventoRepositorio repositorio;
    private final UsuarioEventoRepositorio usuarioEventoRepositorio;
    private final MongoOperations operations;


    public Iterable<Evento> buscarEventos(Pageable pageable) throws Exception {
        log.info("Listing all eventos");
        if (repositorio.findAll(pageable).isEmpty())
            throw new Exception("Nenhum evento encontrado");

        return repositorio.findAll(Sort.by("nome"));
    }

    public Evento buscarEventoPeloId(Long id) {
        log.info("Listing eventos");
        return repositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Evento nao encontrado com o id:" + id));
    }

    @Transactional
    public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
        Evento evento = new Evento(cadastrarEvento.getTitulo(), cadastrarEvento.getDescricao(), cadastrarEvento.getEndereco(), cadastrarEvento.getCategoria(), cadastrarEvento.getUsuario().getIdUsuario());
        operations.save(evento);
        repositorio.save(evento);
    }

//    @Transactional
//    public void entrarEvento(ParticiparEvento participarEvento){
//        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(),participarEvento.getUsuario().getIdUsuario());
//        usuarioEventoRepositorio.save(usuarioEvento);
//    }

//    public Evento entrarEvento(Long idEvento, ParticiparEvento participarEvento){
//        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(),participarEvento.getUsuario().getIdUsuario());
//        Evento evento = repositorio.findById(idEvento).orElse(null);
//        usuarioEventoRepositorio.save(usuarioEvento);
//        return evento;
//    }

    public void entrarEvento(ParticiparEvento participarEvento) {
        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(), participarEvento.getUsuario().getIdUsuario());
        if (usuarioEvento.getEvento().getIdEvento()==null || usuarioEvento.getUsuario().getIdUsuario()==null)
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else if(usuarioEvento.getUsuario()==null||usuarioEvento.getEvento()==null)
            new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        usuarioEventoRepositorio.save(usuarioEvento);
    }
}
