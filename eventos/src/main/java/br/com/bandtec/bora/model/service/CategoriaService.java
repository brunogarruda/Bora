package br.com.bandtec.bora.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.bandtec.bora.model.entity.Categoria;

@Service
public interface CategoriaService {
	List<Categoria> pesquisarTodasCategorias();
	
}
