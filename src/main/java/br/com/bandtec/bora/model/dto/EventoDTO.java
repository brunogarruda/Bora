package br.com.bandtec.bora.model.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bandtec.bora.model.entity.Endereco;
import br.com.bandtec.bora.model.entity.Recorrencia;
import br.com.bandtec.bora.model.entity.SubCategoria;
import br.com.bandtec.bora.model.entity.Usuario;
import lombok.Data;

@Data
public class EventoDTO {
	
	
	private Long idEvento;

	private String nome;
	
	private String dataHoraInicio;

	private String dataHoraFim;
	
	private String descricaoEvento;

	private boolean isPrivado;
	
	private String senha;
	
	private Long idSubCategoria;

	private Endereco endereco;
	
	private Recorrencia recorrencia;
	
	private String organizador;
	
	private String criador;

	private double avaliacao;
	
	private List<Usuario> participantes;

	public EventoDTO(Long idEvento, String nome, String dataHoraInicio, String dataHoraFim, String descricaoEvento,
			String senha, Long idSubCategoria, Endereco endereco, Recorrencia recorrencia,
			String organizador, double avaliacao, List<Usuario> participantes) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.descricaoEvento = descricaoEvento;
		this.senha = senha;
		this.idSubCategoria = idSubCategoria;
		this.endereco = endereco;
		this.recorrencia = recorrencia;
		this.organizador = organizador;
		this.avaliacao = avaliacao;
		this.participantes = participantes;
	}
}
