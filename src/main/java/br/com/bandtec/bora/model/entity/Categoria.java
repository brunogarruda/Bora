package br.com.bandtec.bora.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "tbd_categoria")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;

	@NotEmpty
	@Column(name = "nome_categoria",unique = true)
	private String nomeCategoria;

/*	@OneToMany(mappedBy = "idCategoria")
	private List<Evento> eventos; */
	
	@OneToMany(mappedBy = "categoriaIdFk")
	private List<SubCategoria> subcategorias;

	public Categoria() {
	}

	public Categoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

//	public Categoria(Long idCategoria, @NotEmpty String nomeCategoria, List<Evento> eventos) {
//		this.idCategoria = idCategoria;
//		this.nomeCategoria = nomeCategoria;
////		this.eventos = eventos;
//	}
	
	public Categoria(Long idCategoria, @NotEmpty String nomeCategoria, List<SubCategoria> subcategorias) {
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
		this.subcategorias = subcategorias;
	}


//	public List<Evento> getEventos() {
//		return eventos;
//	}
//
//	public void setEventos(List<Evento> eventos) {
//		this.eventos = eventos;
//	}
}
