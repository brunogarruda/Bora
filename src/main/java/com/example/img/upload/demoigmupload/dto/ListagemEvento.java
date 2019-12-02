package com.example.img.upload.demoigmupload.dto;

import com.example.img.upload.demoigmupload.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ListagemEvento
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class ListagemEvento {

    private List<Usuario> listaUsuario;

    // public Usuario toUSer(){
    //     return new Usuario(usuario.)
    // }


}
