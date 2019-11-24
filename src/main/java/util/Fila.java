package util;

import br.com.bandtec.bora.model.entity.UsuarioEvento;

public class Fila {

	private int tamanho;
	private UsuarioEvento[] fila;

	public Fila(int capacidade) {
		this.tamanho = 0;
		this.fila = new UsuarioEvento[capacidade];
	}

	public boolean isEmpty() {
		return (tamanho == 0);
	}

	public boolean isFull() {
		return (tamanho == fila.length);
	}

	public void insert(UsuarioEvento info) {
		if (isFull())
			return;

		fila[tamanho++] = info;
	}

	public UsuarioEvento peek() {
		return fila[0];
	}

	public UsuarioEvento poll() {
		if(isEmpty()) {
			return null;	
		}
		
		UsuarioEvento a = fila[0];
		for(int i = 0; i < tamanho -1; i++) {
			fila[i] = fila[i+1];
		}
		tamanho--;
		return a;
	}
	
	public void exibe() {
		if(isEmpty())
			return;
		for(int i=0; i < tamanho;i++) {
			System.out.println(fila[i]);
		}
	}
	
	

}
