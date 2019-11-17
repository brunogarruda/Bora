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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "tbd_usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Size(min = 2)
	private String nome;

	@Column(unique = true, length = 50)
	@Size(min = 5)
	private String apelido;

	@Email
	private String email;

	@NotEmpty
	private String celular;

	@NotEmpty
	@Size(min = 5)
	private String senha;

	@JsonManagedReference
	@OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evento> eventosCriados;
	
	@OneToMany
	@Transient
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Evento> eventosQueParticipo;
	
	
	public Usuario() {
	}

	public Usuario(Long idUsuario, @Size(min = 2) String nome, @Size(min = 5) String apelido,
			@Email @NotEmpty String email, @NotEmpty String celular, @NotEmpty @Size(min = 5) String senha) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
	}

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(senha);
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.apelido;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}