package br.com.bandtec.bora.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.core.repository.EventoRepositorio;
import br.com.bandtec.bora.core.model.Evento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
	private final EventoRepositorio eventoRepositorio;

	public Iterable<Evento> buscarEventos(Pageable pageable) {
		log.info("Listing all eventos");
		return eventoRepositorio.findAll(pageable);
	}
}
