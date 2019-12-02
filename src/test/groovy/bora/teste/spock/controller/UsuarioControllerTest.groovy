package bora.teste.spock.controller

import com.example.img.upload.demoigmupload.DemoDeDownloadEEnvioDeImgDaBaseDeDadosApplication
import com.example.img.upload.demoigmupload.controller.AuthenticationController
import com.example.img.upload.demoigmupload.controller.UserRegistrationController
import com.example.img.upload.demoigmupload.dto.LoginForm
import com.example.img.upload.demoigmupload.dto.UserRegistrationDTO
import com.example.img.upload.demoigmupload.repository.UsuarioRepositorio
import com.example.img.upload.demoigmupload.services.UserAuthenticationService
import com.example.img.upload.demoigmupload.services.UserRegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(classes = DemoDeDownloadEEnvioDeImgDaBaseDeDadosApplication)
@Stepwise
class UsuarioControllerTest extends Specification {

    @Autowired
    UserRegistrationController controller;
    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    UserRegistrationService service;
    @Autowired
    UserAuthenticationService authenticationService
    @Autowired
    AuthenticationController authenticationController

    def 'deveria retornar status 204 e sem corpo quando não existirem usuarios'() {
        when:
        def resposta = controller.buscaUsuarioPeloId(1000)

        then:
        thrown ResponseStatusException
    }

    def 'deveria retornar status 200 e o usuarios'() {
        given:
        def quantidade = repositorio.count()

        when:
        def resposta = controller.buscaUsuario()

        then:
        resposta.statusCode == HttpStatus.OK
        resposta.body.size() == quantidade
    }

    def 'deveria retornar status 200 e os usuarios'() {
        given:
        def quantidade = repositorio.count()

        when:
        def resposta = controller.buscaUsuario()

        then:
        resposta.statusCode == HttpStatus.OK
        resposta.body.size() == quantidade
    }
    def 'deveria retornar status 201 que representa que o usuario foi criado'() {
        given:
        def usuario = new UserRegistrationDTO("Guilherme","Gesuvs","g_bernardo10@hotmail.com","11983355797","senha-secreta")

        when:
        def resposta = controller.cadastrarUsuario(usuario)

        then:
        resposta.statusCode == HttpStatus.CREATED
    }

    def 'deveria retornar status 200 que representa que o usuario se logou com sucesso'() {
        given:
        def usuario = new LoginForm("senha-secreta","Gesuvs")

        when:
        def resposta = authenticationController.login(usuario)

        then:
        resposta.statusCode == HttpStatus.OK
    }
    def 'deveria retornar status 401 que representa que o usuario não se logou com sucesso'() {
        given:
        def usuario = new LoginForm("senha-secreta-errada","Gesuvs")

        when:
        def resposta = authenticationController.login(usuario)

        then:
        resposta.statusCode == HttpStatus.UNAUTHORIZED
    }
}
