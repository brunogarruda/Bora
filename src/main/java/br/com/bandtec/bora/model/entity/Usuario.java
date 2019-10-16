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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.bandtec.bora.model.entity.UsuarioEvento;

@Entity
@Table(name = "tbd_usuario")
public class Usuario{

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

//	@JsonManagedReference
//	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties
//	private List<UsuarioEvento> todosEventos;

	
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
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public List<UsuarioEvento> getTodosEventos() {
//		return todosEventos;
//	}
}