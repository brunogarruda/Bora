package br.com.bandtec.bora.token.security.util.security.filter;

import br.com.bandtec.bora.core.property.JwtConfiguration;
import br.com.bandtec.bora.token.security.util.security.token.TokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import security.util.SecurityContextUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.text.ParseException;

import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
	protected final JwtConfiguration jwtConfiguration;
	protected final TokenConverter tokenConverter;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String header = request.getHeader(jwtConfiguration.getHeader().getName());
		if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

		try {
			SecurityContextUtil
					.setSecurityContext(equalsIgnoreCase("signed", jwtConfiguration.getType()) ? validate(token)
							: decryptValidating(token));
		} catch (ParseException | JOSEException e) {
			e.printStackTrace();
		}

		chain.doFilter(request, response);

	}

	private SignedJWT decryptValidating(String encryptedToken)
			throws ParseException, JOSEException, AccessDeniedException {
		String signedToken = tokenConverter.decryptToken(encryptedToken);
		tokenConverter.validateTokenSignature(signedToken);

		return SignedJWT.parse(signedToken);
	}

	private SignedJWT validate(String signedToken) throws AccessDeniedException, ParseException, JOSEException {
		tokenConverter.validateTokenSignature(signedToken);
		return SignedJWT.parse(signedToken);

	}
}
