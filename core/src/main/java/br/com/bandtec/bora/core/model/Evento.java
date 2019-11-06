package br.com.bandtec.bora.core.model;

import lombok.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@Table(name = "tbd_evento")
@Entity
public class Evento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
    private Long id;

//    @Column(name = "cod_evento", unique = true)
//    private String codigoEvento;

    @NotNull(message = "Preenchimento obrigatorio")
    @Column(nullable = false)
    private String nome;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_fim")
    private String dataFim;

    @Column(name = "descricao")
    private String descricaoEvento;

    private String senha;

    private String endereco;

    private String categoria;

}
