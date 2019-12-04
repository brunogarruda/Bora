package br.com.bandtec.bora.core.model;

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
    @Column(name = "id_tipo_categoria")
    private Long idTipoCategoria;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_icone_id")
    private ImageModel icone;
}
