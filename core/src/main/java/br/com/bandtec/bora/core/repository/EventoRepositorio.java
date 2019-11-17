package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Categoria;
import br.com.bandtec.bora.core.model.TipoDeCategoria;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> {
    public Optional<List<Evento>> findByTitulo(String titulo);
    public Optional<List<Evento>> findByCategoria(Categoria categoria);
    public Optional<Evento> findByOrganizador(Usuario usuario);
}
