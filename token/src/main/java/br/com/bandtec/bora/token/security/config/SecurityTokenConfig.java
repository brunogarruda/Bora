package br.com.bandtec.bora.token.security.config;

import br.com.bandtec.bora.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	protected final JwtConfiguration jwtConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "**/**").permitAll()
				.antMatchers("/**/swagger-ui.html").permitAll().antMatchers(HttpMethod.GET, "/**/swagger-resources/**",
						"/**/webjars/springfox-swagger-ui/**", "/**/v2/api-docs/**")
				.permitAll();

	}

}
