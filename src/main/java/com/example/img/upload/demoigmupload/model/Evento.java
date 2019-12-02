package com.example.img.upload.demoigmupload.model;

import com.example.img.upload.demoigmupload.dto.EventosRespostaDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbd_evento")
public class Evento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

//    @Temporal(TemporalType.TIME)
    private LocalTime horaInicio;

//    @Temporal(TemporalType.)
    private LocalTime horaFim;

    private String descricao;

    private String senha;

    private String endereco;

    @ManyToOne
    @JoinColumn(name = "fk_capa_evento_id")
    private ImageModel capaDoEvento;

    @ManyToOne
    @JoinColumn(name = "fk_categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "organizador_id")
   @JsonBackReference
    private Usuario organizador;

    //    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}
    @Transient
    private List<Usuario> participantes;

//    @OneToMany(mappedBy = "participante",orphanRemoval = true)
//    @JsonManagedReference
//    private List<UsuarioEvento> participantes;


    public Evento(Long idEvento) {
        this.idEvento=idEvento;
    }

    public Evento(String titulo, String descricao,String endereco,LocalDate dataInicio , LocalDate dataFim,
                  LocalTime horaInicio,LocalTime horaFim, Long categoria, Long apelido) {
        this.titulo = titulo;
        this.descricao=descricao;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.endereco=endereco;
        this.categoria= new Categoria(categoria);
        this.organizador = new Usuario(apelido);
    }
}
