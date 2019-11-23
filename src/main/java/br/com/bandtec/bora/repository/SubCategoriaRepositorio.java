package br.com.bandtec.bora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bandtec.bora.model.entity.SubCategoria;

@Repository
public interface SubCategoriaRepositorio extends JpaRepository<SubCategoria, Long> {}
