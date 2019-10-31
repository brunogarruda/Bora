package br.com.bandtec.bora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.Evento;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long>{
	
	List<Evento> findByNome(String nomeEvento);

	List<Evento> findByOrganizador(String organizador);
	
    //@Query(value = "SELECT u FROM Evento u where u.nome =:nome")
	//@Query(value = "select e from Evento e INNER JOIN Categoria c on c.idCategoria = e.idCategoria INNER JOIN SubCategoria sc on sc.idSubCategoria = c.idSubCategoria where sc.nome = :nome")
	//@Query(value = "select e,c,sc from Evento e, Categoria c, SubCategoria sc where c.idCategoria = sc.idSubCategoria and e.idEvento = c.idCategoria and e.idEvento = sc.idSubCategoria and sc.nome = :nome")
	
    @Query("select e from Evento e INNER JOIN Categoria c on e.idCategoria = c.idCategoria INNER JOIN SubCategoria sc on sc.categoriaIdFk = c.idCategoria where sc.nome =: nome")
	
    
	List<Evento> buscarEventoPorSubCategoria (@Param("nome") String nome);
	
//	List<Evento> findByOrganizador(String organizador); 
}
