package com.example.img.upload.demoigmupload.dto;

import com.example.img.upload.demoigmupload.model.Usuario;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

//    @Pattern(regexp = "^[a-zA-Z\\s]+", message = "{nome.not.valid}")
    private String nome;

//    @NotEmpty(message = "{apelido.not.empty}")
    private String apelido;

//    @NotEmpty(message = "{email.not.empty}")
//    @Email(message = "{email.not.valid}")
    private String email;

//    @NotEmpty(message = "{celular.not.empty}")
//    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "{celular.not.valid}")
    private String celular;
    //(11) 98801-4111

//    @NotEmpty(message = "{senha.not.empty}")
//    @Size(min = 5,message = "Digite pelo menos 5 caracteres!")
//    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{8,64})", message = "{senha.not.valid}")
    private String senha;

    public Usuario cadastrar(){
        return new Usuario(nome,apelido,email,celular,senha);
    }
    public Usuario cadastroSimples(){
        return new Usuario(apelido,email,senha);
    }
}
