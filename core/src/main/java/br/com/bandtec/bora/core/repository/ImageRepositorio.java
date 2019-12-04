package br.com.bandtec.bora.core.repository;

import br.com.bandtec.bora.core.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositorio extends JpaRepository<ImageModel,Long> {
}
