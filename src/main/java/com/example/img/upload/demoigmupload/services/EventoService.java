package com.example.img.upload.demoigmupload.services;

import com.example.img.upload.demoigmupload.dto.*;
import com.example.img.upload.demoigmupload.model.Categoria;
import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.example.img.upload.demoigmupload.model.UsuarioEvento;
import com.example.img.upload.demoigmupload.repository.CategoriaRepositorio;
import com.example.img.upload.demoigmupload.repository.EventoRepositorio;
import com.example.img.upload.demoigmupload.repository.UsuarioEventoRepositorio;
import com.example.img.upload.demoigmupload.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
    private final EventoRepositorio repositorio;
    private final UsuarioEventoRepositorio usuarioEventoRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

//    public Iterable<EventosRespostaDTO> ddd() {
//        List<EventosRespostaDTO> respostaDTOList = new ArrayList<>();
//        EventosRespostaDTO respostaDTO = new EventosRespostaDTO();
//        Iterable<Evento> evento =repositorio.findAll();
//        respostaDTO.setEventoList(evento);
//        EventoDTOList dtoList = new EventoDTOList();
//        respostaDTO.getEventoList().forEach(er->new EventoDTOList(er.getTitulo(),er.getEndereco()));
//        respostaDTOList.add(respostaDTO);
//
//        return respostaDTOList;
//    }

//    public Iterable<Evento> todosEventos() {
//        log.info("Listing all eventos");
//        return repositorio.findAll();
//    }

    public Iterable<Evento> buscarEventos(Pageable pageable) {
        log.info("Listing all eventos");
        if (repositorio.findAll(pageable).isEmpty())
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return repositorio.findAll(Sort.by("titulo"));
    }

    public Evento buscarEventoPeloId(Long id) {
        log.info("Listing eventos");
        return retornaParticipantes(id);
    }


    public Evento buscarPeloIdEvento(Long id) {
        log.info("Listing eventos");
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPeloTitulo(String titulo) {
        log.info("Listing eventos");
        return repositorio.findByTitulo(titulo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPelaCategoria(String categoria) {
        return repositorio.findEventoByCategoriaNome(categoria)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    public List<Evento> buscarEventoPeloOrganizador(String organizador) {
        return repositorio.encontrarOrganizadorDoEvento(organizador);
    }

    @Transactional
    public void cadastrarEvento(CadastrarEvento cadastrarEvento) {
        Usuario usuario = usuarioRepositorio.findByApelido(cadastrarEvento.getUsuario().getApelido()).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
        Categoria categoria =
                categoriaRepositorio.findByNome(cadastrarEvento.getCategoria().getNome())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

        Evento evento = new Evento(
                cadastrarEvento.getTitulo(), cadastrarEvento.getDescricao(),
                cadastrarEvento.getEndereco(),cadastrarEvento.getDataInicio(),cadastrarEvento.getDataFim(),
                cadastrarEvento.getHoraInicio(),cadastrarEvento.getHoraFim(),
                categoria.getIdCategoria(),usuario.getIdUsuario());
        repositorio.save(evento);
    }


    public void entrarEvento(ParticiparEvento participarEvento) {
        UsuarioEvento usuarioEvento = new UsuarioEvento(participarEvento.getEvento().getIdEvento(),
                participarEvento.getUsuario().getIdUsuario());

        if (usuarioEvento.getEvento().getIdEvento() == null
                || usuarioEvento.getParticipante().getIdUsuario() == null)
            new ResponseEntity< >(HttpStatus.BAD_REQUEST);
        else if (usuarioEvento.getParticipante()== null || usuarioEvento.getEvento() == null)
            new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

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

    public void sairDoEvento(Long idEvento, ApelidoForm apelidoForm){
        Long idUsuario = usuarioRepositorio.findByApelido(apelidoForm.getApelido()).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT)).getIdUsuario();
        idEvento = repositorio.findById(idEvento).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT)).getIdEvento();
        Long idUsuarioEvento = usuarioEventoRepositorio.buscaPorUsuarioEventoPeloIdUsuarioIdEvento(idUsuario,idEvento).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT)).getId();
        usuarioEventoRepositorio.deleteById(idUsuarioEvento);
    }
}
