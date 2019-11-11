package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioEventoRepositorio extends JpaRepository<UsuarioEvento,Long> {
}
