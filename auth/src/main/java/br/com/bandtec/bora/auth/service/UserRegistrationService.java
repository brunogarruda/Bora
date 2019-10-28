package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.auth.dto.UserRegistrationDTO;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserRegistrationService {
    private final UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void cadastrar(UserRegistrationDTO userRegistrationDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(userRegistrationDTO.getNome());
        usuario.setApelido(userRegistrationDTO.getApelido());
        usuario.setSenha(userRegistrationDTO.getSenha());
        usuario.setEmail(userRegistrationDTO.getEmail());

        usuarioRepositorio.save(usuario);
    }

}
