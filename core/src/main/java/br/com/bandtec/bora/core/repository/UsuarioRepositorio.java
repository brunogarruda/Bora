package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {
    Optional<Usuario> findByApelido(String apelido);

    @Query("select u from Usuario u where u.apelido =:apelido and u.senha =:senha")
    public Usuario validaLoginESenha(@Param("apelido") String apelido, @Param("senha") String senha);


}
