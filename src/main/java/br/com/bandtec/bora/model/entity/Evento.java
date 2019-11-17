package br.com.bandtec.bora.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//	@DateTimeFormat(pattern = " dd/MM/yyyy hh:mm")
	private String dataHoraInicio;

	@Column(name = "data_hora_fim")
//	@DateTimeFormat(pattern = " dd/MM/yyyy hh:mm")
	private String dataHoraFim;
	
	@Size(max = 255)
	@Column(name = "descricao")
	private String descricaoEvento;

	@Column(name = "is_privado")
	private boolean isPrivado;
	
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="sub_categoria_id_fk", referencedColumnName = "id_sub_categoria")
	private SubCategoria idSubCategoria;

	@NotEmpty
	@OneToOne
	@JoinColumn(name="idEndereco")
	private Endereco endereco;
	
	@OneToOne
	@JoinColumn(name="id_recorrencia")
	private Recorrencia recorrencia;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "organizador_id")
	private Usuario organizador;
	
	@Transient
	@OneToMany
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Usuario> participantes;

	
	public Evento() {
	}

	public Evento(Long idEvento, @NotEmpty @Size(min = 2) String nome, @NotEmpty String dataHoraInicio,
			String dataHoraFim, @Size(max = 255) String descricao, boolean isPrivado, String senha,
			@NotEmpty SubCategoria subcategoriaIdFk, @NotEmpty Endereco endereco, Usuario organizador, Recorrencia recorrencia) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.descricaoEvento = descricao;
		this.isPrivado = isPrivado;
		this.senha = senha;
		this.idSubCategoria = subcategoriaIdFk;
		this.endereco = endereco;
		this.organizador = organizador;
		this.recorrencia = recorrencia;
	}	
}
