package br.com.bandtec.bora.model.security.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bandtec.bora.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtAuthenticationUsernameFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final JwtConfiguration jwtConfiguration;

	@Override
	@SneakyThrows
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("Autenticacao...");
		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

		if (usuario == null) {
			throw new UsernameNotFoundException("Indisponivel para resolver apelido e/ou senha");
		}

		log.info("Criando a autenticacao para o usuario '{}'", usuario.getApelido());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario.getApelido(), usuario.getSenha(), Collections.emptyList());
		usernamePasswordAuthenticationToken.setDetails(usuario);
		

		return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		log.info("Autenticacao bem sucedida para o usuario '{}', gerendo o JWT token", auth.getName());
		
		super.successfulAuthentication(request, response, chain, auth);
	}
	
	
	private Sig
	
	
	
	

}
