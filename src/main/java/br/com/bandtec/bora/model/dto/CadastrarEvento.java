package br.com.bandtec.bora.model.dto;

import br.com.bandtec.bora.model.entity.Evento;
import br.com.bandtec.bora.model.entity.Usuario;
import lombok.Data;
import br.com.bandtec.bora.model.entity.Categoria;;

@Data
public class CadastrarEvento {

	private Usuario usuario;
	private Evento evento;
	private Categoria categoria;

	public CadastrarEvento() {
	}

	public CadastrarEvento(Usuario usuario, Evento evento, Categoria categoria) {
		this.usuario = new Usuario(usuario.getIdUsuario());
		this.evento = evento;
	}
}
