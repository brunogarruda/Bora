package br.com.bandtec.bora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.UsuarioEvento;;

@Repository
public interface UsuarioEventoRepositorio extends JpaRepository<UsuarioEvento, Long> {

	List<UsuarioEvento> findByUsuario_idUsuario(Long idUsuario);

	List<UsuarioEvento> findByEvento_idEvento(Long idEvento);

}
