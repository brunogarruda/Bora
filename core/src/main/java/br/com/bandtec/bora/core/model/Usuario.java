package br.com.bandtec.bora.core.model;

import lombok.*;
import javax.persistence.*;

@Table(name = "tbd_usuario")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String apelido;

    private String nome;

    private String email;

    private String celular;

    private String token;

    private String senha;

    public Usuario(String nome, String apelido, String email, String celular, String senha) {
        this.nome=nome;
        this.apelido=apelido;
        this.email=email;
        this.celular=celular;
        this.senha=senha;
    }


//    public Usuario(Usuario usuario) {
//        this.idUsuario = usuario.getIdUsuario();
//        this.apelido = usuario.getApelido();
//        this.nome=usuario.getNome();
//        this.email=usuario.getEmail();
//        this.celular=usuario.getCelular();
//        this.senha=usuario.getSenha();
//    }
}
