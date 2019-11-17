package br.com.bandtec.bora.evento.model.service;

import br.com.bandtec.bora.core.model.*;
import br.com.bandtec.bora.core.repository.CategoriaRepositorio;
import br.com.bandtec.bora.core.repository.PesquisaRepositorio;
import br.com.bandtec.bora.core.repository.UsuarioEventoRepositorio;
import br.com.bandtec.bora.evento.model.dto.CadastrarEvento;
import br.com.bandtec.bora.evento.model.dto.ParticiparEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.core.repository.EventoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
    private final EventoRepositorio repositorio;
    private final UsuarioEventoRepositorio usuarioEventoRepositorio;
    private final PesquisaRepositorio pesquisaRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;


    public Iterable<Evento> buscarEventos(Pageable pageable) {
        log.info("Listing all eventos");
        if (repositorio.findAll(pageable).isEmpty())
            new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return repositorio.findAll(Sort.by("titulo"));
    }

    public Evento buscarEventoPeloId(Long id) {
        log.info("Listing eventos");
        return repositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPeloTitulo(String titulo){
        log.info("Listing eventos");
        return repositorio.findByTitulo(titulo).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPelaCategoria(Categoria categoria){
        return repositorio.findByCategoria(categoria).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public Evento buscarEventoPeloOrganizador(Usuario organizador){
        return repositorio.findByOrganizador(organizador).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @Transactional
    public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
        Categoria categoria = categoriaRepositorio.findByNome(cadastrarEvento.getCategoria().getNome()).orElseThrow(
            ()->new ResponseStatusException(HttpStatus.NO_CONTENT));

        Evento evento = new Evento(cadastrarEvento.getTitulo(), cadastrarEvento.getDescricao(), cadastrarEvento.getEndereco(), categoria.getIdCategoria(), cadastrarEvento.getUsuario().getIdUsuario());
        repositorio.save(evento);
    }

//    public Categoria categoriaTeste(Categoria categoria){
//        return categoriaRepositorio.findByNome(categoria.getNome()).orElseThrow(
//            ()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    public void gravar(CadastrarEvento cadastrarEvento){
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setTitulo(cadastrarEvento.getTitulo());
        pesquisa.setDescricao(cadastrarEvento.getDescricao());
        pesquisa.setOrganizador(cadastrarEvento.getUsuario());
        pesquisaRepositorio.save(pesquisa);
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
