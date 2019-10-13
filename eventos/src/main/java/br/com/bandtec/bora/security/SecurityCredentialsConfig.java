package br.com.bandtec.bora.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.bandtec.bora.core.property.JwtConfiguration;
import security.config.SecurityTokenConfig;
import security.filter.JwtTokenAuthorizationFilter;
import security.token.TokenConverter;

@EnableWebSecurity
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
