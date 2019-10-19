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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "tbd_evento")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
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
	private String dataHoraInicio;

	@Column(name = "data_hora_fim")
	private String dataHoraFim;
	
	@Size(max = 255)
	@Column(name = "descricao")
	private String descricaoEvento;

	@Column(name = "is_privado")
	private boolean isPrivado;
	
	private String senha;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@NotEmpty
	@JoinColumn(name="categoria_id_fk")
	private Categoria idCategoria;

	@NotEmpty
	@OneToOne
	@JoinColumn(name="endereco_id_fk")
	private Endereco endereco;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "organizador_id_fk")
	private UsuarioEvento organizador;

	
	public Evento() {
	}

	public Evento(Long idEvento, @NotEmpty @Size(min = 2) String nome, @NotEmpty String dataHoraInicio,
			String dataHoraFim, @Size(max = 255) String descricao, boolean isPrivado, String senha,
			@NotEmpty Categoria idCategoria, @NotEmpty Endereco endereco, UsuarioEvento organizador) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.descricaoEvento = descricao;
		this.isPrivado = isPrivado;
		this.senha = senha;
		this.idCategoria = idCategoria;
		this.endereco = endereco;
     	this.organizador = organizador;
	}	

//	public UsuarioEvento getOrganizador() {
//		return organizador;
//	}
//
//	public void setOrganizador(UsuarioEvento organizador) {
//		this.organizador = organizador;
//	}
}
