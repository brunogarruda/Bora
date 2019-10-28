package br.com.bandtec.bora.evento.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.evento.model.dto.CadastrarUsuario;
import br.com.bandtec.bora.evento.model.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nenhuma")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints para gerenciar usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<Usuario>> list(@PageableDefault(size = 10, sort = "apelido") Pageable pageable) {
        Page<Usuario> page = service.buscarUsuarios(pageable);

        if (page.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(page);

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CadastrarUsuario> cadastrarUsuario(@Valid @RequestBody CadastrarUsuario cadastrarUsuario) {
        service.cadastrarUsuario(cadastrarUsuario);
        return ResponseEntity.ok().build();
    }

    // @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario
    // usuario) {
    // service.cadastrar(usuario);
    // return ResponseEntity.ok().build();
    // }

}
