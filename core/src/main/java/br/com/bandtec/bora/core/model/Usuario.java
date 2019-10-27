package br.com.bandtec.bora.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

// @Getter
// @Setter
// @ToString

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@Table(name = "tbd_usuario")
@Entity
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "cod_usuario", unique = true)
	private String codigoUsuario;

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

	public @Builder Usuario(@NonNull Usuario usuario) {
		this.id = usuario.getId();
		this.codigoUsuario = usuario.getCodigoUsuario();
		this.apelido = usuario.getApelido();
		this.senha = usuario.getSenha();
		this.role = usuario.getRole();
	}

}
