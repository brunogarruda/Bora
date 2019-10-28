package br.com.bandtec.bora.evento.model.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import br.com.bandtec.bora.evento.model.dto.CadastrarUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CacheConfig(cacheNames = "boraCache")
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

    @CacheEvict(allEntries = true)
    public void clearCache() {
    }

    @Cacheable
    public Page<Usuario> buscarUsuarios(Pageable pageable) {
        log.info("Listing all usuario");
        return usuarioRepositorio.findAll(pageable);
    }

    @Transactional
    public void cadastrarUsuario(CadastrarUsuario cadastrarUsuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info("cadastrado iniciado");

        Usuario usuario = new Usuario();
        usuario.setApelido(cadastrarUsuario.getApelido());
        usuario.setCodigoUsuario(cadastrarUsuario.getCodigoUsuario());
        usuario.setSenha(passwordEncoder.encode(cadastrarUsuario.getSenha()));
        usuarioRepositorio.save(usuario);

        log.info("usuario cadastrado, {}", usuario);
    }
}
