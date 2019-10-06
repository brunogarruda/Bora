package br.com.bandtec.bora.model.entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.bandtec.bora.model.entity.UsuarioEvento;

@Entity
@Table(name = "tbd_usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@NotNull
	@Size(min = 2, message = "Minino duas letras")
	private String nome;

	@NotNull
	@Size(min = 2, message = "Minino duas letras")
	private String apelido;

	private String celular;

	private String senha;

	@OneToMany(mappedBy = "organizador")
	@JsonIgnoreProperties
	private List<Evento> eventosCriado;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties
	private List<UsuarioEvento> todosEventos;

	public Usuario() {
	}

	public Usuario(String nome, String apelido, String celular, String senha) {
		this.nome = nome;
		this.apelido = apelido;
		this.celular = celular;
		this.senha = senha;
	}

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder().encode(senha);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.apelido;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}