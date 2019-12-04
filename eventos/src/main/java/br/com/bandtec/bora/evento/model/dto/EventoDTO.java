package br.com.bandtec.bora.evento.model.dto;

import br.com.bandtec.bora.core.model.Categoria;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    public Long idEvento;
    @NotEmpty(message = "{titulo.not.empty}")
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String horaFim;
    private String rua;
    private String numero;
    private String cep;
    private String bairro;
    private Double latitude;
    private Double longitude;

    private ImageModel imageModel;

    private Categoria categoria;

    public Evento atualizarEvento(){
        return new Evento(
            idEvento,
            titulo,
            descricao,
            dataInicio,
            dataFim,
            horaInicio,
            horaFim,
            rua,
            numero,
            cep,
            bairro,
            latitude,
            longitude,
            imageModel,
            categoria.getIdCategoria());
    }

}
