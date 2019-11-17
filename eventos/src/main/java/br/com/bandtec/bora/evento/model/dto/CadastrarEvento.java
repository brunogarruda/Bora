package br.com.bandtec.bora.evento.model.dto;

import br.com.bandtec.bora.core.model.Categoria;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarEvento {

    @NotEmpty(message = "{titulo.not.empty}")
    private String titulo;
    private String descricao;
    private String endereco;
    private Categoria categoria;
    private Usuario usuario;

    public Evento cadastrar(){
        return new Evento(titulo,descricao,endereco,categoria.getIdCategoria(),usuario.getIdUsuario());
    }

}
