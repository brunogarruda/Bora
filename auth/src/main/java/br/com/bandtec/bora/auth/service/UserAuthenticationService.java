package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.auth.dto.DadosLogin;
import br.com.bandtec.bora.auth.exception.ExistingEmailException;
import br.com.bandtec.bora.auth.exception.ExpiredTokenException;
import br.com.bandtec.bora.auth.exception.InvalidLoginException;
import br.com.bandtec.bora.auth.exception.InvalidTokenException;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticationService {
    private final UsuarioRepositorio usuarioRepositorio;

    public Usuario findByApelido(String apelido){
        return usuarioRepositorio.findByApelido(apelido);
    }

}
