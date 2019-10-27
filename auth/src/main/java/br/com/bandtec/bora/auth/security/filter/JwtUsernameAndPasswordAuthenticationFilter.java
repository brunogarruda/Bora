package br.com.bandtec.bora.auth.security.filter;

import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.property.JwtConfiguration;
import br.com.bandtec.bora.token.security.token.TokenCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Collections.emptyList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final JwtConfiguration jwtConfiguration;
	private final TokenCreator tokenCreator;

  @Override
  @SneakyThrows
    public Authentication attemptAuthentication(
        HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		log.info("Autenticando...");
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

		if (usuario == null) {
			throw new UsernameNotFoundException("Nao encontrei o usuario");
		}

		log.info("Cria autenticacao para o usuario '{}' e chama o UserDetailServiceImpl loadUserByUsername",
				usuario.getApelido());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuario.getApelido(), usuario.getSenha(), emptyList());

		usernamePasswordAuthenticationToken.setDetails(usuario);

		return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}

	@Override
	@SneakyThrows
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) {
        log.info("Authentication was successful for the user '{}', generating JWE token", auth.getName());

        SignedJWT signedJWT = tokenCreator.createSignedJWT(auth);

		String encryptedToken = tokenCreator.encryptToken(signedJWT);

		log.info("Token generated successfully, adding it to the response header");

		response.addHeader("Access-Control-Expose-Headers", "XSRF-TOKEN, " + jwtConfiguration.getHeader().getName());

		response.addHeader(jwtConfiguration.getHeader().getName(),
				jwtConfiguration.getHeader().getPrefix() + encryptedToken);
	}

}
