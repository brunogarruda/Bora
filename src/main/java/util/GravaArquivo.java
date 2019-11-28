package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

import org.joda.time.DateTime;

import br.com.bandtec.bora.model.entity.Evento;

public class GravaArquivo {

	public static void gravaArquivo(Evento evento) {
		FileWriter arq = null;
		Formatter saida = null;
		boolean error = false;
		String nomeArquivo;

		nomeArquivo = "i.txt";

		try {
			arq = new FileWriter(nomeArquivo, true);
			saida = new Formatter(arq);
		} catch (IOException erro) {
			System.err.println("Erro ao abrir arquivo.");
			System.exit(1);
		}

		try {
			String header = header(evento);
			String body = body(evento);
			saida.format("%s;%n",header);
			saida.format("%s;%n",body);
					
		} catch (FormatterClosedException erro) {
			System.err.println("Erro ao gravar no arquivo.");
			error = true;
		} finally {
			saida.close();
			try {
				arq.close();
			} catch (IOException erro) {
				System.err.println("Erro ao fechar arquivo.");
				error = true;
			}
			if (error) {
				System.exit(1);
			}
		}
	}

	public static String header(Evento evento) {

		String id = String.format("%05d", evento.getIdEvento());
		return "AlteracaoDeEvento" + id + DateTime.now().toString("dd/MM/yyyy HH:mm:ss");
	}
	
	public static String body(Evento evento) {
		
		String privado = evento.isPrivado() ? "1" : "0";

		return  String.format("%30.30s", evento.getNome())
				+String.format("%16.16s", evento.getDataHoraInicio())
				+String.format("%16.16s", evento.getDataHoraFim())
				+String.format("%60.60s", evento.getDescricaoEvento())
				+privado
				+String.format("%30.30s", evento.getEndereco().getRua())
				+String.format("%5.5s", evento.getEndereco().getNumero())
				+String.format("%20.20s", evento.getEndereco().getBairro())
				+String.format("%9.9s", evento.getEndereco().getCep())
				+String.format("%20.20s", evento.getEndereco().getCidade())
				+String.format("%20.20s", evento.getEndereco().getEstado());
	}
	
	public static String Trailer(Evento evento) {
		return "a";
	}

}
