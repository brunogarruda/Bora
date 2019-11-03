package br.com.bandtec.bora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Usuario findByIdUsuario(String usuario);
	
	@Query("from Usuario a where a.apelido = :login")
	Usuario findByApelido(@Param("login") String login);
	
	@Query("from Usuario a where a.email = :login")
	Usuario findByEmail(@Param("login") String login);
	
}

