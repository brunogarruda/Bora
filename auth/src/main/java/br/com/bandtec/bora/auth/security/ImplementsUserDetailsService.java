//package br.com.bandtec.bora.auth.security;
//
//import br.com.bandtec.bora.auth.service.UserAuthenticationService;
//import br.com.bandtec.bora.core.model.Usuario;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class ImplementsUserDetailsService implements UserDetailsService {
//    private UserAuthenticationService service;
//
//    @Override
//    public UserDetails loadUserByUsername(String apelido) throws UsernameNotFoundException {
//        Usuario buscaUsuario = service.findByApelido(apelido);
//        if (buscaUsuario==null)
//            throw new UsernameNotFoundException("Usuario nao encontrado");
//
//        return (UserDetails) buscaUsuario;
//    }
//}
