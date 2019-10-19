package br.com.bandtec.bora.evento.model.dto;

import br.com.bandtec.bora.core.model.Usuario;
import lombok.Data;

@Data
public class CadastrarUsuario {
    private Usuario usuario;

    public CadastrarUsuario() {
    }
    
}
