//package com.example.img.upload.demoigmupload.controller;
//
//import com.example.img.upload.demoigmupload.model.ImageModel;
//import com.example.img.upload.demoigmupload.model.Usuario;
//import com.example.img.upload.demoigmupload.repository.ImageRepositorio;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import java.awt.*;
//import java.io.IOException;
//
//@RestController
//@RequestMapping(value = "/usuarios", produces = MediaType.ALL_VALUE)
//public class ImageController {
//
//    @Autowired
//    ImageRepositorio repositorio;
//
//    @PostMapping
//    public ResponseEntity<ImageModel> upload(@RequestParam("file")MultipartFile file) throws IOException {
//        ImageModel img = new ObjectMapper().readValue(user,Usuario.class);
//        usuario.setFotoPerfil(file.getBytes());
//        usuario.setFileName(file.getOriginalFilename());
//        Usuario dbUser = usuarioService.criarUsuario(usuario);
//
//
//        return ResponseEntity.ok("ok");
//    }
//}
