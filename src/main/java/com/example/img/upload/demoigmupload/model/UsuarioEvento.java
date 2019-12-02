package com.example.img.upload.demoigmupload.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbd_participantes")
@Entity
@Data
public class UsuarioEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Usuario participante;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public UsuarioEvento(Long idEvento,Long idUsuario){
        this.evento = new Evento(idEvento);
        this.participante = new Usuario(idUsuario);
    }

    public UsuarioEvento(String apelido){
        this.participante = new Usuario(apelido);
    }
}
