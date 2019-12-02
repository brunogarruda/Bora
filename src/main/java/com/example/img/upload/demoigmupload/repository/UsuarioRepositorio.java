package com.example.img.upload.demoigmupload.repository;

import com.example.img.upload.demoigmupload.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//    public Usuario retornaEventosQuePartipa(Long id){
//        List<Evento> eventos = new ArrayList<Evento>();
//
//        repositorio.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
//        usuarioEventoRepositorio.findByParticipante_IdUsuario(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NO_CONTENT))
//                .forEach(res->eventos.add(res.getEvento()));
//        Usuario usuario = new Usuario();
//        usuario.setEventosQueParticipo(eventos);
//        return usuario;
//    }
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {
    Optional<Usuario> findByApelido(String apelido);

    @Query("select u from Usuario u where u.apelido =:apelido and u.senha =:senha")
    public Usuario validaLoginESenha(@Param("apelido") String apelido, @Param("senha") String senha);

    @Query("from Usuario")
    public List<Usuario> buscarUsuarios();


}
