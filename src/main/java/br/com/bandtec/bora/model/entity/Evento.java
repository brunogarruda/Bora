package br.com.bandtec.bora.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tbd_evento")
@Data
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evento")
	private Long idEvento;

	@NotEmpty
	@Size(min = 2)
	@Column(name = "nome_evento")
	private String nome;
	
	@NotEmpty
	@Column(name = "data_hora_inicio")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dataHoraInicio;

	@Column(name = "data_hora_fim")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dataHoraFim;
	
	@Size(max = 255)
	@Column(name = "descricao")
	private String descricaoEvento;

	@Column(name = "is_privado")
	private boolean isPrivado;
	
	private String senha;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@NotEmpty
	private Categoria categoria;

	@NotEmpty
	@OneToOne
	@JoinColumn(name="idEndereco")
	private Endereco endereco;

//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "organizador_id")
//	private UsuarioEvento organizador;

	
	public Evento() {
	}

	public Evento(Long idEvento, @NotEmpty @Size(min = 2) String nome, @NotEmpty String dataHoraInicio,
			String dataHoraFim, @Size(max = 255) String descricao, boolean isPrivado, String senha,
			@NotEmpty Categoria categoria, @NotEmpty Endereco endereco, UsuarioEvento organizador) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.descricaoEvento = descricao;
		this.isPrivado = isPrivado;
		this.senha = senha;
		this.categoria = categoria;
		this.endereco = endereco;
//		this.organizador = organizador;
	}	

//	public UsuarioEvento getOrganizador() {
//		return organizador;
//	}
//
//	public void setOrganizador(UsuarioEvento organizador) {
//		this.organizador = organizador;
//	}
}
