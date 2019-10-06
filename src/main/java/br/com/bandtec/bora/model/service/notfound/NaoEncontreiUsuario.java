package br.com.bandtec.bora.model.service.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontreiUsuario extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7524563255100849005L;

	public NaoEncontreiUsuario(String exception) {
		super(exception);
	}	
	
	
}
