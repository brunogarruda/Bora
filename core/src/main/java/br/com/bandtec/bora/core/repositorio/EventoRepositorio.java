package br.com.bandtec.bora.core.repositorio;

import br.com.bandtec.bora.core.model.Evento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> {
	
	List<Evento> findByNome(String nomeEvento);

	List<Evento> findByOrganizador(String organizador); 
}
