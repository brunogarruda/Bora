package com.example.img.upload.demoigmupload.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Data
//@Embeddable
public class Endereco {

    private String rua;

    private String numero;

    private String cep;

    private String bairro;

    private String cidade;

    private String estado;

}
