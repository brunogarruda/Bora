package br.com.bandtec.bora.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tbd_endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEndereco")
	@JsonIgnore
	private Long idEndereco;
	
	private String rua;
	
	private String numero;
	
	private String cep;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;

	public Long getIdEndereco() {
		return idEndereco;
	}

	
	public Endereco() {
	}

	public Endereco(Long idEndereco, String rua, String numero, String cep, String bairro, String cidade,
			String estado) {
		this.idEndereco = idEndereco;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
}
