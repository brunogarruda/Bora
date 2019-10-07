package br.com.bandtec.bora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.bandtec.bora.model.entity.Categoria;;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long>{
	
	List<Categoria> findAll();
}
