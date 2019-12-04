package br.com.bandtec.bora.core.model;

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
    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_de_categoria_id")
    private TipoDeCategoria tipoDeCategoria;


    public Categoria(Long idCategoria){
        this.idCategoria=idCategoria;
    }

    public Categoria(String nome){
        this.nome=nome;
    }
}
