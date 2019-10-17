package br.com.bandtec.bora.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TBD_SUB_CATEGORIA")
public class SubCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sub_categoria")
	@JsonIgnore
	private Long id;
	
	@Column(name = "nome_sub_categoria")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	@NotEmpty
	private Categoria categoria;
	
	

	public SubCategoria() {
	}

	public SubCategoria(Long id, String nome, @NotEmpty Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}
}
