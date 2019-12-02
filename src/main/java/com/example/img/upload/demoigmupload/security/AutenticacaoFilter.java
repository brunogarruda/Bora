package com.example.img.upload.demoigmupload.security;//package br.com.bandtec.bora.auth.security;
//
//import br.com.bandtec.bora.auth.service.TokenService;
//import br.com.bandtec.bora.auth.service.UserAuthenticationService;
//import br.com.bandtec.bora.core.model.Usuario;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collection;
//
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class AutenticacaoFilter extends OncePerRequestFilter {
//    private TokenService tokenService;
//    private UserAuthenticationService service;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token  = recuperarToken(request);
//        boolean tokenValido = tokenService.isTokenValido(token);
//
//        if(tokenValido)
//            autenticarCliente(token);
//
//        filterChain.doFilter(request,response);
//    }
//
//    private static final class CustomUserDetails extends Usuario implements UserDetails{
//        CustomUserDetails(Usuario usuario){
//            super(usuario);
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return null;
//        }
//
//        @Override
//        public String getPassword() {
//            return new BCryptPasswordEncoder().encode(getSenha());
//        }
//
//        @Override
//        public String getUsername() {
//            return getApelido();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//    }
//
//    private void autenticarCliente(String token){
//        Long idUsuario = tokenService.getIdUsuario(token);
//        Usuario usuario = service.findById(idUsuario).get();
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(CustomUserDetails.class, usuario, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//
//    private String recuperarToken(HttpServletRequest request){
//        String token = request.getHeader("Authorization");
//        if(token==null||token.isEmpty()||!token.startsWith("Bearer ")){
//            return null;
//        }
//        return token.substring(7,token.length());
//    }
//}
