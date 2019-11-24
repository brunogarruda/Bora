package br.com.bandtec.bora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.bora.model.entity.SubCategoria;
import br.com.bandtec.bora.model.service.SubCategoriaService;

@RestController
@RequestMapping("/api/sub-categoria")
public class SubCategoriaController {
	
	@Autowired
	private SubCategoriaService subCategoriaService;
	
	@GetMapping
	public ResponseEntity<List<SubCategoria>> buscarTodasSubCategorias() throws Exception{
		return ResponseEntity.ok(subCategoriaService.buscarTodasSubCategorias());
	}
}