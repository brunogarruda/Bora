package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbd_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@EqualsAndHashCode.Include
	private Long idUsuario;

	@NotNull
	@Size(min = 2, message = "Minino duas letras")
	@JsonProperty
	private String nome;

	@Column(unique = true, length = 50)
	@NotNull
	@Size(min = 2, message = "Minino duas letras")
	@JsonProperty
	private String apelido;

	@Email
	@NotEmpty
	@JsonProperty
	private String email;

	@NotEmpty
	private String celular;

	@NotEmpty
	@Size(min = 5, message = "Minino de cinco caracteres")
	@JsonIgnore
	@ToString.Exclude
	private String senha;

	@NotEmpty
	private String role = "USUARIO";

	@OneToMany(mappedBy = "organizador")
	private List<Evento> eventosCriado;

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(@NotNull Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.apelido = usuario.getApelido();
		this.senha = usuario.getSenha();
		this.role = usuario.getRole();
	}

}