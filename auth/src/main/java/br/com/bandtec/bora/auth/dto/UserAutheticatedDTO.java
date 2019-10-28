package br.com.bandtec.bora.auth.dto;

import br.com.bandtec.bora.core.model.Usuario;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAutheticatedDTO {
    private String tipo;
    private String email;
    private String apelido;
    private String nome;
    private String token;

    public static UserAutheticatedDTO toDTO(Usuario user, String tipo) {
        return new UserAutheticatedDTO(user.getEmail(), user.getNome(),user.getApelido(), user.getToken(), tipo);
    }


}
