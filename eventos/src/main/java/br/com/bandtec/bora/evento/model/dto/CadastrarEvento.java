package br.com.bandtec.bora.evento.model.dto;

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

    private Long idEvento;
    @NotEmpty(message = "{titulo.not.empty}")
    private String titulo;
    private String descricao;
    private String rua;
    private String numero;
    private String cep;
    private String bairro;
    private Double latitude;
    private Double longitude;
    private Usuario usuario;

    public Evento cadastrar(){
        return new Evento(titulo,rua,numero,cep,bairro,latitude,longitude,usuario.getIdUsuario());
    }
}
