package br.com.bandtec.bora.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bandtec.bora.model.entity.SubCategoria;
import br.com.bandtec.bora.repository.SubCategoriaRepositorio;

@Service
public class SubCategoriaService {
	
	@Autowired
	private SubCategoriaRepositorio subCategoriaRepositorio;

	public List<SubCategoria> buscarTodasSubCategorias() {
		List<SubCategoria> subCategorias = subCategoriaRepositorio.findAll();
		return subCategorias;
	}

}
