package br.com.bandtec.bora.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotEmpty(message = "{apelido.not.empty}")
    private String apelido;

    @NotEmpty(message = "{senha.not.empty}")
    private String senha;
}
