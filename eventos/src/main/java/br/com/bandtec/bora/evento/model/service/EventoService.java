package br.com.bandtec.bora.evento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Iterable<Evento> buscarEventos(Pageable pageable) throws Exception {
		log.info("Listing all eventos");
		if (eventoRepositorio.findAll(pageable).isEmpty())
			throw new Exception("Nenhum evento encontrado");

		return eventoRepositorio.findAll(Sort.by("nome"));
	}

	public Evento buscarEventoPeloId(Long id) {
		log.info("Listing eventos");
		return eventoRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado com o id:" + id));
	}

}
