package br.com.bandtec.bora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bandtec.bora.model.dto.CadastrarEvento;
import br.com.bandtec.bora.model.entity.UsuarioEvento;;

@Repository
public interface UsuarioEventoRepositorio extends JpaRepository<UsuarioEvento, Long> {

}
