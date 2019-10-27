package br.com.bandtec.bora.core.model;

import lombok.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@Table(name = "tbd_evento")
@Entity
public class Evento implements AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = 2841106963046964316L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cod_evento", unique = true)
    private String codigoEvento;

    @NotNull(message = "Preenchimento obrigatorio")
    @Column(nullable = false)
    private String nome;

    @Column(name = "data_inicio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @Column(name = "data_fim")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    @Column(name = "descricao")
    private String descricaoEvento;

    @Column(name = "is_privado")
    private boolean isPrivado;

    private String senha;

    private String endereco;

    private String categoria;

    // private Categoria categoria;

    // @Embedded
    // private Endereco endereco;

}
