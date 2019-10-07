package br.com.bandtec.bora.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByIdUsuario(String usuario);

	Usuario findByApelido(String usuario);

}
