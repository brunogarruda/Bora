package br.com.bandtec.bora.bora;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import br.com.bandtec.bora.model.security.jwt.JwtAuthenticationUsernameFilter;
import br.com.bandtec.bora.model.security.jwt.JwtConfiguration;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final JwtConfiguration jwtConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling()
				.authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
				.addFilter(new JwtAuthenticationUsernameFilter()).authorizeRequests()
				.antMatchers(jwtConfiguration.getLoginUrl()).permitAll().antMatchers("/usuarios").hasRole("ADMIN")
				.anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	private UserDetailsService jwtUserDetailsService;
//	private JwtRequestFilter jwtRequestFilter;
//
//	@Autowired
//	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//			UserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter) {
//		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//		this.jwtUserDetailsService = jwtUserDetailsService;
//		this.jwtRequestFilter = jwtRequestFilter;
//	}
//	
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEnconder());
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEnconder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/login")
//				.permitAll().antMatchers(HttpMethod.GET, "/eventos").permitAll().anyRequest().authenticated().and()
//				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// cria uma conta default
//		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//	}
