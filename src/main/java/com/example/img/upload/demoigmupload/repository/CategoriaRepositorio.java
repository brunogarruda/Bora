package com.example.img.upload.demoigmupload.repository;

import com.example.img.upload.demoigmupload.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
    public Optional<Categoria> findByNome(String categoria);
}
