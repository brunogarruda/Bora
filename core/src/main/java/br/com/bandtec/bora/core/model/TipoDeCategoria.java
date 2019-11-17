package br.com.bandtec.bora.core.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @OneToMany(mappedBy = "tipoDeCategoria")
    private List<Categoria> categoria;
}
