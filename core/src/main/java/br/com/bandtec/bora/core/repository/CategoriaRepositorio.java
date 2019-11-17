package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
    public Optional<Categoria> findByNome (String categoria);
}
