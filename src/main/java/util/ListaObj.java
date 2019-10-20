package util;

public class ListaObj <T> {
	
	private T[] vetor;
	private int nroElem;	
	
	public ListaObj(int tam) {
		vetor = (T[]) new Object[tam];
		nroElem = 0;
	}
	
	public boolean adiciona(T elem) {
		if (nroElem == vetor.length) {
			System.out.println("Lista cheia");
			return false;
		}
		else {
			vetor[nroElem++] = elem;
			return true;
		}
	}
	
	public void exibe() {
		if (nroElem == 0) {
			System.out.println("\nLista vazia");
		}
		else {
			System.out.println("\nLista:");
			for (int i=0; i < nroElem; i++) {
				System.out.println(vetor[i]);	
			}
		}
	}

	public int busca (T elem) {
		for (int i=0; i < nroElem; i++) { 
			if (vetor[i].equals(elem)) {	
				return i;
			}
		}
		return -1;
	}
	
	public boolean removePeloIndice (int indice) {
		if (indice < 0 || indice >= nroElem) {
			return false;
		}
		else {
			for (int i=indice; i < nroElem-1; i++) {
				vetor[i] = vetor[i+1];
			}
			nroElem--;
			return true;
		}
	}
	
	public boolean removeElemento(T elem) {
		return removePeloIndice(busca(elem));
	}
	
	public int getTamanho() {
		return nroElem;
	}
	
	public T getElemento(int indice) {
		if (indice < 0 || indice >= nroElem) {
			return null;
		}
		else {
			return vetor[indice];
		}
	}
	
	public void limpa() {
		nroElem = 0;
	}
	
}


