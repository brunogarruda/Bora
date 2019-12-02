package com.example.img.upload.demoigmupload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@Table(name = "tbd_imagens")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    @Lob
    private byte[] pic;


    public ImageModel() {
    }

    public ImageModel(String originalFilename, String contentType, byte[] bytes) {
        this.nome = originalFilename;
        this.tipo=contentType;
        this.pic=bytes;
    }
}
