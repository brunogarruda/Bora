package br.com.bandtec.bora.gateway.security.filter;

import br.com.bandtec.bora.core.property.JwtConfiguration;
import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.NonNull;
import lombok.SneakyThrows;
import br.com.bandtec.bora.token.security.filter.JwtTokenAuthorizationFilter;
import br.com.bandtec.bora.token.security.token.TokenConverter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static br.com.bandtec.bora.token.security.util.SecurityContextUtil.setSecurityContext;

public class GatewayJwtTokenAuthorizationFilter extends JwtTokenAuthorizationFilter {
	public GatewayJwtTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
		super(jwtConfiguration, tokenConverter);
	}

	@Override
	@SneakyThrows
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain chain) {
		String header = request.getHeader(jwtConfiguration.getHeader().getName());

		if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

		String signedToken = tokenConverter.decryptToken(token);

		tokenConverter.validateTokenSignature(signedToken);

		setSecurityContext(SignedJWT.parse(signedToken));

		if (jwtConfiguration.getType().equalsIgnoreCase("signed"))
			RequestContext.getCurrentContext().addZuulRequestHeader("Authorization",
					jwtConfiguration.getHeader().getPrefix() + signedToken);

		chain.doFilter(request, response);
	}
}
