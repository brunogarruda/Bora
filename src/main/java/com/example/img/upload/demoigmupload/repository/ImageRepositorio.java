package com.example.img.upload.demoigmupload.repository;

import com.example.img.upload.demoigmupload.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositorio extends JpaRepository<ImageModel,Long> {
}
