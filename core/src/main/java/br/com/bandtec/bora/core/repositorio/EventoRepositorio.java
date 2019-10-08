package br.com.bandtec.bora.core.repositorio;

import br.com.bandtec.bora.core.model.Evento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> { }
