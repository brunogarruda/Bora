package br.com.bandtec.bora.model.security.jwt;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collector;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtAuthenticationUsernameFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final JwtConfiguration jwtConfiguration;
	private final TokenCreator tokenCreator;

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

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuario.getApelido(), usuario.getSenha(), Collections.emptyList());
		usernamePasswordAuthenticationToken.setDetails(usuario);

		return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		log.info("Autenticacao bem sucedida para o usuario '{}', gerendo o JWT token", auth.getName());

		SignedJWT signedJWT = signedJWT(auth);
		String encryptToken = encryptToken(signedJWT);

		log.info("Token gerado com sucesso");
		
		response.addHeader("Access-Control-Expose-Headers", "XSRF-TOKEN,"+ jwtConfiguration.getHeader().getName());
		response.addHeader(jwtConfiguration.getHeader().getName(), jwtConfiguration.getHeader().getPrefix() + encryptToken);
		
	}

	private SignedJWT signedJWT(Authentication auth) {
		log.info("Iniciado o token JWT");
		Usuario usuario = (Usuario) auth.getPrincipal();
		JWTClaimsSet jwtClaimsSet = jwtClaimsSet(auth, usuario);

		KeyPair rsaJWK = keyPair();
		log.info("Constroi JWT from RSA keys");
		JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaJWK.getPublic()).keyID(UUID.randomUUID().toString()).build();

		SignedJWT signedJWT = new SignedJWT(
				new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT).build(), jwtClaimsSet);

		log.info("Validando o token com private RSA Key");

		RSASSASigner signer = new RSASSASigner(rsaJWK.getPrivate());
		signedJWT.sign(signer);

		log.info("Serialized token '{}'", signedJWT.serialize());
		return signedJWT;
	}

	private JWTClaimsSet jwtClaimsSet(Authentication auth, Usuario usuario) {
		log.info("Criando o JWTClaimsSet para '{}' ", usuario);
		return new JWTClaimsSet.Builder().subject(usuario.getApelido())
				.claim("authorities",
						auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
				.issuer("http://localhost:3000").issueTime(new Date())
				.expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
				.build();
	}

	private KeyPair keyPair() {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.genKeyPair();
	}

	private String encryptToken(SignedJWT signedJWT) throws KeyLengthException {
		log.info("Iniciando o metodo de encriptacao do token");
		DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

		JWEObject jweObject = new JWEObject(
				new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256).contentType("JWT").build(),
				new Payload(signedJWT));
		log.info("Encriptando token com chave privada");

		jweObject.encrypt(directEncrypter);

		log.info("Token encriptado");

		return jweObject.serialize();

	}

}
