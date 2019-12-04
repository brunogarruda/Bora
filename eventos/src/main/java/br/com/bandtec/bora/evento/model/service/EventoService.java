package br.com.bandtec.bora.evento.model.service;

import br.com.bandtec.bora.core.model.*;
import br.com.bandtec.bora.core.repository.*;
import br.com.bandtec.bora.evento.model.dto.CadastrarEvento;
import br.com.bandtec.bora.evento.model.dto.EventoDTO;
import br.com.bandtec.bora.evento.model.dto.ParticiparEvento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
    private final EventoRepositorio repositorio;
    private final UsuarioEventoRepositorio usuarioEventoRepositorio;
    private final PesquisaRepositorio pesquisaRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;


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

    public List<Evento> buscarEventoPelaCategoria(String categoria){
        return repositorio.findEventoByCategoriaNome(categoria).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPeloOrganizador(String organizador){
        return repositorio.findEventoByOrganizadorApelido(organizador).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @Transactional
    public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
        Usuario usuario = usuarioRepositorio.findByApelido(cadastrarEvento.getUsuario().getApelido()).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NO_CONTENT));

        Evento evento = new Evento(
            cadastrarEvento.getTitulo(),
            cadastrarEvento.getRua(),
            cadastrarEvento.getNumero(),
            cadastrarEvento.getCep(),
            cadastrarEvento.getBairro(),
            cadastrarEvento.getLatitude(),
            cadastrarEvento.getLongitude(),
            usuario.getIdUsuario());
        repositorio.save(evento);
    }

    @Transactional
    public void atualizarEventoComFoto(MultipartFile file, Long idEvento,EventoDTO eventoDTO) throws IOException {
        ImageModel imageModel = new ImageModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        Categoria categoria = categoriaRepositorio.findByNome(eventoDTO.getCategoria().getNome()).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NO_CONTENT));

        repositorio.findById(idEvento).map(evento->{
            Evento atualizaEvento =  new Evento(
                evento.getIdEvento(),
                eventoDTO.getTitulo(),
                eventoDTO.getDescricao(),
                eventoDTO.getDataInicio(),
                eventoDTO.getDataFim(),
                eventoDTO.getHoraInicio(),
                eventoDTO.getHoraFim(),
                eventoDTO.getRua(),
                eventoDTO.getNumero(),
                eventoDTO.getCep(),
                eventoDTO.getBairro(),
                eventoDTO.getLatitude(),
                eventoDTO.getLongitude(),
                imageModel,
                categoria.getIdCategoria());
            return repositorio.save(atualizaEvento);
        });
    }


    @Transactional
    public void atualizarEvento(Long idEvento, EventoDTO eventoDTO) {
        repositorio.findById(idEvento).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
        Categoria categoria = categoriaRepositorio.findByNome(eventoDTO.getCategoria().getNome()).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
        repositorio.findById(idEvento).map(evento->{
           Evento atualizaEvento =  new Evento(
               evento.getIdEvento(),
               eventoDTO.getTitulo(),
               eventoDTO.getDescricao(),
               eventoDTO.getDataInicio(),
               eventoDTO.getDataFim(),
               eventoDTO.getHoraInicio(),
               eventoDTO.getHoraFim(),
               eventoDTO.getRua(),
               eventoDTO.getNumero(),
               eventoDTO.getCep(),
               eventoDTO.getBairro(),
               eventoDTO.getLatitude(),
               eventoDTO.getLongitude(),
               categoria.getIdCategoria());
            return repositorio.save(atualizaEvento);
        });
    }

    public void gravar(CadastrarEvento cadastrarEvento){
        Pesquisa pesquisa = new Pesquisa();
        pesquisa.setIdEvento(cadastrarEvento.getIdEvento());
        pesquisa.setTitulo(cadastrarEvento.getTitulo());
        pesquisa.setDescricao(cadastrarEvento.getDescricao());
        pesquisa.setOrganizador(cadastrarEvento.getUsuario().getApelido());
        pesquisaRepositorio.save(pesquisa);
    }

//    @Transactional
//    public void entrarEvento(ParticiparEvento participarEvento){
//        Long idEvento = participarEvento.getEvento().getIdEvento();
//        String apelido = participarEvento.getUsuario().getApelido();
//        Evento evento = repositorio.findById(idEvento).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
//        Usuario usuario = usuarioRepositorio.findByApelido(apelido).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
//        UsuarioEvento usuarioEvento = new UsuarioEvento(evento.getIdEvento(),usuario.getIdUsuario());
//        repositorio.
//            findByOrganizadorIdUsuario(usuario.getIdUsuario()).
//            orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
//
//        usuarioEventoRepositorio.save(usuarioEvento);
//    }

public void entrarEvento(Long idEvento,ParticiparEvento participarEvento){
    Evento evento = repositorio.findById(idEvento).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
    Usuario usuario = usuarioRepositorio.findByApelido(participarEvento.getUsuario().getApelido()).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT));
    UsuarioEvento usuarioEvento = new UsuarioEvento(evento.getIdEvento(),usuario.getIdUsuario());
    Optional<List<Evento>> list = repositorio.verificaSeTemEvento(usuario.getIdUsuario(),idEvento);
    if(list.isPresent())
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    if(!usuarioEventoRepositorio.findUsuarioEventoByEvento_IdEventoAndParticipante_IdUsuario(idEvento,usuario.getIdUsuario()).isEmpty())
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    usuarioEventoRepositorio.save(usuarioEvento);
}

    public Evento retornaParticipantes(Long id) {
        Evento evento = repositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        List<UsuarioEvento> usuarioEventos = usuarioEventoRepositorio.findByEvento_idEvento(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        List<Usuario> participantes = new ArrayList<>();

        for (UsuarioEvento value : usuarioEventos) {
            Usuario usuario = value.getParticipante();

            if (usuario != null)
                participantes.add(usuario);
        }

        if (participantes.isEmpty())
            evento.setParticipantes(null);

        else
            evento.setParticipantes(participantes);

        return evento;
    }

//    public Evento entrarEvento(Long idEvento, ParticiparEvento participarEvento){
//        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(),participarEvento.getUsuario().getIdUsuario());
//        Evento evento = repositorio.findById(idEvento).orElse(null);
//        usuarioEventoRepositorio.save(usuarioEvento);
//        return evento;
//    }

//    public void entrarEvento(ParticiparEvento participarEvento) {
//        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(), participarEvento.getUsuario().getIdUsuario());
//        if (usuarioEvento.getEvento().getIdEvento()==null || usuarioEvento.getUsuario().getIdUsuario()==null)
//            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        else if(usuarioEvento.getUsuario()==null||usuarioEvento.getEvento()==null)
//            new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//
//        usuarioEventoRepositorio.save(usuarioEvento);
//    }
}
