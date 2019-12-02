package com.example.img.upload.demoigmupload.dto;

import com.example.img.upload.demoigmupload.model.Categoria;
import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarEvento {

    @NotEmpty(message = "{titulo.not.empty}")
    private String titulo;

    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

//    @Temporal(TemporalType.TIME)
    private LocalTime horaInicio;

//    @Temporal(TemporalType.TIME)
    private LocalTime horaFim;

    //@NotEmpty(message = "Se voce nao falar o local, ninguem vai conseguir encontrar o role")
    private String endereco;

    //@NotEmpty(message = "Não esqueça de selecionar a categoria, Obrigado !")
    private Categoria categoria;

    //@NotEmpty(message = "evento precisa ter o organizador")
    private Usuario usuario;

    public Evento cadastrar(){
        return new Evento(titulo,descricao,endereco,dataInicio,dataFim,horaInicio,horaFim,categoria.getIdCategoria(),usuario.getIdUsuario());
    }

}
