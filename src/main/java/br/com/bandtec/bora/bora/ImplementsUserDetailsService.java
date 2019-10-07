package br.com.bandtec.bora.bora;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class ImplementsUserDetailsService implements UserDetailsService {

	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("msg: Procurando no DB o usuario pelo apelido '{}'", username);
		Usuario usuario = usuarioRepositorio.findByApelido(username);
		log.info("Aplicacao encotrou '{}'", usuario);

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Nao encontrou usuario, '%s", username));
		}

		return new CustomUserDetails(usuario);
	}

	private static final class CustomUserDetails extends Usuario implements UserDetails {

		public CustomUserDetails(@NotNull Usuario usuario) {
			super(usuario);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
		}

		@Override
		public String getPassword() {
			return null;
		}

		@Override
		public String getUsername() {
			return null;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}
