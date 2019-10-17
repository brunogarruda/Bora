package br.com.bandtec.bora.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tbd_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	@JsonIgnore
	private Long idCategoria;

	@NotEmpty
	@Column(name = "nome_categoria",unique = true)
	private String nomeCategoria;

//	@OneToMany(mappedBy = "categoria")
//	@JsonIgnoreProperties
//	private List<Evento> eventos;

	public Categoria() {
	}

	public Categoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria(Long idCategoria, @NotEmpty String nomeCategoria, List<Evento> eventos) {
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
//		this.eventos = eventos;
	}


//	public List<Evento> getEventos() {
//		return eventos;
//	}
//
//	public void setEventos(List<Evento> eventos) {
//		this.eventos = eventos;
//	}
}
