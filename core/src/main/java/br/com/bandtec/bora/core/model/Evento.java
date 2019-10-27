package br.com.bandtec.bora.core.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

// @Getter
// @Setter
// @ToString

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@Table(name = "tbd_evento")
@Entity
public class Evento implements AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "cod_evento", unique = true)
	private String codigoEvento;

	@NotNull(message = "Preenchimento obrigatorio")
	@Column(nullable = false)
	private String nome;

}
