package br.com.bandtec.bora.core.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Data
@Embeddable
public class Endereco {

    private String rua;

    private String numero;

    private String cep;

    private String bairro;

    private String cidade;

    private String estado;

}
