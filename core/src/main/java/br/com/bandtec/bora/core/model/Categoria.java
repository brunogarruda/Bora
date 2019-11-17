package br.com.bandtec.bora.core.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_de_categoria_id")
    private TipoDeCategoria tipoDeCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<Evento> evento;

    public Categoria(Long idCategoria){
        this.idCategoria=idCategoria;
    }

    public Categoria(String nome){
        this.nome=nome;
    }
}
