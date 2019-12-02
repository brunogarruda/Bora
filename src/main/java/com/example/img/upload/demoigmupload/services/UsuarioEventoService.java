package com.example.img.upload.demoigmupload.services;

import com.example.img.upload.demoigmupload.model.Evento;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.example.img.upload.demoigmupload.model.UsuarioEvento;
import com.example.img.upload.demoigmupload.repository.EventoRepositorio;
import com.example.img.upload.demoigmupload.repository.UsuarioEventoRepositorio;
import com.example.img.upload.demoigmupload.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UsuarioEventoService {
    private final UsuarioEventoRepositorio repositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EventoRepositorio eventoRepositorio;

    public void sairEvento(Long id){
        Optional<Evento> evento = eventoRepositorio.findById(id);
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        Long idUser = usuario.get().getIdUsuario();
        Long idEve = evento.get().getIdEvento();

        Optional<UsuarioEvento> usuarioRepositorio =
                repositorio.findUsuarioEventoByEvento_IdEventoAndParticipante_IdUsuario(idEve,idUser);
        repositorio.deleteById(usuarioRepositorio.get().getId());
    }

}
