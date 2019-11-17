package br.com.bandtec.bora.core.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbd_participantes")
@Entity
public class UsuarioEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public UsuarioEvento(Long idEvento,Long idUsuario){
        this.evento = new Evento(idEvento);
        this.usuario = new Usuario(idUsuario);
    }
}
