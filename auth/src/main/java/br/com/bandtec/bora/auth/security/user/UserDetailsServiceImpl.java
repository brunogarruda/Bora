package br.com.bandtec.bora.auth.security.user;

import br.com.bandtec.bora.core.model.Usuario;
import br.com.bandtec.bora.core.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String apelido) {
		log.info("Procurando na base de dados pelo usuario do apelido '{}'", apelido);

		Usuario usuario = usuarioRepositorio.findByApelido(apelido);

		log.info("Usuario encontrado '{}'", usuario);

		if (usuario == null)
			throw new UsernameNotFoundException(String.format("Usuario '%s' não foi encontrado", apelido));

		return new CustomUserDetails(usuario);
	}

	private static final class CustomUserDetails extends Usuario implements UserDetails {
		/**
		 *
		 */
		private static final long serialVersionUID = 8327797172982120495L;

		CustomUserDetails(@NotNull Usuario usuario) {
			super(usuario);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
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

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
