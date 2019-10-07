package br.com.bandtec.bora.model.excecoes.validation;

import java.util.Date;

public class ErrosDetalhe {
	private Date momento;
	private String msg;
	private String detalhe;

	public ErrosDetalhe(Date momento, String msg, String detalhe) {
		this.momento = momento;
		this.msg = msg;
		this.detalhe = detalhe;
	}

	public Date getMomento() {
		return momento;
	}

	public String getMsg() {
		return msg;
	}

	public String getDetalhe() {
		return detalhe;
	}

}
