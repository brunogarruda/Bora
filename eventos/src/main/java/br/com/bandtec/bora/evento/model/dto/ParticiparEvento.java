package br.com.bandtec.bora.evento.model.dto;

import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.model.UsuarioEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParticiparEvento {

//    @NotEmpty(message = "{evento.not.empty}")
    private Evento evento;
//    @NotEmpty(message = "{usuario.not.empty}")
    private Usuario usuario;

    public UsuarioEvento participarEvento(){
        return new UsuarioEvento(evento.getIdEvento(),usuario.getIdUsuario());
    }

////    @NotEmpty(message = "{idEvento.not.empty}")
//    private Long idEvento;
//
//    //@NotEmpty(message = "{IdUsuario.not.empty}")
//    private Long idUsuario;
//
//    //@NotEmpty(message = "{apelido.not.empty}")
//    private String apelido;
//
//    public UsuarioEvento participarEvento(){
//        return new UsuarioEvento(idEvento,idUsuario,apelido);
//    }
}
