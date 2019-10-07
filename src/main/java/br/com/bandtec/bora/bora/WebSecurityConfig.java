package br.com.bandtec.bora.bora;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import com.twilio.http.Request;

import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.security.jwt.JWTAuthenticationFilter;
import br.com.bandtec.bora.model.security.jwt.JWTLoginFilter;
import br.com.bandtec.bora.model.security.jwt.JwtAuthenticationEntryPoint;
import br.com.bandtec.bora.model.security.jwt.JwtAuthenticationUsernameFilter;
import br.com.bandtec.bora.model.security.jwt.JwtConfiguration;
import br.com.bandtec.bora.model.security.jwt.JwtRequestFilter;
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
