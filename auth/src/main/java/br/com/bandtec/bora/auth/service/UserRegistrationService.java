package br.com.bandtec.bora.auth.service;

import br.com.bandtec.bora.auth.dto.UserRegistrationDTO;
import br.com.bandtec.bora.core.model.Evento;
import br.com.bandtec.bora.core.model.ImageModel;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.ImageRepositorio;
import br.com.bandtec.bora.core.repository.UsuarioEventoRepositorio;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserRegistrationService {
    private final UsuarioRepositorio repositorio;
    private final ImageRepositorio imageRepositorio;
    private final UsuarioEventoRepositorio usuarioEventoRepositorio;

    @Transactional
    public void cadastrar(MultipartFile file, UserRegistrationDTO userRegistrationDTO) throws IOException {
        ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());

        imageRepositorio.save(imageModel);
        Usuario usuario = new Usuario(
            userRegistrationDTO.getNome(),
            userRegistrationDTO.getApelido(),
            userRegistrationDTO.getEmail(),
            userRegistrationDTO.getCelular(),
            userRegistrationDTO.getSenha());
        usuario.setFotoPerfil(imageModel);
        repositorio.save(usuario);

        System.out.println(usuario);
    }


    public Usuario atualizarUser(Usuario usuario) {
        return repositorio.save(usuario);
    }

    public Usuario salvar(Usuario usuario) {
        try {
            return repositorio.save(usuario);
        } catch (ResponseStatusException res) {
            throw new ResponseStatusException(res.getStatus(), res.getMessage());
        }
    }

    public Iterable<Usuario> buscaTodosUsuarios(){
        return repositorio.findAll();
    }

        public Usuario buscaPeloId (Long id){
            return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        }

        public Usuario retornaEventosQuePartipa (String apelido){
            List<Evento> eventos = new ArrayList<Evento>();

            Usuario usuario = repositorio.findByApelido(apelido).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

            usuarioEventoRepositorio.findByParticipante_IdUsuario(usuario.getIdUsuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT))
                .forEach(res -> eventos.add(res.getEvento()));
            usuario.setEventosQueParticipo(eventos);
            return usuario;
        }

        public Usuario buscaPeloApelido (String apelido){
            return repositorio.findByApelido(apelido).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        }

//    public void cadastrar(UserRegistrationDTO userRegistrationDTO){
//        Usuario usuario = new Usuario(userRegistrationDTO.getNome(),userRegistrationDTO.getApelido(),userRegistrationDTO.getEmail(),userRegistrationDTO.getCelular(),userRegistrationDTO.getSenha());
//        repositorio.save(usuario);
//    }


}
