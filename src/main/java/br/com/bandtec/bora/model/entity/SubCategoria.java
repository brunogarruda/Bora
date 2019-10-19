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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "TBD_SUB_CATEGORIA")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class SubCategoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sub_categoria")
	private Long idSubCategoria;
	
	@Column(name = "nome_sub_categoria")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id_fk")
	@NotEmpty
	private Categoria categoriaIdFk;
	
	

	public SubCategoria() {
	}

	public SubCategoria(Long idSubCategoria, String nome, @NotEmpty Categoria categoriaIdFk) {
		this.idSubCategoria = idSubCategoria;
		this.nome = nome;
		this.categoriaIdFk = categoriaIdFk;
	}
}
