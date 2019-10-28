package br.com.bandtec.bora.auth.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private String apelido;
    private String nome;
    private String email;
    private String senha;
}
