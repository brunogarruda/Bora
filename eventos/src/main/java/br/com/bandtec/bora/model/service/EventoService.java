package br.com.bandtec.bora.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.dto.CadastrarEvento;
import br.com.bandtec.bora.model.entity.UsuarioEvento;
import br.com.bandtec.bora.repository.UsuarioEventoRepositorio;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventoService {
	private final EventoRepositorio eventoRepositorio;

	public Iterable<Eventos> list(Pageable pageable) {
		log.info("Listing all eventos");
		return eventoRepositorio.findAll(pageable);
	}
}
