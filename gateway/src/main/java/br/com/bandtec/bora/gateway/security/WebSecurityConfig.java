// package br.com.bandtec.bora.gateway.security;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// /**
//  * WebSecurityConfig
//  */
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//         .httpBasic().disable();
//         http
//         .authorizeRequests().antMatchers(HttpMethod.POST, "**/**").permitAll();

//     }
// }
