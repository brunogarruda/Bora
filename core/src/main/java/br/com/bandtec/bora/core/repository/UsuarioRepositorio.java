package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByApelido(String apelido);

}
