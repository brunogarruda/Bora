//package com.example.img.upload.demoigmupload.services;
//
//import com.example.img.upload.demoigmupload.repository.UsuarioRep;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UsuarioService {
//    private final UsuarioRep repositorio;
//
//    @Autowired
//    public UsuarioService(UsuarioRep repositorio) {
//        this.repositorio = repositorio;
//    }
//
//
//    public Usuario2 criarUsuario(Usuario2 usuario2){
//        return repositorio.save(usuario2);
//    }
//
//    public List<Usuario2> todosUsuarios(){
//        return repositorio.findAll();
//    }
//
//    public Optional<Usuario2> oneUsuario(Long id){
//        return repositorio.findById(id);
//    }
//
//
//}
