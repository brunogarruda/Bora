package com.example.img.upload.demoigmupload.repository;

import com.example.img.upload.demoigmupload.model.Categoria;
import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> {
    public Optional<List<Evento>> findByTitulo(String titulo);
//    public Optional<List<Evento>> findEventoByCategoria(String categoria);
    public Optional<List<Evento>> findEventoByCategoriaNome(String categoria);



    @Query("select e, u from Evento e, Usuario u where e.organizador = u.apelido and e.organizador = :organizador")
    public List<Evento> encontrarOrganizadorDoEvento(@Param("organizador") String organizador);

    //   Iterable<Evento> findAll(Pageable pageable, Sort titulo);
}
