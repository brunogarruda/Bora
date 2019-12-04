package br.com.bandtec.bora.auth.controller;

import br.com.bandtec.bora.auth.dto.LoginForm;
import br.com.bandtec.bora.auth.dto.TokenDTO;
import br.com.bandtec.bora.auth.service.UserAuthenticationService;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class AuthenticationControllerTest {
    private AuthenticationController loginController;
    private UsuarioRepositorio repositorio;
    private UserAuthenticationService service;

    @Before
    public void setUp(){
        repositorio = Mockito.mock(UsuarioRepositorio.class);
        service = Mockito.mock(UserAuthenticationService.class);
        loginController = new AuthenticationController(service);
    }

    @Test
    public void loginSucesso(){
        String apelido = "apelido";
        String senha = "senha";
        LoginForm form = new LoginForm(apelido,senha);
        Mockito.when(service.criaTokenLogin(form)).thenReturn(String.valueOf(new TokenDTO("Token","Bearer")));
        ResponseEntity<TokenDTO> response = loginController.login(form);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void loginErro(){
        String apelido = "apelido";
        String senha = "senha";
        LoginForm form = new LoginForm(apelido,senha);
        Mockito.when(service.criaTokenLogin(form)).thenReturn("erro");
        ResponseEntity<TokenDTO> response = loginController.login(form);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
    }

}
