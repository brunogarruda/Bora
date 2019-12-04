package br.com.bandtec.bora.evento.model.dto;

import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.model.UsuarioEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParticiparEvento {

//    @NotEmpty(message = "{evento.not.empty}")
//    private Evento evento;
//    @NotEmpty(message = "{usuario.not.empty}")
    private Usuario usuario;

//    public UsuarioEvento participarEvento(){
//        return new UsuarioEvento(evento.getIdEvento(),usuario.getApelido());
//    }
public UsuarioEvento participarEvento(){
    return new UsuarioEvento(usuario.getApelido());
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
