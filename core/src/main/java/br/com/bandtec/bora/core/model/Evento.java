package br.com.bandtec.bora.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
@Table(name = "tbd_evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column n(name = "id_evento")
	private Long idEvento;

	@NotEmpty
	@Size(min = 2)
	@Column(name = "nome_evento")
	private String nome;

	@Size(max = 255)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@NotEmpty
	private Categoria categoria;

	@NotEmpty
	@Column(name = "hora_inicio")
	private String horaInicio;

	@Column(name = "hora_fim")
	private String horaFim;

	@NotEmpty
	private String endereco;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "organizador_id")
	private Usuario organizador;

	public Evento() {
	}

	public Evento(Long idEvento, @NotEmpty @Size(min = 2) String nome, @NotEmpty Categoria categoria,
                  @NotEmpty Time horaInicio, @NotEmpty String endereco, Usuario organizador) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.categoria = categoria;
		this.horaInicio = horaInicio;
		this.endereco = endereco;
		this.organizador = organizador;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
	}
}
