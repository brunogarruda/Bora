package br.com.bandtec.bora.gateway.security.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import br.com.bandtec.bora.token.security.config.SecurityTokenConfig;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityConfig(JwtConfiguration jwtConfiguration,TokenConverter tokenConverter){
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}
