package br.com.bandtec.bora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.Evento;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long>{
	
	List<Evento> findByNome(String nomeEvento);

	List<Evento> findByOrganizador(String organizador); 
}
