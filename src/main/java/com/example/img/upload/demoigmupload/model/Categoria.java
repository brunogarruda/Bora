package com.example.img.upload.demoigmupload.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbd_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @JsonProperty
    //@NotEmpty(message = "Não esqueça de selecionar a categoria, Obrigado !")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_de_categoria_id")
//    @JsonBackReference(value ="categoria-tipoCategoria")
    private TipoDeCategoria tipoDeCategoria;

//    @OneToMany(mappedBy = "categoria")
//    @JsonManagedReference(value = "categoria-evento")
//    private List<Evento> evento;

    public Categoria(Long idCategoria){
        this.idCategoria=idCategoria;
    }

    public Categoria(String nome){
        this.nome=nome;
    }
}
