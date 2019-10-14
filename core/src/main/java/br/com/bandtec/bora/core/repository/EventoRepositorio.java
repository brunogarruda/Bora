package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Evento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> { }
