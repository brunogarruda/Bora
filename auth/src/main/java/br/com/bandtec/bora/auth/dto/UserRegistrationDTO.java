package br.com.bandtec.bora.auth.dto;

import br.com.bandtec.bora.core.model.Usuario;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private String nome;

    @NotEmpty(message = "{apelido.not.empty}")
    private String apelido;

    @NotEmpty(message = "{email.not.empty}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotEmpty(message = "{celular.not.empty}")
    private String celular;

    @NotEmpty(message = "{senha.not.empty}")
    private String senha;

    public Usuario cadastrar(){
        return new Usuario(nome,apelido,email,celular,senha);
    }
}
