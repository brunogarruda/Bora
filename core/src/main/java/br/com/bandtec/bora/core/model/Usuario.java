package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tbd_usuario")
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotNull(message = "Preenchimento Obrigatorio")
	@Column(nullable = false)
	private String apelido;

	@NotNull(message = "Preenchimento obrigatorio")
	@Column(nullable = false)
	@ToString.Exclude
	private String senha;

	@NotNull
	@Column(nullable = false)
	@Builder.Default
	private String role = "USER";

	public Usuario(@NotNull Usuario usuario) {
		this.id = usuario.getId();
		this.apelido = usuario.getApelido();
		this.senha = usuario.getSenha();
		this.role = usuario.getRole();
	}

}