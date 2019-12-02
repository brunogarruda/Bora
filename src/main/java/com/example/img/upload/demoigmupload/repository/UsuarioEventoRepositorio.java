package com.example.img.upload.demoigmupload.repository;

import com.example.img.upload.demoigmupload.model.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioEventoRepositorio extends PagingAndSortingRepository<UsuarioEvento,Long> {
	Optional<List<UsuarioEvento>> findByEvento_idEvento(Long idEvento);
	Optional<List<UsuarioEvento>> findByParticipante_IdUsuario(Long idUsuario);
	Optional<UsuarioEvento> findUsuarioEventoByEvento_IdEventoAndParticipante_IdUsuario(Long idEvento, Long idUsuario);

	@Query(value = "select * from tbd_participantes where participante_id = :idUsuario and evento_id = :idEvento ;", nativeQuery = true)
	Optional<UsuarioEvento> buscaPorUsuarioEventoPeloIdUsuarioIdEvento(@Param("idUsuario") Long idUsuario, @Param("idEvento") Long idEvento);



}
