package br.com.bandtec.bora.model.dto;

import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.entity.Usuario;
import br.com.bandtec.bora.model.entity.Categoria;;

public class CadastrarEvento {

	private Usuario usuario;
	private Evento evento;
	private Categoria categoria;

	public CadastrarEvento() {
	}

	public CadastrarEvento(Usuario usuario, Evento evento, Categoria categoria) {
		this.usuario = usuario;
		this.evento = evento;
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
