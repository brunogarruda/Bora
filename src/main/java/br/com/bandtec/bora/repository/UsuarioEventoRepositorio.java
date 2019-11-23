package br.com.bandtec.bora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.UsuarioEvento;;

@Repository
public interface UsuarioEventoRepositorio extends JpaRepository<UsuarioEvento, Long> {

	List<UsuarioEvento> findByUsuario_idUsuario(Long idUsuario);

	List<UsuarioEvento> findByEvento_idEvento(Long idEvento);
	
	@Query(value = "select * from tbd_usuario_evento where usuario_id = :idUsuario and evento_id = :idEvento;", nativeQuery = true)
	UsuarioEvento buscaPorUsuarioEventoPeloIdUsuarioIdEvento(@Param("idUsuario") Long idUsuario, @Param("idEvento") Long idEvento);

}
