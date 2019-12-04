package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
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
    @Email
    private String email;

    private String celular;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "fk_foto_perfil_id")
    private ImageModel fotoPerfil;

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Evento> eventosOrganizados;

    @Transient
    private List<Evento> eventosQueParticipo;

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

    public Usuario(String apelido) {
        this.apelido=apelido;
    }
    public Usuario(String nome, String apelido, String email, String celular, String senha, ImageModel fotoPerfil) {
        this.nome=nome;
        this.apelido=apelido;
        this.email=email;
        this.celular=celular;
        this.senha=senha;
        this.fotoPerfil = fotoPerfil;
    }

    public Usuario(String apelido, String email, String senha) {
        this.apelido=apelido;
        this.email=email;
        this.senha=senha;
    }
}
