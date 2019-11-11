package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.auth.dto.LoginForm;
import br.com.bandtec.bora.auth.security.ExpiredTokenException;
import br.com.bandtec.bora.auth.security.InvalidTokenException;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserAuthenticationService {
    private final UsuarioRepositorio repositorio;
    private final TokenService service;

//    public Usuario authenticate(LoginForm dados, String token){
//        Usuario usuario = repositorio.findByApelido(dados.getApelido()).orElseThrow(ExistingApelidoException::new);
//        if(dados.getSenha().equals(usuario.getSenha())&&!token.isEmpty() && validate(token))
//            return usuario;
//        else {
//            return null;
//        }
//    }

    public String criaTokenLogin(LoginForm loginForm){
        Usuario usuario = repositorio.validaLoginESenha(loginForm.getApelido(),loginForm.getSenha());
        if(usuario==null)
            return "erro";
        return service.geraToken(usuario);
    }

    private boolean validate(String token){
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = service.decodeToken(tokenTratado);
            log.info(claims.getIssuer());
            log.info(String.valueOf(claims.getIssuedAt()));
            if(claims.getExpiration().before(new Date(System.currentTimeMillis())))throw new ExpiredTokenException();
            return true;

        }catch (ExpiredTokenException et){
            et.printStackTrace();;
            throw et;
        }catch (Exception e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }
}
