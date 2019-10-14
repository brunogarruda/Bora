package br.com.bandtec.bora.token.security.util;


import br.com.bandtec.bora.core.model.Usuario;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import static java.util.stream.Collectors.toList;

@Slf4j
public class SecurityContextUtil {
	private SecurityContextUtil() {

	}

	public static void setSecurityContext(SignedJWT signedJWT) {
		try {
			JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
			String usuario = claims.getSubject();
			if (usuario == null)
				throw new JOSEException("Ausencia de JWT");

			List<String> authorities = claims.getStringListClaim("authorities");
			Usuario apelido = Usuario.builder().id(claims.getLongClaim("idUsuario")).apelido(usuario)
					.role(String.join(",", authorities)).build();

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null,
					createAuthorities(authorities));
			auth.setDetails(signedJWT.serialize());

			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (Exception e) {

			log.error("Erro: Configurações sucurity context ", e);
			SecurityContextHolder.clearContext();
		}
	}

	private static List<SimpleGrantedAuthority> createAuthorities(List<String> authorities) {
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

}
