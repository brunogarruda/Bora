package br.com.bandtec.bora.core.repositorio;

import br.com.bandtec.bora.model.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByIdUsuario(String usuario);

	Usuario findByApelido(String usuario);

}
