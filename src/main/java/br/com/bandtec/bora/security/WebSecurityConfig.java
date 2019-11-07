package br.com.bandtec.bora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.bandtec.bora.repository.UsuarioRepositorio;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepositorio UsuarioRepositorio;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
		.antMatchers(HttpMethod.GET, "/api/eventos").permitAll()
		.antMatchers(HttpMethod.POST, "/api/eventos").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/eventos/{idEvento}").permitAll()	
		.antMatchers(HttpMethod.GET, "/api/usuarios").authenticated()
		.antMatchers(HttpMethod.GET, "/api/{eventos}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/eventos").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/eventos/{idEvento}").permitAll()
		.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/categoria").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoFilter(tokenService, UsuarioRepositorio), UsernamePasswordAuthenticationFilter.class);
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");	
	}
	
// UM EXEMPLO DE AUTENTICAÇO EM MEMORIA COM SPRING SECURITY 
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("bruno")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
	
}