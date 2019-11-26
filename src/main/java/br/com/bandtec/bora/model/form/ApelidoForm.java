package br.com.bandtec.bora.model.form;

import lombok.Data;

@Data
public class ApelidoForm {
	
	String apelido;

	public ApelidoForm() {
	}
	
	public ApelidoForm(String apelido) {
		this.apelido = apelido;
	}
	
	

}
