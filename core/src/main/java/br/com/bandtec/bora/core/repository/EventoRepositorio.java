package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepositorio extends PagingAndSortingRepository<Evento, Long> {
    public Optional<List<Evento>> findByTitulo(String titulo);
    public Optional<List<Evento>> findEventoByOrganizadorApelido(String apelido);
    public Optional<List<Evento>> findEventoByCategoriaNome(String categoria);
    public List<Evento> findEventoByOrganizadorIdUsuario(Long idUsuario);
//    @Query(value = "select e from Evento e,Usuario u where e.idEvento = :idEvento and u.idUsuario = :idUsuario")
    @Query(value = "select * from tbd_evento as e, tbd_usuario as u where e.organizador_id =:idUsuario and u.id_usuario=:idUsuario and e.id_evento =:idEvento", nativeQuery = true)
    public Optional<List<Evento>> verificaSeTemEvento(@Param("idUsuario")Long idUsuario, @Param("idEvento")Long idEvento);
}
//select e from Evento e where e.idEvento
