package com.example.img.upload.demoigmupload.controller;

import com.example.img.upload.demoigmupload.dto.UserRegistrationDTO;
import com.example.img.upload.demoigmupload.model.ImageModel;
import com.example.img.upload.demoigmupload.model.Usuario;
import com.example.img.upload.demoigmupload.services.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/usuarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRegistrationController {
    private final UserRegistrationService service;


    @PostMapping(value = "teste")
    public ResponseEntity<UserRegistrationDTO> cadastrarComFoto(@RequestParam MultipartFile file, @RequestParam String user) throws IOException {
        UserRegistrationDTO usuario = new ObjectMapper().readValue(user,UserRegistrationDTO.class);
        service.cadastrar(file,usuario);

//        ImageModel db = repositorio.save(imageModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserRegistrationDTO> atualizar(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            service.atualizarUser(userRegistrationDTO.cadastrar());
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(ex.getStatus());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<UserRegistrationDTO> cadastrarUsuario(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            service.salvar(userRegistrationDTO.cadastroSimples());
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(ex.getStatus(),ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscaUsuario(Usuario usuario){
        List<Usuario> user = service.buscaTodosUsuarios(usuario);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/eventos-participados/{apelido}")
    public ResponseEntity<Usuario> buscarEventosParticipadoDoUsuario(@PathVariable(value = "apelido")String apelido){
        return ResponseEntity.ok(service.retornaEventosQuePartipa(apelido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaUsuarioPeloId(@PathVariable(value = "id")Long id){
        return ResponseEntity.ok(service.buscaPeloId(id));
    }

    @GetMapping("/apelido/{apelido}")
    public ResponseEntity<Usuario> buscaUsuarioPeloApelido(@PathVariable(value = "apelido")String apelido){
        try {
            return new ResponseEntity<>(service.buscaPeloApelido(apelido),HttpStatus.OK);
        }catch (ResponseStatusException re){
            throw new ResponseStatusException(re.getStatus(),re.getMessage());
        }
    }
}
