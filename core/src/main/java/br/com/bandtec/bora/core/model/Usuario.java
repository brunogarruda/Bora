package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tbd_usuario")
@Entity
@Builder
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String codigoUsuario;

    private String apelido;

    private String nome;

    private String email;

    private String celular;

    private String token;

    private String senha;
}
