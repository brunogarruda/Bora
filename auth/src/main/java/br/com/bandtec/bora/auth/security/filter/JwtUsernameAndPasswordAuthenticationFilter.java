package br.com.bandtec.bora.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.bandtec.bora.core.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;
    private final TokenCreator tokenCreator;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Autenticando...");
        Usuario usuario = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);

        if(usuario==null){
            throw new UsernameNotFoundException("Nao encontrei o usuario");
        }

        log.info("Cria autenticacao para o usuario '{}' e chama o UserDetailServiceImpl loadUserByUsername",usuario.getApelido());

        return super.attemptAuthentication(request, response);
    }
}
