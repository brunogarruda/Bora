package br.com.bandtec.bora.auth.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private String nome;
    private String apelido;
    private String email;
    private String celular;
    private String senha;
}
