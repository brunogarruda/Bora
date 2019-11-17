package br.com.bandtec.bora.core.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbd_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(unique = true)
    private String apelido;

    private String nome;

    @Column(unique = true)
    private String email;

    private String celular;

    private String senha;

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventosOrganizados;

    public Usuario(Long idUsuario) {
        this.idUsuario=idUsuario;
    }

    public Usuario(String nome, String apelido, String email, String celular, String senha) {
        this.nome=nome;
        this.apelido=apelido;
        this.email=email;
        this.celular=celular;
        this.senha=senha;
    }
}
