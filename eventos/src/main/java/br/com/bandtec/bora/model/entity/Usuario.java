package br.com.bandtec.bora.model.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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