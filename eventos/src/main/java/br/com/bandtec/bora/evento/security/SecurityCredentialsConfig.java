package br.com.bandtec.bora.evento.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.bandtec.bora.core.property.JwtConfiguration;
import br.com.bandtec.bora.token.security.config.SecurityTokenConfig;
import br.com.bandtec.bora.token.security.filter.JwtTokenAuthorizationFilter;
import br.com.bandtec.bora.token.security.token.TokenConverter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityCredentialsConfig extends SecurityTokenConfig {
	private final TokenConverter tokenConverter;

	public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
		super(jwtConfiguration);
		this.tokenConverter = tokenConverter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
				UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

}
