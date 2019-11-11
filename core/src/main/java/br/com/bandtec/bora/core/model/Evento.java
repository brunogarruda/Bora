package br.com.bandtec.bora.core.model;

import lombok.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbd_evento")
public class Evento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String titulo;

    private String dataInicio;

    private String dataFim;

    private String descricao;

    private String senha;

    private String endereco;

    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "organizador_id")
    private Usuario organizador;

    public Evento(Long idEvento) {
        this.idEvento=idEvento;
    }

    public Evento(String titulo, String descricao, String endereco, String categoria, Long idUsuario) {
        this.titulo = titulo;
        this.descricao=descricao;
        this.endereco=endereco;
        this.categoria=categoria;
        this.organizador = new Usuario(idUsuario);
    }

}
