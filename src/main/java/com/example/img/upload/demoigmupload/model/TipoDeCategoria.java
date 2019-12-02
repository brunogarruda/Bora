package com.example.img.upload.demoigmupload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbd_tipo_categoria")
@Entity
public class TipoDeCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCategoria;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_icone_id")
    private ImageModel icone;

//    @OneToMany(mappedBy = "tipoDeCategoria")
//    @JsonManagedReference(value = "categoria-tipoCategoria")
//    private List<Categoria> categoria;
}
