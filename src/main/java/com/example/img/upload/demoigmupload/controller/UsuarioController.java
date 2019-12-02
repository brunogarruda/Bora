//package com.example.img.upload.demoigmupload.controller;
//
//import com.example.img.upload.demoigmupload.dto.UserRegistrationDTO;
//import com.example.img.upload.demoigmupload.model.ImageModel;
//import com.example.img.upload.demoigmupload.model.Usuario;
//import com.example.img.upload.demoigmupload.repository.ImageRepositorio;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value = "/usuarios", produces = MediaType.ALL_VALUE)
//public class UsuarioController {
//
//    @Autowired
//    ImageRepositorio repositorio;
//    //UsuarioService usuarioService;
//
//
//    @PostMapping
//    public ResponseEntity<String> createUsuario(@RequestParam MultipartFile file, @RequestParam String user) throws IOException {
//        ImageModel imageModel = new ImageModel();
//        UserRegistrationDTO usuario = new ObjectMapper().readValue(user,UserRegistrationDTO.class);
//
//
//        System.out.println(usuario);
//
//        imageModel.setPic(file.getBytes());
//        imageModel.setNome(file.getOriginalFilename());
//        imageModel.setTipo(file.getContentType());
//        ImageModel db = repositorio.save(imageModel);
//        return ResponseEntity.ok("Sucesso");
//    }
////@PostMapping
////public ResponseEntity<String> createUsuario(@RequestParam MultipartFile file) throws IOException {
////    ImageModel imageModel = new ImageModel();
////    imageModel.setPic(file.getBytes());
////    imageModel.setNome(file.getOriginalFilename());
////    imageModel.setTipo(file.getContentType());
////    ImageModel db = repositorio.save(imageModel);
////    return ResponseEntity.ok("Sucesso");
////}
//
//        @GetMapping("{id}")
//    public ResponseEntity<Optional<ImageModel>> busca(@PathVariable(value = "id") Long id){
//        Optional<ImageModel> model =repositorio.findById(id);
//        return ResponseEntity.ok(model);
//    }
//
////    @PostMapping
////    public ResponseEntity<String> createUsuario(@RequestParam MultipartFile file) throws IOException {
////        ImageModel imageModel = new ImageModel();
////        imageModel.setPic(file.getBytes());
////        imageModel.setNome(file.getOriginalFilename());
////        ImageModel db = repositorio.save(imageModel);
////
////        try {
////            System.out.printf("File name=%s, size=%s\n", file.getOriginalFilename(),file.getSize());
////            //creating a new file in some local directory
////            File fileToSave = new File("/home/gesuvs/Documentos/Guilherme/Bandtec/3º Semestre/Tecnicas de Programação Web/projeto-pwc3/demo-igm-upload/uploads\n" + file.getOriginalFilename());
////            //copy file content from received file to new local file
////            file.transferTo(fileToSave);
////        } catch (IOException ioe) {
////            //if something went bad, we need to inform client about it
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        }
////        //everything was OK, return HTTP OK status (200) to the client
////        return ResponseEntity.ok("Sucesso");
////    }
//
////    @PostMapping
////    public ResponseEntity<String> createUsuario(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws IOException {
////        Usuario usuario = new ObjectMapper().readValue(user,Usuario.class);
////        System.out.println(usuario);
////        usuario.setFotoPerfil(file.getBytes());
////        usuario.setFileName(file.getOriginalFilename());
////        Usuario dbUser = usuarioService.criarUsuario(usuario);
////        return ResponseEntity.ok("ok");
////
////    }
////    @PostMapping
////    public ResponseEntity<Usuario> salvarComImg(@RequestBody Usuario usuario){
////        byte[] foto = usuario.getFotoPerfil().getBytes();
////        usuario.setFotoPerfil(Arrays.toString(foto));
////        repositorio.save(usuario);
////        return ResponseEntity.ok(usuario);
////    }
////
////    @GetMapping
////    public ResponseEntity<List<Usuario>> busca(){
////        usuarioService.todosUsuarios();
////        return ResponseEntity.ok()
////
////    }
//
////
////    @GetMapping("{id}")
////    public ResponseEntity<Optional<Usuario>> buscaUm(@PathVariable(value = "id")Long id){
////        return ResponseEntity.ok(usuarioService.oneUsuario(id));
////    }
//
//
//}
