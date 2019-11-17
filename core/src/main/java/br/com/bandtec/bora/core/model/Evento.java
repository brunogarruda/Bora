package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne
    @JoinColumn(name = "fk_categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonBackReference
    @JoinColumn(name = "organizador_id")
    private Usuario organizador;

    public Evento(Long idEvento) {
        this.idEvento=idEvento;
    }

    public Evento(String titulo, String descricao, String endereco, Long categoria, Long idUsuario) {
        this.titulo = titulo;
        this.descricao=descricao;
        this.endereco=endereco;
        this.categoria= new Categoria(categoria);
        this.organizador = new Usuario(idUsuario);
    }

}
