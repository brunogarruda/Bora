package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.auth.dto.UserRegistrationDTO;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserRegistrationService {
    private final UsuarioRepositorio repositorio;

//    @Transactional
//    public void cadastrar(UserRegistrationDTO userRegistrationDTO){
//        Usuario usuario = new Usuario();
//        usuario  = usuarioRepositorio.save(usuario);
//
//        usuario.setNome(userRegistrationDTO.getNome());
//        usuario.setApelido(userRegistrationDTO.getApelido());
//        usuario.setCelular(userRegistrationDTO.getCelular());
//        usuario.setSenha(userRegistrationDTO.getSenha());
//        usuario.setEmail(userRegistrationDTO.getEmail());
//
//
//        usuarioRepositorio.save(usuario);
//    }

    public Usuario salvar(Usuario usuario){
        return repositorio.save(usuario);
    }

//    public void cadastrar(UserRegistrationDTO userRegistrationDTO){
//        Usuario usuario = new Usuario(userRegistrationDTO.getNome(),userRegistrationDTO.getApelido(),userRegistrationDTO.getEmail(),userRegistrationDTO.getCelular(),userRegistrationDTO.getSenha());
//        repositorio.save(usuario);
//    }


}

